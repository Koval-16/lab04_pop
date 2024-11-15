package ite.kubak.model.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {
    @JsonProperty("product-code")
    private String productCode;
    @JsonProperty("product-name")
    private String productName;
    private List<Year> years;

    public String getProductCode() {
        return productCode;
    }

    public List<Year> getYears() {
        return years;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }
}
