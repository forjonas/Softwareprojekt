package view.tableModel;

import entity.aufgabensammlung.Training;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TableModel für die tabellarische Übersicht der bearbeiteten Trainings
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class TrainingTableModel extends AbstractTableModel {

    private List<Training> trainingsliste;
    private final String[] COLUMN_NAMES = {"Bearbeiter", "Gesamtzeit", "Gesamtpunktzahl", "Filter: Bearbeitungszeit", "Filter: Kategorie", "Filter: Schwierigkeitsgrad", "Filter: Aufgabentyp"};

    /**
     * Konstruktor der Klasse TrainingTableModel
     *
     * @param trainingsliste Liste mit Trainings, die in der Tabelle dargestellt werden sollen
     */
    public TrainingTableModel(List<Training> trainingsliste) {
        this.trainingsliste = trainingsliste;
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
        return trainingsliste.size();
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
            case 3:
            case 4:
            case 5:
            case 6:
                return String.class;
            case 2:
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
                if (trainingsliste.get(rowIndex).getTrainingsErsteller() == null) {
                    return "Fehler, kein Trainingsersteller";
                } else if (trainingsliste.get(rowIndex).getTrainingsErsteller().getVorname() == null || trainingsliste.get(rowIndex).getTrainingsErsteller().getNachname() == null) {
                    return "Fehler, Name des Trainingserstellers ist Null";
                } else {
                    String vorname = trainingsliste.get(rowIndex).getTrainingsErsteller().getVorname();
                    String nachname = trainingsliste.get(rowIndex).getTrainingsErsteller().getNachname();
                    return vorname + " " + nachname;
                }
            }
            case 1:
                return trainingsliste.get(rowIndex).getGesamtzeit() + "min";
            case 2:
                return trainingsliste.get(rowIndex).getGesamtpunktzahl();
            case 3: {
                if (trainingsliste.get(rowIndex).getBearbeitungszeit() == 0) {
                    return "Nicht gefiltert";
                } else {
                    return trainingsliste.get(rowIndex).getBearbeitungszeit() + " min";
                }
            }
            case 4: {
                if (trainingsliste.get(rowIndex).getKategorie() == null) {
                    return "Nicht gefiltert";
                } else {
                    return trainingsliste.get(rowIndex).getKategorie().getCode();
                }
            }
            case 5: {
                if (trainingsliste.get(rowIndex).getSchwierigkeitsgrad() == null) {
                    return "Nicht gefiltert";
                } else {
                    return trainingsliste.get(rowIndex).getSchwierigkeitsgrad().getCode();
                }
            }
            case 6: {
                if (trainingsliste.get(rowIndex).getAufgabentypen() != null && trainingsliste.get(rowIndex).getAufgabentypen().size() > 0) {
                    String aufgabentypen = trainingsliste.get(rowIndex).getAufgabentypen().get(0).getCode();
                    for (int i = 1; i < trainingsliste.get(rowIndex).getAufgabentypen().size(); i++) {
                        aufgabentypen += ", " + trainingsliste.get(rowIndex).getAufgabentypen().get(i).getCode();
                    }
                    return aufgabentypen;
                } else {
                    return "Fehler, kein Aufgabentyp gewählt";
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