/**
 * 
 */
package org.toilelibre.libe.userinteractions.util;

import javax.swing.table.DefaultTableModel;

/**
 * @author lionel
 * 
 */
public final class TableModel {

    private final DefaultTableModel dtm;

    public TableModel() {
        this.dtm = new DefaultTableModel ();
    }

    /**
     * @param model1
     */
    public TableModel(final Object model1) {
        this.dtm = (DefaultTableModel) model1;
    }

    /**
     * @param columnName1
     * @see DefaultTableModel#addColumn(java.lang.Object)
     */
    public void addColumn (final Object columnName1) {
        this.dtm.addColumn (columnName1);
    }

    /**
     * @param rowData1
     * @see DefaultTableModel#addRow(java.lang.Object[])
     */
    public void addRow () {
        this.dtm.addRow (new Object [0]);
    }

    /**
     * @return the dtm
     */
    public Object getImpl () {
        return this.dtm;
    }

    /**
     * @param row1
     * @param column1
     * @return
     * @seeDefaultTableModel#getValueAt(int, int)
     */
    public Object getValueAt (final int row1, final int column1) {
        return this.dtm.getValueAt (row1, column1);
    }

    /**
     * @param rowCount1
     * @see DefaultTableModel#setRowCount(int)
     */
    public void setRowCount (final int rowCount1) {
        this.dtm.setRowCount (rowCount1);
    }

    /**
     * @param value1
     * @param row1
     * @param column1
     * @see DefaultTableModel# setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt (final Object value1, final int row1,
            final int column1) {
        int row = row1;
        if (row == -1) {
            row = this.dtm.getRowCount () - 1;
        }
        this.dtm.setValueAt (value1, row, column1);
    }

}
