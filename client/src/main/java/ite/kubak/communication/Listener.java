package ite.kubak.communication;

import ite.kubak.model.Hospitality.Element;
import ite.kubak.model.benefit.Benefit;
import ite.kubak.model.index.IndexOfTables;

import java.util.List;

public interface Listener {



    List<String> get_benefits(String query);

    List<Integer> get_index(String name);

    List<String> get_tables(int year);

    List<Element> get_chart(int year, String table);

}
