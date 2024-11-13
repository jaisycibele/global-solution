package br.com.fiap.global_solution.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PrecoEnergiaRequest {

    @NotNull
    private String data;

    @Positive
    @NotNull
    private Double precoKwh;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getPrecoKwh() {
        return precoKwh;
    }

    public void setPrecoKwh(Double precoKwh) {
        this.precoKwh = precoKwh;
    }
}
