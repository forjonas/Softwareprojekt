package View.tableModel;

import entity.aufgabensammlung.Training;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;

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
    private String[] columnNames = {"Nutzer", "Gesamtzeit", "Gesamtpunktzahl", "Filter: Bearbeitungszeit", "Filter: Kategorie", "Filter: Schwierigkeitsgrad", "Filter: Aufgabentyp"};

    public TrainingTableModel(List<Training> trainingsliste) {
        this.trainingsliste = trainingsliste;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return trainingsliste.size();
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
            case 3:
                return String.class;
            case 2:
                return Integer.class;
            case 4:
                return Kategorie.class;
            case 5:
                return Schwierigkeitsgrad.class;
            case 6:
                return Aufgabentyp.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                String vorname = trainingsliste.get(rowIndex).getTrainingsErsteller().getVorname();
                String nachname = trainingsliste.get(rowIndex).getTrainingsErsteller().getNachname();
                return vorname + " " + nachname;
            }
            case 1:
                return trainingsliste.get(rowIndex).getGesamtzeit();
            case 2:
                return trainingsliste.get(rowIndex).getGesamtpunktzahl();
            case 3:
                return trainingsliste.get(rowIndex).getBearbeitungszeit();
            case 4:
                return trainingsliste.get(rowIndex).getKategorie();
            case 5:
                return trainingsliste.get(rowIndex).getSchwierigkeitsgrad();
            case 6:
                //return trainingsliste.get(rowIndex).getAufgabentyp();
                return null;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
