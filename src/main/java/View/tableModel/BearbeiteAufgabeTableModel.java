package View.tableModel;

import entity.aufgabe.Aufgabe;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TabelModel für die Tabelle, aus der einzelne Aufgaben zur Bearbeitung ausgewählt werden können
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class BearbeiteAufgabeTableModel extends AbstractTableModel {
    private List<Aufgabe> aufgabenliste;
    private final String[] COLUMN_NAMES = {"Name", "Kategorie", "Bearbeitungszeit", "Aufgabentyp", "Schwierigkeit", "Punkte"};

    public BearbeiteAufgabeTableModel(List<Aufgabe> aufgabenliste) {
        this.aufgabenliste = aufgabenliste;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    @Override
    public int getRowCount() {
        return aufgabenliste.size();
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
            case 3:
            case 4:
                return String.class;
            case 5:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                if(aufgabenliste.get(rowIndex).getName() == null) {
                    return "Fehler, Name ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getName();
                }
            }
            case 1: {
                if(aufgabenliste.get(rowIndex).getKategorie() == null) {
                    return "Fehler, Kategorie ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getKategorie().getCode();
                }
            }
            case 2: {
                if(aufgabenliste.get(rowIndex).getBearbeitungszeit() == 0) {
                    return "Fehler, Beabreitungszeit ist 0";
                } else {
                    return aufgabenliste.get(rowIndex).getBearbeitungszeit() + " Min";
                }
            }
            case 3: {
                if(aufgabenliste.get(rowIndex).getAufgabentyp() == null) {
                    return "Fehler, Aufgabentyp ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getAufgabentyp().getCode();
                }
            }
            case 4: {
                if(aufgabenliste.get(rowIndex).getSchwierigkeitsgrad() == null) {
                    return "Fehler, Schwierigkeitsgrad ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getSchwierigkeitsgrad().getCode();
                }
            }
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