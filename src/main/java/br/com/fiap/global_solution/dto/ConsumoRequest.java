package br.com.fiap.global_solution.dto;

import br.com.fiap.global_solution.model.Aparelho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConsumoRequest {

    @NotNull
    private Aparelho aparelho;

    @NotNull
    private String data;

    @Positive
    @NotNull
    private Double consumoKwh;

    @Positive
    @NotNull
    private Double custoEstimado;

    public Aparelho getAparelho() {
        return aparelho;
    }

    public void setAparelho(Aparelho aparelho) {
        this.aparelho = aparelho;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(Double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public Double getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(Double custoEstimado) {
        this.custoEstimado = custoEstimado;
    }
}
