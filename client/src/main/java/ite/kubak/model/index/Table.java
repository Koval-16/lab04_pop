package ite.kubak.model.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {
    private String id;
    private String type;
    private Attr attributes;
    @JsonProperty("links")
    private Link links;

    public Attr getAttributes() {
        return attributes;
    }

    public String getId() {
        return id;
    }

    public void setAttributes(Attr attributes) {
        this.attributes = attributes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Link getLinks() {
        return links;
    }

    public void setLink(Link links) {
        this.links = links;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
