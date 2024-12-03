package com.example.newcanting3375.api;

import com.example.newcanting3375.model.TurvarData;
import com.example.newcanting3375.model.VarData;
import com.example.newcanting3375.model.VervarData;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ApiResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data-availability")
    private String dataAvailability;
    @SerializedName("var")
    private List<VarData> var;
    @SerializedName("turvar")
    private List<TurvarData> turvar;
    @SerializedName("vervar")
    private List<VervarData> vervar;
    @SerializedName("datacontent")
    private Map<String, Double> datacontent;

    // Getters and Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataAvailability() {
        return dataAvailability;
    }

    public void setDataAvailability(String dataAvailability) {
        this.dataAvailability = dataAvailability;
    }

    public List<VarData> getVar() {
        return var;
    }

    public List<TurvarData> getTurvar() {
        return turvar;
    }

    public List<VervarData> getVervar() {
        return vervar;
    }

    public Map<String, Double> getDatacontent() {
        return datacontent;
    }
}