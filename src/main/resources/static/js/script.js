// ==========================================
// myStudent - JavaScript
// ==========================================


// Mostra/nascondi campi in base al ruolo selezionato
    function toggleRoleFields() {
        const role = document.getElementById('role').value;
        const subjectGroup = document.getElementById('subjectGroup');
        const classNamesInput = document.getElementById('classNames');
        const classNamesLabel = document.getElementById('classNamesLabel');
        const classNamesHelp = document.getElementById('classNamesHelp');

        if (role === 'TEACHER') {
            // Mostra campo materia
            subjectGroup.style.display = 'block';
            document.getElementById('subject').setAttribute('required', 'required');

            // Aggiorna placeholder e help text per classi multiple
            classNamesInput.placeholder = 'es. 3A,4B,5C';
            classNamesLabel.textContent = 'Classi Insegnate';
            classNamesHelp.textContent = 'Inserisci le classi separate da virgola (es. 3A,4B,5C)';
        } else if (role === 'STUDENT') {
            // Nascondi campo materia
            subjectGroup.style.display = 'none';
            document.getElementById('subject').removeAttribute('required');

            // Aggiorna placeholder e help text per classe singola
            classNamesInput.placeholder = 'es. 3A';
            classNamesLabel.textContent = 'Classe';
            classNamesHelp.textContent = 'Inserisci la tua classe (es. 3A)';
        } else {
            // Nessun ruolo selezionato
            subjectGroup.style.display = 'none';
            document.getElementById('subject').removeAttribute('required');
            classNamesHelp.textContent = '';
        }
    }

    // Inizializza al caricamento della pagina
    document.addEventListener('DOMContentLoaded', function() {
        toggleRoleFields();
    });

// Funzione per mostrare/nascondere password
function togglePassword(inputId) {
    const input = document.getElementById(inputId);
    const button = input.nextElementSibling;
    const icon = button.querySelector('.material-symbols-outlined');
    
    if (input.type === 'password') {
        input.type = 'text';
        icon.textContent = 'visibility_off';
    } else {
        input.type = 'password';
        icon.textContent = 'visibility';
    }
}

// Gestione form di registrazione
const registerForm = document.querySelector('form[action="#"]');
if (registerForm && window.location.pathname.includes('registrazione')) {
    registerForm.addEventListener('submit', function(e) {

        const formData = new FormData(this);
        const data = Object.fromEntries(formData);
        
        // Validazione codice fiscale (semplice)
        const codiceFiscale = data['codice-fiscale'];
        if (codiceFiscale && codiceFiscale.length !== 16) {
            alert('Il codice fiscale deve essere di 16 caratteri');
            return;
        }
        
        // Qui puoi aggiungere la logica di registrazione
        console.log('Registration data:', data);
        
        alert('Registrazione completata! (questa è una demo)');
        // window.location.href = 'login.html';
    });
}

// Gestione logout
const logoutButton = document.querySelector('.logout-button');
const logoutForm = document.getElementById('logoutForm');

if (logoutButton && logoutForm) {
    logoutButton.addEventListener('click', function(e) {
        e.preventDefault(); // Previene il comportamento default del link

        if (confirm('Sei sicuro di voler uscire?')) {
            console.log('Submitting logout form...');
            logoutForm.submit(); // QUESTO È FONDAMENTALE: invia il form a Spring
        }
    });
}

// Formattazione automatica codice fiscale in maiuscolo
const codiceFiscaleInput = document.getElementById('codice-fiscale');
if (codiceFiscaleInput) {
    codiceFiscaleInput.addEventListener('input', function(e) {
        this.value = this.value.toUpperCase();
    });
}

// Gestione dropdown menu utente (alternativa senza hover per mobile)
const userMenuButton = document.querySelector('.user-menu-button');
const userMenuDropdown = document.querySelector('.user-menu-dropdown');

if (userMenuButton && userMenuDropdown) {
    // Per dispositivi touch, gestisci il click
    let isOpen = false;
    
    userMenuButton.addEventListener('click', function(e) {
        e.stopPropagation();
        isOpen = !isOpen;
        
        if (isOpen) {
            userMenuDropdown.style.opacity = '1';
            userMenuDropdown.style.visibility = 'visible';
            userMenuDropdown.style.transform = 'translateY(0)';
        } else {
            userMenuDropdown.style.opacity = '0';
            userMenuDropdown.style.visibility = 'hidden';
            userMenuDropdown.style.transform = 'translateY(10px)';
        }
    });
    
    // Chiudi il menu quando si clicca fuori
    document.addEventListener('click', function() {
        if (isOpen) {
            isOpen = false;
            userMenuDropdown.style.opacity = '0';
            userMenuDropdown.style.visibility = 'hidden';
            userMenuDropdown.style.transform = 'translateY(10px)';
        }
    });
    
    // Previeni la chiusura quando si clicca dentro il menu
    userMenuDropdown.addEventListener('click', function(e) {
        e.stopPropagation();
    });
}

// Animazione caricamento pagina
document.addEventListener('DOMContentLoaded', function() {
    console.log('myStudent App - Ready');
    
    // Aggiungi classe per animazioni se necessario
    document.body.classList.add('loaded');
});

// Gestione responsive per il menu mobile (se necessario)
function handleResize() {
    const width = window.innerWidth;
    
    if (width < 640) {
        // Mobile view
        console.log('Mobile view');
    } else {
        // Desktop view
        console.log('Desktop view');
    }
}

// Ascolta il resize
window.addEventListener('resize', handleResize);
handleResize(); // Chiama subito all'avvio
