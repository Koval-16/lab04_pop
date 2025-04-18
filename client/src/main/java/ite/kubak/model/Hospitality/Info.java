package ite.kubak.model.Hospitality;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    @JsonProperty("data")
    private AData data;

    public void setData(AData data) {
        this.data = data;
    }

    public AData getData() {
        return data;
    }
}
