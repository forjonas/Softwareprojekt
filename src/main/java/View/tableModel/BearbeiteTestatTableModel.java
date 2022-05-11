package View.tableModel;

import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TabelModel für die Tabelle in der Bearbeiten-Ansicht der Testate
 *
 * @author Jonas Herbst
 * @version 06.05.22
 */
public class BearbeiteTestatTableModel extends AbstractTableModel {
    private List<Testat> testatliste;
    private String[] columnNames = {"Name", "Dozent", "Zeit", "Maximalpunktzahl", "Bearbeitet"};
    private Benutzer user;

    public BearbeiteTestatTableModel(List<Testat> testatliste, Benutzer user) {
        this.testatliste = testatliste;
        this.user = user;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return testatliste.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1:
            case 2:
            case 4:
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
            case 0:
                return testatliste.get(rowIndex).getName();
            case 1: {
                String vorname = testatliste.get(rowIndex).getTestatErsteller().getVorname();
                String nachname = testatliste.get(rowIndex).getTestatErsteller().getNachname();
                return vorname + " " + nachname;
            }
            case 2:
                return testatliste.get(rowIndex).getGesamtzeit() + " Min";
            case 3:
                return testatliste.get(rowIndex).getGesamtpunktzahl();
            case 4: {
                List<TestatBearbeitung> bearbeitungen = testatliste.get(rowIndex).getBearbeitungen();
                for(TestatBearbeitung t: bearbeitungen) {
                    if(t.getTestatBearbeiter() == user) {
                        return "Ja";
                    }
                }
                return "Nein";
            }
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
