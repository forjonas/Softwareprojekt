package View.tableModel;

import entity.aufgabe.Aufgabe;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TableModel für die Tabelle für die Administration der Aufgaben
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class AufgabeTableModel extends AbstractTableModel {

    private List<Aufgabe> aufgabenliste;
    private final String[] COLUMN_NAMES = {"Name", "Kategorie", "Bearbeitungszeit", "Aufgabentyp", "Schwierigkeit", "Punkte", "Ersteller"};

    /**
     * Konstruktor der Klasse AufgabeTableModel
     *
     * @param aufgabenliste Liste mit Aufgaben, die in der Tabelle dargestellt werden sollen
     */
    public AufgabeTableModel(List<Aufgabe> aufgabenliste) {
        this.aufgabenliste = aufgabenliste;
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
        return aufgabenliste.size();
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
            case 6:
                return String.class;
            case 5:
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
                if(aufgabenliste.get(rowIndex).getBearbeitungszeit() == 0) {
                    return "Fehler, Bearbeitungszeit ist 0";
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
            case 6: {
                if (aufgabenliste.get(rowIndex).getAufgabenErsteller() == null) {
                    return "Fehler, kein Aufgabenersteller";
                } else if (aufgabenliste.get(rowIndex).getAufgabenErsteller().getVorname() == null || aufgabenliste.get(rowIndex).getAufgabenErsteller().getNachname() == null) {
                    return "Fehler, Name des Aufgabenersteller ist Null";
                } else {
                    String vorname = aufgabenliste.get(rowIndex).getAufgabenErsteller().getVorname();
                    String nachname = aufgabenliste.get(rowIndex).getAufgabenErsteller().getNachname();
                    return vorname + " " + nachname;
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