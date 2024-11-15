package ite.kubak.model.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    private String related;

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }
}
