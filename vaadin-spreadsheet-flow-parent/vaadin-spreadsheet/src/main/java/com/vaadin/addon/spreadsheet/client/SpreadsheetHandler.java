package com.vaadin.addon.spreadsheet.client;

/*
 * #%L
 * Vaadin Spreadsheet
 * %%
 * Copyright (C) 2013 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import java.util.HashMap;
import java.util.Map;

public interface SpreadsheetHandler {

    /**
     * These cells have become visible and possibly need the content, if has not
     * been given previously or has not changed.
     */
    public void onSheetScroll(int firstRow, int firstColumn, int lastRow,
            int lastColumn);

    /** Address field value changed. */
    public void sheetAddressChanged(String value);

    /** Single cell selected inside sheet. */
    public void cellSelected(int row, int column,
            boolean oldSelectionRangeDiscarded);

    /** Cell range selected from scratch. Actual selected cell not changed. */
    public void cellRangeSelected(int row1, int col1, int row2, int col2);

    /** Single cell added to selection. Selection changed to this. */
    public void cellAddedToSelectionAndSelected(int row, int column);

    /**
     * Multiple cells added to previous range selection. Actual selected cell
     * not changed.
     */
    public void cellsAddedToRangeSelection(int row1, int col1, int row2,
            int col2);

    /**
     * Complete row selected. New selected cell is at firstColumnIndex:row.
     * 
     * @param row
     *            the row that was selected
     * @param firstColumnIndex
     *            column index for the selected cell (left most visible)
     */
    public void rowSelected(int row, int firstColumnIndex);

    /**
     * Complete row added to previous range selection. New selected cell is at
     * firstColumnIndex:row.
     * 
     * @param row
     *            the row that was selected
     * @param firstColumnIndex
     *            column index for the selected cell (left most visible)
     */
    public void rowAddedToRangeSelection(int row, int firstColumnIndex);

    /**
     * Complete column selected. New selected cell is at column:firstRowIndex.
     * 
     * @param column
     *            the column that was selected
     * @param firstRowIndex
     *            row index for the selected cell (top most visible)
     */
    public void columnSelected(int column, int firstRowIndex);

    /**
     * Complete column added to previous range selection. New selected cell is
     * at column:firstRowIndex.
     * @param firstRowIndex
     *            row index for the selected cell (top most)
     * @param column
     *            the column that was selected
     */
    public void columnAddedToSelection(int firstRowIndex, int column);

    /**
     * The new selection that was painted from the old. Values and formulas
     * should be painted to the new selection.
     * @param r1
     *            new selection top, 1-based
     * @param c1
     *            new selection left, 1-based
     * @param r2
     *            new selection bottom, 1-based
     * @param c2
     *            new selection right, 1-based
     */
    public void selectionIncreasePainted(int r1, int c1, int r2, int c2);

    /**
     * The existing selection has been painted inwards meaning that the painted
     * selection cells should be cleared.
     * @param row
     *            topmost cell index where the clearing starts, 1-based
     * @param col
     *            leftmost cell index where the clearing starts, 1-based
     */
    public void selectionDecreasePainted(int row, int col);

    public void cellValueEdited(int row, int col, String value);

    /**
     * 
     * @param sheetIndex
     *            0-based
     * @param scrollTop
     * @param scrollLeft
     */
    public void sheetSelected(int sheetIndex, int scrollLeft, int scrollTop);

    /**
     * 
     * @param sheetIndex
     *            0-based
     * @param newName
     */
    public void sheetRenamed(int sheetIndex, String newName);

    /**
     * Sheet is created as the last sheet
     * 
     * @param scrollTop
     * @param scrollLeft
     */
    public void sheetCreated(int scrollLeft, int scrollTop);

    /**
     * Cell range selected by painting
     * @param selectedCellRow
     * @param selectedCellColumn
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    public void cellRangePainted(int selectedCellRow, int selectedCellColumn,
            int row1, int col1, int row2, int col2);

    /**
     * Delete the contents of the selected cells, do not remove
     * style/formatting.
     */
    public void deleteSelectedCells();

    /**
     * A cell containing a hyperlink has been clicked.
     * @param row
     *            1-based
     * @param column
     *            1-based
     */
    public void linkCellClicked(int row, int column);

    /**
     * Rows resized with header drag and drop. Indexes 1-based.
     * 
     * @param newRowSizes
     *            row index and new size (converted pt)
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    public void rowsResized(Map<Integer, Float> newRowSizes, int row1,
            int col1, int row2, int col2);

    /**
     * Columns resized with drag and drop. Indexes 1-based.
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param newRowSizes
     *            column index and new size (px)
     */
    public void columnResized(Map<Integer, Integer> newColumnSizes, int row1,
            int col1, int row2, int col2);

    /**
     * Column autofit with double click on the column header resizing area.
     * 
     * @param columnIndex
     *            1-based
     */
    public void onColumnAutofit(int columnIndex);

    /**
     * Client pressed undo ctrl/meta+z
     */
    public void onUndo();

    /**
     * Client pressed redo ctrl/meta+y
     */
    public void onRedo();

    public void setCellStyleWidthRatios(
            HashMap<Integer, Float> cellStyleWidthRatioMap);

    /**
     * Client tried to modify protected cell
     */
    public void protectedCellWriteAttempted();

    /**
     * Client pasted text at current selection.
     * 
     * @param text
     */
    public void onPaste(String text);

    /**
     * Called after successful cut operation; currently selected cells should be
     * cleared
     */
    public void clearSelectedCellsOnCut();
}
