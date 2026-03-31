// ==========================================
// myStudent - JavaScript per Insegnanti
// ==========================================

// Variabile globale per il grafico
let gradesChart = null;

// Funzioni per gestire i modali
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'flex';
        document.body.style.overflow = 'hidden';
    }
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';

        // Reset del form
        const form = modal.querySelector('form');
        if (form) {
            form.reset();
        }

        // Distruggi il grafico quando chiudi il modal
        if (modalId === 'graficoModal' && gradesChart) {
            gradesChart.destroy();
            gradesChart = null;
        }
    }
}

// Chiudi modal cliccando fuori
window.addEventListener('click', function(e) {
    if (e.target.classList.contains('modal')) {
        closeModal(e.target.id);
    }
});

// Chiudi modal con ESC
document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => {
            if (modal.style.display === 'flex') {
                closeModal(modal.id);
            }
        });
    }
});

// Sistema di notifiche
function showNotification(message, type = 'info') {
    // Rimuovi notifiche esistenti
    const existingNotification = document.querySelector('.notification');
    if (existingNotification) {
        existingNotification.remove();
    }

    // Crea nuova notifica
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;

    // Scegli l'icona in base al tipo
    let icon = 'info';
    if (type === 'success') icon = 'check_circle';
    else if (type === 'error') icon = 'error';
    else if (type === 'warning') icon = 'warning';

    notification.innerHTML = `
        <span class="material-symbols-outlined">${icon}</span>
        <span>${message}</span>
    `;

    document.body.appendChild(notification);

    // Mostra notifica
    setTimeout(() => {
        notification.classList.add('show');
    }, 10);

    // Rimuovi dopo 3 secondi
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 3000);
}

// ==========================================
// ✅ FUNZIONE PER CREARE IL GRAFICO
// ==========================================
function createGradesChart(grades, average) {
    // Distruggi il grafico precedente se esiste
    if (gradesChart) {
        gradesChart.destroy();
    }

    const ctx = document.getElementById('gradesChart').getContext('2d');
    
    // Crea le etichette (Voto 1, Voto 2, ecc.)
    const labels = grades.map((_, index) => `Voto ${index + 1}`);
    
    // Colore della linea basato sulla media
    const lineColor = average >= 6 ? '#22c55e' : '#dc2626';
    const backgroundColor = average >= 6 ? 'rgba(34, 197, 94, 0.1)' : 'rgba(220, 38, 38, 0.1)';

    gradesChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Voti',
                data: grades,
                borderColor: lineColor,
                backgroundColor: backgroundColor,
                borderWidth: 3,
                pointBackgroundColor: lineColor,
                pointBorderColor: '#ffffff',
                pointBorderWidth: 2,
                pointRadius: 6,
                pointHoverRadius: 8,
                tension: 0.3, // Curve più morbide
                fill: true
            },
            {
                label: 'Media',
                data: Array(grades.length).fill(average),
                borderColor: '#135bec',
                backgroundColor: 'transparent',
                borderWidth: 2,
                borderDash: [5, 5],
                pointRadius: 0,
                pointHoverRadius: 0
            },
            {
                label: 'Sufficienza',
                data: Array(grades.length).fill(6),
                borderColor: '#94a3b8',
                backgroundColor: 'transparent',
                borderWidth: 1,
                borderDash: [10, 5],
                pointRadius: 0,
                pointHoverRadius: 0
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    display: true,
                    position: 'top',
                    labels: {
                        font: {
                            family: 'Lexend, sans-serif',
                            size: 12,
                            weight: 600
                        },
                        padding: 15,
                        usePointStyle: true,
                        pointStyle: 'circle'
                    }
                },
                tooltip: {
                    backgroundColor: '#ffffff',
                    titleColor: '#111318',
                    bodyColor: '#616f89',
                    borderColor: '#dbdfe6',
                    borderWidth: 1,
                    padding: 12,
                    displayColors: true,
                    titleFont: {
                        family: 'Lexend, sans-serif',
                        size: 14,
                        weight: 700
                    },
                    bodyFont: {
                        family: 'Lexend, sans-serif',
                        size: 13,
                        weight: 500
                    },
                    callbacks: {
                        label: function(context) {
                            let label = context.dataset.label || '';
                            if (label) {
                                label += ': ';
                            }
                            if (context.parsed.y !== null) {
                                label += context.parsed.y.toFixed(1);
                            }
                            return label;
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    max: 10,
                    ticks: {
                        stepSize: 1,
                        font: {
                            family: 'Lexend, sans-serif',
                            size: 12,
                            weight: 500
                        },
                        color: '#616f89'
                    },
                    grid: {
                        color: '#f0f0f0',
                        drawBorder: false
                    }
                },
                x: {
                    ticks: {
                        font: {
                            family: 'Lexend, sans-serif',
                            size: 11,
                            weight: 500
                        },
                        color: '#616f89'
                    },
                    grid: {
                        display: false,
                        drawBorder: false
                    }
                }
            }
        }
    });
}

// Validazione voto (deve essere tra 1 e 10)
const votoInput = document.getElementById('voto-valore');
if (votoInput) {
    votoInput.addEventListener('input', function() {
        const value = parseFloat(this.value);
        if (value < 1) this.value = 1;
        if (value > 10) this.value = 10;
    });
}

// Validazione ore assenza (deve essere tra 1 e 8)
const oreInput = document.getElementById('assenza-ore');
if (oreInput) {
    oreInput.addEventListener('input', function() {
        const value = parseInt(this.value);
        if (value < 1) this.value = 1;
        if (value > 8) this.value = 8;
    });
}

// ==========================================
// EVENT LISTENERS per i pulsanti
// ==========================================

// Inizializzazione
document.addEventListener('DOMContentLoaded', function() {
    console.log('Dashboard Insegnanti - Ready');

    // Imposta la data di oggi come default per i campi data
    const today = new Date().toISOString().split('T')[0];
    const dateInputs = document.querySelectorAll('input[type="date"]');
    dateInputs.forEach(input => {
        if (!input.value) {
            input.value = today;
        }
    });

    // ==========================================
    // GESTIONE PULSANTI VOTO
    // ==========================================
    const btnVoto = document.querySelectorAll('.btn-voto');
    btnVoto.forEach(button => {
        button.addEventListener('click', function() {
            // Leggi i dati dal data-* attributes
            const studentId = this.getAttribute('data-student-id');
            const firstName = this.getAttribute('data-first-name');
            const lastName = this.getAttribute('data-last-name');
            const className = this.getAttribute('data-class-name');

            // Apri il modal
            openModal('votoModal');

            // Imposta l'ID studente nel campo hidden
            const studentIdInput = document.getElementById('voto-studente-id');
            if (studentIdInput) {
                studentIdInput.value = studentId;
            }

            // Visualizza nome completo e classe nel campo readonly
            const studentDisplay = document.getElementById('voto-studente-display');
            if (studentDisplay) {
                studentDisplay.value = `${firstName} ${lastName} - ${className}`;
            }

            // Imposta la data di oggi
            const dateInput = document.getElementById('voto-data');
            if (dateInput) {
                dateInput.value = today;
            }
        });
    });

    // ==========================================
    // GESTIONE PULSANTI ASSENZA
    // ==========================================
    const btnAssenza = document.querySelectorAll('.btn-assenza');
    btnAssenza.forEach(button => {
        button.addEventListener('click', function() {
            // Leggi i dati dal data-* attributes
            const studentId = this.getAttribute('data-student-id');
            const firstName = this.getAttribute('data-first-name');
            const lastName = this.getAttribute('data-last-name');
            const className = this.getAttribute('data-class-name');

            // Apri il modal
            openModal('assenzaModal');

            // Imposta l'ID studente nel campo hidden
            const studentIdInput = document.getElementById('assenza-studente-id');
            if (studentIdInput) {
                studentIdInput.value = studentId;
            }

            // Visualizza nome completo e classe nel campo readonly
            const studentDisplay = document.getElementById('assenza-studente-display');
            if (studentDisplay) {
                studentDisplay.value = `${firstName} ${lastName} - ${className}`;
            }

            // Imposta la data di oggi
            const dateInput = document.getElementById('assenza-data');
            if (dateInput) {
                dateInput.value = today;
            }
        });
    });

    // ==========================================
    // ✅ GESTIONE PULSANTI GRAFICO (MEDIA CLICCABILE)
    // ==========================================
    const btnGrafico = document.querySelectorAll('.btn-grafico');
    btnGrafico.forEach(button => {
        button.addEventListener('click', function() {
            // Leggi i dati dal data-* attributes
            const firstName = this.getAttribute('data-first-name');
            const lastName = this.getAttribute('data-last-name');
            const className = this.getAttribute('data-class-name');
            const gradesString = this.getAttribute('data-grades');
            const average = parseFloat(this.getAttribute('data-average'));

            // Converti la stringa di voti in un array di numeri
            const grades = gradesString ? gradesString.split(',').map(g => parseFloat(g.trim())) : [];

            // Verifica che ci siano voti
            if (grades.length === 0) {
                showNotification('Nessun voto disponibile per questo studente', 'info');
                return;
            }

            // Apri il modal
            openModal('graficoModal');

            // Imposta i dati dello studente
            const studentName = document.getElementById('chart-student-name');
            const studentClass = document.getElementById('chart-student-class');
            const chartAverage = document.getElementById('chart-average');

            if (studentName) {
                studentName.textContent = `${firstName} ${lastName}`;
            }

            if (studentClass) {
                studentClass.textContent = `Classe ${className}`;
            }

            if (chartAverage) {
                chartAverage.textContent = average.toFixed(1);
                // Colora la media
                if (average >= 6) {
                    chartAverage.style.color = '#22c55e';
                } else {
                    chartAverage.style.color = '#dc2626';
                }
            }

            // Calcola le statistiche
            const maxGrade = Math.max(...grades);
            const minGrade = Math.min(...grades);
            const totalGrades = grades.length;

            // Aggiorna le statistiche
            const maxGradeElement = document.getElementById('max-grade');
            const minGradeElement = document.getElementById('min-grade');
            const totalGradesElement = document.getElementById('total-grades');

            if (maxGradeElement) {
                maxGradeElement.textContent = maxGrade.toFixed(1);
            }

            if (minGradeElement) {
                minGradeElement.textContent = minGrade.toFixed(1);
            }

            if (totalGradesElement) {
                totalGradesElement.textContent = totalGrades;
            }

            // Crea il grafico
            createGradesChart(grades, average);
        });
    });

    // ==========================================
    // ✅ GESTIONE MESSAGGI FLASH DAL SERVER
    // ==========================================
    
    // Controlla se ci sono messaggi flash negli attributi data del body
    const body = document.body;
    const successMessage = body.getAttribute('data-success-message');
    const errorMessage = body.getAttribute('data-error-message');

    if (successMessage) {
        showNotification(successMessage, 'success');
    }
    
    if (errorMessage) {
        showNotification(errorMessage, 'error');
    }
});
