package com.ggasoftware.uitest.control.complex.table;

import com.ggasoftware.uitest.utils.map.MapArray;

import java.util.List;

/**
 * Created by roman.i on 20.10.2014.
 */

public interface ITable<P, T extends ClickableText> extends IHaveValue {
    Cell<P, T> cell(int colNum, int rowNum) throws Exception;
    Cell<P, T> cell(String colName, int rowNum) throws Exception;
    Cell<P, T> cell(int colNum, String rowName) throws Exception;
    Cell<P, T> cell(String colName, String rowName) throws Exception;
    List<Cell<P, T>> findCellsValues(String value) throws Exception;
    List<Cell<P, T>> matchCellsValues(String regex) throws Exception;
    Cell<P, T> findFirstCellWithValue(String value) throws Exception;

    Cell<P, T> findCellInColumn(int colIndex, String value) throws Exception;
    MapArray<String, Cell<P, T>> matchCellsInColumn(int colIndex, String regex) throws Exception;
    Cell<P, T> findCellInColumn(String colName, String value) throws Exception;
    MapArray<String, Cell<P, T>> matchCellsInColumn(String colname, String regex) throws Exception;

    Cell<P, T> findCellInRow(int rowIndex, String value) throws Exception;
    MapArray<String, Cell<P, T>> matchCellsInRow(int rowIndex, String regex) throws Exception;
    Cell<P, T> findCellInRow(String rowName, String value) throws Exception;
    MapArray<String, Cell<P, T>> matchCellsInRow(String rowName, String pattern) throws Exception;

    MapArray<String, Cell<P, T>> findColumnByRowValue(int rowIndex, String value) throws Exception;
    MapArray<String, Cell<P, T>> findColumnByRowValue(String rowName, String value) throws Exception;
    MapArray<String, Cell<P, T>> findRowByColumnValue(int colIndex, String value) throws Exception;
    MapArray<String, Cell<P, T>> findRowByColumnValue(String colName, String value) throws Exception;

    Columns<P, T> columns();
    MapArray<String, Cell<P, T>> getColumn(int colNum);
    MapArray<String, Cell<P, T>> getColumn(String colName);
    Rows<P, T> rows();
    MapArray<String, Cell<P, T>> getRow(int rowNum);
    MapArray<String, Cell<P, T>> getRow(String rowName);
    String[] getFooter();
}