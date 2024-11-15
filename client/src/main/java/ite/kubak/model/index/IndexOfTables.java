package ite.kubak.model.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndexOfTables {
    private Data data;

    public IndexOfTables(){}

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
