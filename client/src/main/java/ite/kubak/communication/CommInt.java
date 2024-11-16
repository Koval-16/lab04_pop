package ite.kubak.communication;

import ite.kubak.model.Hospitality.Info;
import ite.kubak.model.benefit.Benefit;
import ite.kubak.model.index.IndexOfTables;

import java.util.List;

public interface CommInt {
    List<Benefit> get_benefits(String URL, String query);

    IndexOfTables get_index(String URL, String name);

    Info get_info(String URL);
}
