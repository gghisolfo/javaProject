package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


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


        // setTitle("Rubrica");
        // setSize(600, 400);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("📒 Rubrica");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        Font fontBase = new Font("SansSerif", Font.PLAIN, 14);


        // rimuove riga direttamente editabile
        model = new DefaultTableModel(new String[]{"Nome", "Cognome", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };



        table = new JTable(model);
        table.setFont(fontBase);
        table.setRowHeight(28);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setGridColor(new Color(220, 220, 220));
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(fontBase.deriveFont(Font.BOLD));
        header.setBackground(new Color(240, 240, 240));


        refreshTable();

        // JButton btnNuovo = new JButton("Nuovo");
        // JButton btnModifica = new JButton("Modifica");
        // JButton btnElimina = new JButton("Elimina");
        // btnNuovo.setBackground(new Color(230, 230, 230));
        // btnNuovo.setForeground(Color.RED);
        // btnNuovo.setFont(new Font("SansSerif", Font.ITALIC, 12));

        JButton btnNuovo = creaBottone("Nuovo", new Color(76, 175, 80));
        JButton btnModifica = creaBottone("Modifica", new Color(33, 150, 243));
        JButton btnElimina = creaBottone("Elimina", new Color(244, 67, 54));



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

    private JButton creaBottone(String testo, Color colore) {
            JButton btn = new JButton(testo);
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setBackground(colore);
            btn.setFont(new Font("SansSerif", Font.BOLD, 13));
            btn.setPreferredSize(new Dimension(110, 35));
            return btn;
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
