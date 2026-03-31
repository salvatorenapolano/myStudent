# myStudent 🎓

**myStudent** è un'applicazione web sviluppata con **Spring Web** e **Spring Security** che permette la gestione di studenti, voti e assenze attraverso un sistema di autenticazione con ruoli differenziati.

## 🚀 Funzionalità

### 👩‍🏫 Insegnante
- Visualizzazione di tutti gli studenti
- Accesso alle statistiche:
  - Voti
  - Assenze
  - Media generale
- Inserimento di:
  - Nuovi voti
  - Assenze

### 👨‍🎓 Studente
- Visualizzazione dei propri voti
- Visualizzazione delle proprie assenze

## 🔐 Sicurezza
L'applicazione utilizza **Spring Security** per:
- Autenticazione degli utenti
- Gestione dei ruoli (insegnante / studente)
- Protezione delle risorse

## 🛠️ Tecnologie utilizzate
- **Spring Web** – sviluppo web con Spring MVC
- **Spring Security** – gestione di autenticazione e autorizzazioni
- **Thymeleaf** – template engine per le pagine web
- **Lombok** – riduzione del boilerplate code
- **Validation** – validazione dei dati lato server
- **MySQL Driver** – connessione al database MySQL
- **Spring Data JPA** – gestione dei dati e repository
- **Docker** – containerizzazione dell’applicazione

## 📌 Obiettivo del progetto
Fornire una piattaforma semplice e intuitiva per la gestione scolastica, con accesso controllato in base al ruolo dell’utente.

## ▶️ Avvio del progetto

### Opzione 1 – Locale
1. Clona la repository
2. Configura il database MySQL nel file `application.properties`
3. Avvia l'applicazione con:
   ```bash
   mvn spring-boot:run
4. Accedi dal browser su: http://localhost:8080
   
### Opzione 2 – Docker
(Assicurati di avere docker installato)
1. Clona la repository
2. Configura il database MySQL nel file `application.properties`
3. Costruisci l'immagine Docker:
   ```bash
   docker build -t mystudent .
4. Avvia il container creato:
   ```bash
   docker run -p 8080:8080 mystudent
5. Accedi dal browser su: http://localhost:8080
