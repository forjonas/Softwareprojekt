package tableModel;

import entity.Testat;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * TabelModel für die Tabelle in der Bearbeiten-Ansicht der Testate
 *
 * @author Jonas Herbst
 * @version 06.05.22
 */
public class BearbeiteTestatTableModel extends AbstractTableModel {
    private List<Testat> testatliste;
    private String[] columnNames = {"Name", "Dozent", "Zeit", "Maximalpunktzahl", "Bearbeitet"};

    public BearbeiteTestatTableModel(List<Testat> testatliste) {
        this.testatliste = testatliste;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return testatliste.size();
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
            case 2:
            case 4:
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
            case 0:
                return testatliste.get(rowIndex).getName();
            case 1:
                return "Dozentenbeziehung noch nicht implementiert";
            case 2:
                return testatliste.get(rowIndex).getGesamtzeit() + " Min";
            case 3:
                return testatliste.get(rowIndex).getGesamtpunktzahl();
            case 4: {
                return "Feature unimplementiert"; //Als Checkbox oder Wort gewünscht?
/*                if (testatliste.get(rowIndex).isBearbeitet()) {
                    return "Ja";
                } else {
                    return "Nein";
                }*/
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
