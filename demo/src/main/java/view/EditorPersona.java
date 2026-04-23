
package view;

import javax.swing.*;
import model.Rubrica;
import model.Persona;
import java.awt.*;

public class EditorPersona extends JDialog {

    private JTextField nome = new JTextField(15);
    private JTextField cognome = new JTextField(15);
    private JTextField indirizzo = new JTextField(15);
    private JTextField telefono = new JTextField(15);
    private JTextField eta = new JTextField(5);

    public EditorPersona(MainFrame parent, Rubrica rubrica, Persona p, int index) {

        super(parent, "Editor Persona", true);

        setSize(350, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));

        form.add(new JLabel("Nome"));
        form.add(nome);
        form.add(new JLabel("Cognome"));
        form.add(cognome);
        form.add(new JLabel("Indirizzo"));
        form.add(indirizzo);
        form.add(new JLabel("Telefono"));
        form.add(telefono);
        form.add(new JLabel("Età"));
        form.add(eta);

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

        JPanel bottoni = new JPanel();
        bottoni.add(salva);
        bottoni.add(annulla);

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.CENTER);
        add(bottoni, BorderLayout.SOUTH);
    }
}