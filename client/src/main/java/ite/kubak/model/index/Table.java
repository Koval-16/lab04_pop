package ite.kubak.model.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {
    private String id;
    private Attr attributes;
    private Link link;

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

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
