package model;


import java.util.*;
import java.io.*;


public class Rubrica {
    private List<Persona> persone = new ArrayList<>();
    private final String FILE = "informazioni.txt";

    public List<Persona> getPersone() {
        return persone;
    }

    public void aggiungi(Persona p) {
        persone.add(p);
        salva();
    }

    public void modifica(int index, Persona p) {
        persone.set(index, p);
        salva();
    }

    public void elimina(int index) {
        persone.remove(index);
        salva();
    }

    // Persistenza prima
    public void salva() {
        try (PrintStream ps = new PrintStream(new File(FILE))) {
            for (Persona p : persone) {
                ps.println(p.toFileString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Persistenza - dopo
    public void carica() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] dati = sc.nextLine().split(";");
                Persona p = new Persona(
                        dati[0], dati[1], dati[2], dati[3], Integer.parseInt(dati[4])
                );
                persone.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}