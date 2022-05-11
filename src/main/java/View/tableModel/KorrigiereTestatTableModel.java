package View.tableModel;

import entity.aufgabensammlung.TestatBearbeitung;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TableModel für die Tabelle in der Korrigieren-Ansicht der Testate
 *
 * @author Jonas Herbst
 * @version 06.05.22
 */
public class KorrigiereTestatTableModel extends AbstractTableModel {
    private List<TestatBearbeitung> testatbearbeitungsliste;
    private String[] columnNames = {"Name", "Nutzer", "Dozent", "Zeit", "Erreichte Punktzahl", "Korrigiert"};

    public KorrigiereTestatTableModel(List<TestatBearbeitung> testatbearbeitungsliste) {
        this.testatbearbeitungsliste = testatbearbeitungsliste;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return testatbearbeitungsliste.size();
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
            case 3:
            case 4:
            case 5:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return testatbearbeitungsliste.get(rowIndex).getTestat().getName();
            case 1: {
                String vorname = testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter().getVorname();
                String nachname = testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter().getNachname();
                return vorname + " " + nachname;
            }
            case 2: {
                String vorname = testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller().getVorname();
                String nachname = testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller().getNachname();
                return vorname + " " + nachname;
            }
            case 3:
                return testatbearbeitungsliste.get(rowIndex).getTestat().getGesamtzeit() + " Min";
            case 4: {
                if(testatbearbeitungsliste.get(rowIndex).isTestatBewertet()) {
                    return Integer.toString(testatbearbeitungsliste.get(rowIndex).getErreichtePunktzahl());
                } else {
                    return "Noch nicht korrigiert";
                }
            }
            case 5: {
                if(testatbearbeitungsliste.get(rowIndex).isTestatBewertet()) {
                    return "Ja";
                } else {
                    return "Nein";
                }
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
