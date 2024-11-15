package ite.kubak.model.Hospitality;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Info {
    @JsonProperty("data.attributes.data")
    private List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
