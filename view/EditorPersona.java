package view;
import javax.swing.*;
import java.awt.*;

import model.Persona;
import model.Rubrica;

public class EditorPersona extends JFrame {

    private JTextField nome = new JTextField(15);
    private JTextField cognome = new JTextField(15);
    private JTextField indirizzo = new JTextField(15);
    private JTextField telefono = new JTextField(15);
    private JTextField eta = new JTextField(5);

    public EditorPersona(MainFrame parent, Rubrica rubrica, Persona p, int index) {
        setTitle("Editor Persona");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nome")); add(nome);
        add(new JLabel("Cognome")); add(cognome);
        add(new JLabel("Indirizzo")); add(indirizzo);
        add(new JLabel("Telefono")); add(telefono);
        add(new JLabel("Età")); add(eta);

        if (p != null) {
            nome.setText(p.getNome());
            cognome.setText(p.getCognome());
            indirizzo.setText(p.getIndirizzo());
            telefono.setText(p.getTelefono());
            eta.setText(String.valueOf(p.getEta()));
        }

        JButton salva = new JButton("Salva");
        JButton annulla = new JButton("Annulla");

        salva.addActionListener(e -> {
            Persona nuova = new Persona(
                    nome.getText(),
                    cognome.getText(),
                    indirizzo.getText(),
                    telefono.getText(),
                    Integer.parseInt(eta.getText())
            );

            if (index == -1)
                rubrica.aggiungi(nuova);
            else
                rubrica.modifica(index, nuova);

            parent.aggiorna();
            dispose();
        });

        annulla.addActionListener(e -> dispose());

        add(salva);
        add(annulla);
    }
}