package ite.kubak.model.benefit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ite.kubak.model.benefit.Benefit;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BenefitResponse {
    private List<Benefit> data;
    private Page links;

    public BenefitResponse(){
    }

    public List<Benefit> getData(){
        return data;
    }

    public void setData(List<Benefit> data) {
        this.data = data;
    }

    public Page getLinks() {
        return links;
    }

    public void setPage(Page links) {
        this.links = links;
    }

}
