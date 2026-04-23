

# 📕⚙️ Funzionalità – Progetto Rubrica

## 📌 Descrizione funzionale
L’applicazione rappresenta una **rubrica telefonica** che consente all’utente di gestire contatti tramite una **interfaccia grafica Java Swing**.

Ogni contatto è rappresentato da un oggetto `Persona`, memorizzato in una lista.

---

## 🧑‍💼 Classe Persona
Ogni persona contiene i seguenti attributi:

- `nome` (String)
- `cognome` (String)
- `indirizzo` (String)
- `telefono` (String)
- `eta` (int)

---

## 🖥️ Interfaccia grafica

### Finestra principale
La finestra principale mostra:

- Una **JTable** con una riga per ogni persona
- Colonne visibili:
  - Nome
  - Cognome
  - Telefono
- Tre bottoni:
  - **Nuovo**
  - **Modifica**
  - **Elimina**

---

### Finestra Editor Persona
Finestra secondaria per inserire o modificare una persona.

Contiene:
- Campi di input (`JLabel` + `JTextField`) per:
  - Nome
  - Cognome
  - Indirizzo
  - Telefono
  - Età
- Bottoni:
  - **Salva**
  - **Annulla**

---

## 🔘 Comportamento dei bottoni

### ➕ Nuovo
- Apre l’editor persona
- Tutti i campi sono vuoti

---

### ✏️ Modifica
- Nessuna selezione:
  - Errore con `JOptionPane.showMessageDialog`
- Con selezione:
  - Apre l’editor con i campi precompilati

---

### 🗑️ Elimina
- Nessuna selezione:
  - Messaggio di errore
- Con selezione:
  - Finestra di conferma:
    > “Eliminare la persona NOME COGNOME?”
  - **Sì** → elimina la persona e aggiorna la tabella
  - **No** → nessuna modifica

---

### 💾 Salva (Editor)
- Salva i dati della persona
- Aggiorna la lista
- Chiude la finestra editor

---

### ❌ Annulla (Editor)
- Chiude l’editor
- Non salva alcun dato

---

## 💽 Persistenza dei dati

### File di salvataggio
Percorso: informatio.txt

Formato:
nome;cognome;indirizzo;telefono;eta

Esempio:
Steve;Jobs;via Cupertino 13;0612344;56

Bill;Gates;via Redmond 10;06688989;60

Babbo;Natale;via del Polo Nord;00000111;99



---

### 🔁 Avvio dell’applicazione
- Se il file esiste:
  - I dati vengono caricati automaticamente
- Se il file non esiste:
  - La rubrica parte vuota
  - Nessun errore viene mostrato

---

### 🔄 Salvataggio automatico
- Ad ogni salvataggio di una persona:
  - Tutte le persone vengono scritte nel file
  - I dati su file e tabella restano sincronizzati