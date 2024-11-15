package ite.kubak.model.Hospitality;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Element {
    @JsonAlias({"type-of-discharge-code,age-group-code,gender-code,type-of-admission-code,procedure-code,disease-code"})
    private int code;
    @JsonAlias({"type-of-discharge-name,age-group-name,gender-name,type-of-admission-name,procedure-name,disease-name"})
    private String name;
    @JsonProperty("number-of-hospitalizations")
    private int hosp_number;
    @JsonProperty("percentage")
    private double hosp_percentage;
    @JsonProperty("duration-of-hospitalization-mediana")
    private int mediana;

    public String getName() {
        return name;
    }

    public double getHosp_percentage() {
        return hosp_percentage;
    }

    public int getCode() {
        return code;
    }

    public int getHosp_number() {
        return hosp_number;
    }

    public int getMediana() {
        return mediana;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setHosp_number(int hosp_number) {
        this.hosp_number = hosp_number;
    }

    public void setHosp_percentage(double hosp_percentage) {
        this.hosp_percentage = hosp_percentage;
    }

    public void setMediana(int mediana) {
        this.mediana = mediana;
    }

    public void setName(String name) {
        this.name = name;
    }
}
