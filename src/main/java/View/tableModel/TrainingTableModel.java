package View.tableModel;

import entity.aufgabensammlung.Training;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TabelModel für die tabellarische Übersicht der bearbeiteten Trainings
 *
 * @author Jonas Herbst
 * @version 09.05.22
 */
public class TrainingTableModel extends AbstractTableModel {
    private List<Training> trainingsliste;
    private final String[] COLUMN_NAMES = {"Bearbeiter", "Gesamtzeit", "Gesamtpunktzahl", "Filter: Bearbeitungszeit", "Filter: Kategorie", "Filter: Schwierigkeitsgrad", "Filter: Aufgabentyp"};

    public TrainingTableModel(List<Training> trainingsliste) {
        this.trainingsliste = trainingsliste;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    @Override
    public int getRowCount() {
        return trainingsliste.size();
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

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}