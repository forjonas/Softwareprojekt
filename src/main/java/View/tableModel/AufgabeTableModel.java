package View.tableModel;

import entity.aufgabe.Aufgabe;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TabelModel für die Tabelle der Aufgaben
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class AufgabeTableModel extends AbstractTableModel {
    private List<Aufgabe> aufgabenliste;
    private String[] columnNames = {"Name", "Kategorie", "Bearbeitungszeit", "Aufgabentyp", "Schwierigkeit", "Punkte"};

    public AufgabeTableModel(List<Aufgabe> aufgabenliste) {
        this.aufgabenliste = aufgabenliste;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return aufgabenliste.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 2:
                return String.class;
            case 1:
                return Kategorie.class;
            case 3:
                return Aufgabentyp.class;
            case 4:
                return Schwierigkeitsgrad.class;
            case 5:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return aufgabenliste.get(rowIndex).getName();
            case 1:
                return aufgabenliste.get(rowIndex).getKategorie();
            case 2:
                return aufgabenliste.get(rowIndex).getBearbeitungszeit() + " Min";
            case 3:
                return aufgabenliste.get(rowIndex).getAufgabentyp();
            case 4:
                return aufgabenliste.get(rowIndex).getSchwierigkeitsgrad();
            case 5:
                return aufgabenliste.get(rowIndex).getPunktewert();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
