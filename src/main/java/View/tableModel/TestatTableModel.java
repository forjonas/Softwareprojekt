package View.tableModel;

import entity.aufgabensammlung.Testat;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TabelModel für die Katalog-Tabelle der Testate
 *
 * @author Jonas Herbst
 * @version 06.05.22
 */
public class TestatTableModel extends AbstractTableModel {
    private List<Testat> testatliste;
    private final String[] COLUMN_NAMES = {"Name", "Dozent", "Zeit", "Punktzahl"};

    public TestatTableModel(List<Testat> testatliste) {
        this.testatliste = testatliste;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    @Override
    public int getRowCount() {
        return testatliste.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1:
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                if (testatliste.get(rowIndex).getName() == null) {
                    return "Fehler, Name ist Null";
                } else {
                    return testatliste.get(rowIndex).getName();
                }
            }
            case 1: {
                if (testatliste.get(rowIndex).getTestatErsteller() == null) {
                    return "Fehler, Dozent ist Null";
                } else if (testatliste.get(rowIndex).getTestatErsteller().getVorname() == null || testatliste.get(rowIndex).getTestatErsteller().getNachname() == null) {
                    return "Fehler, Name des Dozenten ist Null";
                } else {
                    String vorname = testatliste.get(rowIndex).getTestatErsteller().getVorname();
                    String nachname = testatliste.get(rowIndex).getTestatErsteller().getNachname();
                    return vorname + " " + nachname;
                }
            }
            case 2: {
                if (testatliste.get(rowIndex).getGesamtzeit() == 0) {
                    return "Fehler, Gesamtzeit ist 0";
                } else {
                    return testatliste.get(rowIndex).getGesamtzeit() + " Min";
                }
            }
            case 3:
                return testatliste.get(rowIndex).getGesamtpunktzahl();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
