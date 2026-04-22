package Rubrica.src.view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Rubrica.src.model.Persona;
import Rubrica.src.model.Rubrica;

import java.awt.*;

public class MainFrame extends JFrame {

    private Rubrica rubrica;
    private JTable table;
    private DefaultTableModel model;

    public MainFrame() {
        rubrica = new Rubrica();
        rubrica.carica();


        setTitle("Rubrica");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // rimuove riga direttamente editabile
        model = new DefaultTableModel(new String[]{"Nome", "Cognome", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };



        table = new JTable(model);
        refreshTable();

        JButton btnNuovo = new JButton("Nuovo");

        btnNuovo.setBackground(new Color(230, 230, 230));
        btnNuovo.setForeground(Color.RED);
        btnNuovo.setFont(new Font("SansSerif", Font.ITALIC, 12));


        JButton btnModifica = new JButton("Modifica");
        JButton btnElimina = new JButton("Elimina");

 
        //NUOVO
        btnNuovo.addActionListener(e -> apriEditor(null, -1));


        //MODIFICA
        btnModifica.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Seleziona una persona"); //(JOptionPane.showMessageDialog(…
                return;
            }
            apriEditor(rubrica.getPersone().get(row), row);
        });

        //ELIMINA
        btnElimina.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Seleziona una persona"); //(JOptionPane.showMessageDialog(…
                return;
            }

            Persona p = rubrica.getPersone().get(row);
            int conferma = JOptionPane.showConfirmDialog(this,
                    "Eliminare " + p.getNome() + " " + p.getCognome() + "?");

            if (conferma == JOptionPane.YES_OPTION) {
                rubrica.elimina(row);
                refreshTable();
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnNuovo);
        panel.add(btnModifica);
        panel.add(btnElimina);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }


    private void refreshTable() {
        model.setRowCount(0);
        for (Persona p : rubrica.getPersone()) {
            model.addRow(new Object[]{p.getNome(), p.getCognome(), p.getTelefono()});
        }
    }

    private void apriEditor(Persona p, int index) {
        new EditorPersona(this, rubrica, p, index).setVisible(true); //apri editorPersona
    }

    public void aggiorna() {
        refreshTable();
    }
}