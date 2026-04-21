package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Persona;
import model.Rubrica;

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

        model = new DefaultTableModel(new String[]{"Nome", "Cognome", "Telefono"}, 0);
        table = new JTable(model);
        refreshTable();

        JButton btnNuovo = new JButton("Nuovo");
        JButton btnModifica = new JButton("Modifica");
        JButton btnElimina = new JButton("Elimina");

        btnNuovo.addActionListener(e -> apriEditor(null, -1));

        btnModifica.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Seleziona una persona");
                return;
            }
            apriEditor(rubrica.getPersone().get(row), row);
        });

        btnElimina.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Seleziona una persona");
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
        new EditorPersona(this, rubrica, p, index).setVisible(true);
    }

    public void aggiorna() {
        refreshTable();
    }
}