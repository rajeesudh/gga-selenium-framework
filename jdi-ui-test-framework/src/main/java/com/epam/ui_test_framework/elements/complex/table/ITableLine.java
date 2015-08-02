package com.epam.ui_test_framework.elements.complex.table;

import com.epam.ui_test_framework.elements.base.SelectElement;
import com.epam.ui_test_framework.elements.interfaces.base.ISelect;
import com.epam.ui_test_framework.elements.page_objects.annotations.JDIAction;
import com.epam.ui_test_framework.utils.map.MapArray;

/**
 * Created by Roman_Iovlev on 7/28/2015.
 */
public interface ITableLine<T extends SelectElement> {
    /** Get Columns/Rows count*/
    @JDIAction
    int count();
    /** Get Columns/Rows headers*/
    @JDIAction
    String[] headers();
    /** Get Columns/Rows in following format <br>
     *  For rows: rowName>columnName>cell <br>
     *  For columns: columnName>rowName>cell <br>
     *  e.g. myTable.columns().get().get("Name").get("5")
     *  or   myTable.columns().get().count()
     *  But better to use specified commands like
     *      cell("Name, "5")
     *      myTable.columns().count()
     *  */
    @JDIAction
    MapArray<String, MapArray<String, ICell<T>>> get();
}