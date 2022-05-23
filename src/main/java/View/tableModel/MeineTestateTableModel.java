package View.tableModel;

import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TabelModel für die Tabelle in der Bearbeiten-Ansicht der Testate
 *
 * @author Jonas Herbst
 * @version 06.05.22
 */
public class MeineTestateTableModel extends AbstractTableModel {
    private List<Testat> testatliste;
    private final String[] COLUMN_NAMES = {"Name", "Dozent", "Zeit", "Maximalpunktzahl", "Bearbeitet", "Bewertung"};
    private Benutzer user;

    public MeineTestateTableModel(List<Testat> testatliste, Benutzer user) {
        this.testatliste = testatliste;
        this.user = user;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    @Override
    public int getRowCount() {
        return testatliste.size();
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
            case 4:
            case 5:
                return String.class;
            case 3:
                return Integer.class;
            default:
                return null;
        }
    }

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
            case 3: {
                if (testatliste.get(rowIndex).getGesamtpunktzahl() == 0) {
                    return "Fehler, Gesamtpunktzahl ist 0";
                } else {
                    return testatliste.get(rowIndex).getGesamtpunktzahl();
                }
            }
            case 4: {
                if (testatliste.get(rowIndex).getBearbeitungen() == null) {
                    return "Fehler, Bearbeitungen sind Null";
                } else {
                    if (testatliste.get(rowIndex).isTestatVonUserBearbeitetWorden(user)) {
                        return "Ja";
                    } else {
                        return "Nein";
                    }
                }
            }
            case 5: {
                if (testatliste.get(rowIndex).getBearbeitungen() == null) {
                    return "Fehler, Bearbeitungen sind Null";
                } else {
                    TestatBearbeitung testatBearbeitung = testatliste.get(rowIndex).getBearbeitungVonBenutzer(user);
                    if (testatBearbeitung == null) {
                        return "Noch nicht bearbeitet";
                    } else if (testatBearbeitung.getTestatBewerter() == null) {
                        return "Noch nicht bewertet";
                    } else {
                        return testatBearbeitung.getErreichtePunktzahl() + " Punkte";
                    }
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