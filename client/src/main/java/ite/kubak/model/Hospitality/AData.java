package ite.kubak.model.Hospitality;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AData {
    @JsonProperty("attributes")
    private Attribs attributes;

    public void setAttributes(Attribs attributes) {
        this.attributes = attributes;
    }

    public Attribs getAttributes() {
        return attributes;
    }
}
