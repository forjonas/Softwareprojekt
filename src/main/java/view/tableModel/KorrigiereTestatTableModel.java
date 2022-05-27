package view.tableModel;

import entity.aufgabensammlung.TestatBearbeitung;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TableModel für die Tabelle in der Korrigieren-Ansicht der Testate
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class KorrigiereTestatTableModel extends AbstractTableModel {

    private List<TestatBearbeitung> testatbearbeitungsliste;
    private final String[] COLUMN_NAMES = {"Name", "Bearbeiter", "Dozent", "Zeit", "Erreichte Punktzahl", "Korrigiert"};

    /**
     * Konstruktor der Klasse KorrigiereTestatTableModel
     *
     * @param testatbearbeitungsliste Liste mit Testatbearbeitungen, die in der Tabelle dargestellt werden sollen
     */
    public KorrigiereTestatTableModel(List<TestatBearbeitung> testatbearbeitungsliste) {
        this.testatbearbeitungsliste = testatbearbeitungsliste;
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
        return testatbearbeitungsliste.size();
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
            case 3:
            case 4:
            case 5:
                return String.class;
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
                if (testatbearbeitungsliste.get(rowIndex).getTestat() == null) {
                    return "Fehler, Testat ist Null";
                } else if (testatbearbeitungsliste.get(rowIndex).getTestat().getName() == null) {
                    return "Fehler, Name des Testats ist Null";
                } else {
                    return testatbearbeitungsliste.get(rowIndex).getTestat().getName();
                }
            }
            case 1: {
                if (testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter() == null) {
                    return "Fehler, Testatbearbeiter ist Null";
                } else if (testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter().getVorname() == null || testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter().getNachname() == null) {
                    return "Fehler, Name des Testatbearbeiters ist Null";
                } else {
                    String vorname = testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter().getVorname();
                    String nachname = testatbearbeitungsliste.get(rowIndex).getTestatBearbeiter().getNachname();
                    return vorname + " " + nachname;
                }
            }
            case 2: {
                if (testatbearbeitungsliste.get(rowIndex).getTestat() == null) {
                    return "Fehler, Testat ist Null";
                } else if (testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller() == null) {
                    return "Fehler, Dozent ist Null";
                } else if (testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller().getVorname() == null || testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller().getNachname() == null) {
                    return "Fehler, Name des Dozenten ist Null";
                } else {
                    String vorname = testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller().getVorname();
                    String nachname = testatbearbeitungsliste.get(rowIndex).getTestat().getTestatErsteller().getNachname();
                    return vorname + " " + nachname;
                }
            }
            case 3: {
                if (testatbearbeitungsliste.get(rowIndex).getTestat() == null) {
                    return "Fehler, Testat ist Null";
                } else if (testatbearbeitungsliste.get(rowIndex).getTestat().getGesamtzeit() == 0) {
                    return "Fehler, Bearbeitungszeit ist 0";
                } else {
                    return testatbearbeitungsliste.get(rowIndex).getTestat().getGesamtzeit() + " Min";
                }
            }
            case 4: {
                if (testatbearbeitungsliste.get(rowIndex).isTestatBewertet()) {
                    return Integer.toString(testatbearbeitungsliste.get(rowIndex).getErreichtePunktzahl());
                } else {
                    return "Noch nicht korrigiert";
                }
            }
            case 5: {
                if (testatbearbeitungsliste.get(rowIndex).isTestatBewertet()) {
                    return "Ja";
                } else {
                    return "Nein";
                }
            }
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