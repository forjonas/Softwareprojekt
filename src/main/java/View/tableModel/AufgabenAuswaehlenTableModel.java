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
public class AufgabenAuswaehlenTableModel extends AbstractTableModel {
    private List<Aufgabe> aufgabenliste;
    private List<Boolean> auswahlliste;
    private String[] columnNames = {"Name", "Kategorie", "Bearbeitungszeit", "Aufgabentyp", "Schwierigkeit", "Punkte", "Ausgewählt"};

    public AufgabenAuswaehlenTableModel(List<Aufgabe> aufgabenliste) {
        this.aufgabenliste = aufgabenliste;
        this.auswahlliste = new LinkedList<Boolean>();
        for (Aufgabe a : aufgabenliste) {
            auswahlliste.add(false);
        }
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
            case 6:
                return Boolean.class;
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
