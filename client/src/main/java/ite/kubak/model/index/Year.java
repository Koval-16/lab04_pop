package ite.kubak.model.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Year {
    private int year;
    private List<Table> tables;

    public int getYear() {
        return year;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
