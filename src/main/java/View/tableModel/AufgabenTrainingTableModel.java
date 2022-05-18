package View.tableModel;

import entity.aufgabe.Aufgabe;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * TabelModel für die Tabelle der Aufgaben bei der Auswahl von Aufgaben für ein Testat
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class AufgabenTrainingTableModel extends AbstractTableModel {
    private List<Aufgabe> aufgabenliste;
    private List<Boolean> auswahlliste;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    private Kategorie kategorie;
    private final String[] COLUMN_NAMES = {"Name", "Kategorie", "Bearbeitungszeit", "Aufgabentyp", "Schwierigkeit", "Punkte", "Ausgewählt"};

    public AufgabenTrainingTableModel(List<Aufgabe> aufgabenliste) {
        this.aufgabenliste = aufgabenliste;
        this.auswahlliste = new LinkedList<Boolean>();
        for (Aufgabe a : aufgabenliste) {
            auswahlliste.add(false);
        }
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
            case 6:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                if (aufgabenliste.get(rowIndex).getName() == null) {
                    return "Fehler, Name ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getName();
                }
            }

            case 1: {
                if (aufgabenliste.get(rowIndex).getKategorie() == null) {
                    return "Fehler, Kategorie ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getKategorie().getCode();
                }
            }

            case 2: {
                if (aufgabenliste.get(rowIndex).getBearbeitungszeit() == 0) {
                    return "Fehler, Beabreitungszeit ist 0";
                } else {
                    return aufgabenliste.get(rowIndex).getBearbeitungszeit() + " Min";
                }
            }
            case 3: {
                if (aufgabenliste.get(rowIndex).getAufgabentyp() == null) {
                    return "Fehler, Aufgabentyp ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getAufgabentyp().getCode();
                }
            }
            case 4: {
                if (aufgabenliste.get(rowIndex).getSchwierigkeitsgrad() == null) {
                    return "Fehler, Schwierigkeitsgrad ist Null";
                } else {
                    return aufgabenliste.get(rowIndex).getSchwierigkeitsgrad().getCode();
                }
            }
            case 5:
                return aufgabenliste.get(rowIndex).getPunktewert();
            case 6:
                return auswahlliste.get(rowIndex);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        switch (col) {
            case 6:
                auswahlliste.set(row, (Boolean) value);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 6:
                return true;
            default:
                return false;
        }
    }
}
