package com.ggasoftware.jdi_ui_tests.implementation.selenium.elements.complex.table;

import com.ggasoftware.jdi_ui_tests.implementation.selenium.elements.base.SelectElement;
import com.ggasoftware.jdi_ui_tests.implementation.selenium.elements.interfaces.common.IText;
import com.ggasoftware.jdi_ui_tests.core.utils.common.LinqUtils;
import com.ggasoftware.jdi_ui_tests.core.utils.map.MapArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import static com.ggasoftware.jdi_ui_tests.core.settings.JDISettings.asserter;
import static java.lang.String.format;

/**
 * Created by 12345 on 26.10.2014.
 */
class Rows<T extends SelectElement> extends TableLine<T> {
    public Rows() {
        haveHeader = false;
        elementIndex = ElementIndexType.Nums;
    }

    protected String[] getHeadersAction() {
        return LinqUtils.select(table.getWebElement().findElements(By.xpath(".//tr/td[1]")), WebElement::getText)
                .toArray(new String[1]);
    }

    private RuntimeException throwRowsException(String rowName, Exception ex) {
        return asserter.exception(format("Can't Get Rows '%s'. Exception: %s", rowName, ex));
    }

    public final MapArray<String, ICell<T>> getColumn(String colName) {
        try { return cellsToColumn(LinqUtils.select(headers(), rowName -> table.cell(new Column(colName), new Row(rowName)))); }
        catch (Exception ex) { throw throwRowsException(colName, ex); }
    }
    public final MapArray<String, String> getColumnAsText(String colName) {
        return getColumn(colName).toMapArray(IText::getText);
    }

    public MapArray<String, ICell<T>> cellsToColumn(Collection<ICell<T>> cells) {
        return new MapArray<>(cells,
                cell -> headers()[cell.rowNum() - 1],
                cell -> cell);
    }

    public final MapArray<String, ICell<T>> getColumn(int colNum) {
        int rowsCount = -1;
        if (count > 0)
            rowsCount = count;
        else if (headers != null && (headers.length > 0))
            rowsCount = headers.length;
        if (rowsCount == -1)
            rowsCount = headers().length;
        if (rowsCount > 0 && rowsCount < colNum)
            throw asserter.exception(format("Can't Get Row '%s'. [num] > RowsCount(%s).", colNum, rowsCount));
        try {
            return new MapArray<>(count(),
                    rowNum -> headers()[rowNum],
                    rowNum -> table.cell(new Column(colNum), new Row(rowNum+1)));
        }
        catch (Exception ex) { throw throwRowsException(colNum + "", ex); }
    }
    public final MapArray<String, String> getColumnAsText(int colNum) {
        return getColumn(colNum).toMapArray(IText::getText);
    }

    public MapArray<String, MapArray<String, ICell<T>>> get() {
        MapArray<String, MapArray<String, ICell<T>>> rows = new MapArray<>();
        for(String rowName : headers()) {
            MapArray<String, ICell<T>> column = new MapArray<>();
            for (String columnName : table.columns().headers())
                column.add(columnName, table.cell(new Column(columnName), new Row(rowName)));
            rows.add(rowName, column);
        }
        return rows;
    }
}
