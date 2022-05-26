package View.tableModel;

import entity.aufgabensammlung.Testat;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TableModel für die Katalog-Tabelle der Testate
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class TestatTableModel extends AbstractTableModel {

    private List<Testat> testatliste;
    private final String[] COLUMN_NAMES = {"Name", "Dozent", "Zeit", "Punktzahl"};

    /**
     * Konstruktor der Klasse AufgabenAuswaehlenAufgabensammlungTableModel
     *
     * @param testatliste Liste mit Testaten, die in der Tabelle dargestellt werden sollen
     */
    public TestatTableModel(List<Testat> testatliste) {
        this.testatliste = testatliste;
    }

    /**
     * Gibt den Namen der Spalte mit der übergebenen Nummer zurück
     *
     * @param col Nummer der Spalte, deren Name zurückgegeben werden soll
     * @return Namen der Spalte mit der übergebenen Nummer
     */
    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    /**
     * Gibt die Anzahl der Zeilen der Tabelle zurück
     *
     * @return Anzahl der Zeilen der Tabelle
     */
    @Override
    public int getRowCount() {
        return testatliste.size();
    }

    /**
     * Gibt die Anzahl der Spalten der Tabelle zurück
     *
     * @return Anzahl der Spalten der Tabelle
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * Gibt die Klasse der Spalte mit der übergebenen Nummer zurück
     *
     * @param col Nummer der Spalte deren Klasse zurückgegeben werden soll
     * @return Klasse der Spalte mit der übergebenen Nummer
     */
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

    /**
     * Gibt das Objekt in der Zelle mit der übergebenen Zeilennummer und Spaltennummer zurück
     *
     * @param rowIndex    Nummer der Zeile des zurückzugebenden Objekts
     * @param columnIndex Nummer der Spalte des zurückzugebenden Objekts
     * @return Objekt in der Zelle mit der übergebenen Zeilennummer und Spaltennummer
     */
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

    /**
     * Gibt zurück, ob die Zelle mit der übergebenen Zeilennummer und Spaltennummer editierbar ist
     *
     * @param row Nummer der Zeile der Zelle, für die zurückgegeben wird, ob sie editierbar ist
     * @param col Nummer der Spalte der Zelle, für die zurückgegeben wird, ob sie editierbar ist
     * @return Wahrheitswert, der angibt, ob die beschriebene Zelle editierbar ist
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}