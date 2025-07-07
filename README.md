\## progettoCS



\# Sistema di Autenticazione Passwordless con OTP



Questo progetto implementa un sistema di autenticazione \*\*passwordless\*\* basato sull’invio di un \*\*codice OTP (One-Time Password)\*\* via email. Gli utenti si autenticano semplicemente fornendo il proprio indirizzo email e inserendo il codice ricevuto, senza necessità di usare una password tradizionale.



\## Tecnologie Utilizzate



\- \*\*Java 11\*\*

\- \*\*Spring Boot\*\*

\- \*\*Spring Mail\*\*

\- \*\*Maven\*\*



\## Funzionalità



\- Invio di codice OTP a un indirizzo email.

\- Verifica del codice OTP entro una finestra temporale di 5 minuti.

\- Endpoint RESTful per interagire con il sistema.

\- Salvataggio temporaneo degli OTP in memoria (HashMap).



\## Struttura del Progetto



\- `controller`: gestisce le richieste HTTP.

\- `service`: contiene la logica per la generazione, memorizzazione e verifica degli OTP.

\- `model`: definisce le classi per i dati e le richieste.

\- `PasswordlessOtpApplication.java`: punto di ingresso dell’applicazione.



\## Endpoints REST



\### `POST /auth/request-otp`



Richiede un OTP per un indirizzo email.



\#### Request Body

```json

{

&nbsp; "email": "utente@example.com"

}

```



\####Response

&nbsp;- 200 OK: OTP inviato con successo.



&nbsp;- 400 Bad Request: email non valida.



