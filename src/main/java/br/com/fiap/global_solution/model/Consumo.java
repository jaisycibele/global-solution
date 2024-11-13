package br.com.fiap.global_solution.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsumo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aparelho_id", referencedColumnName = "idAparelho", foreignKey = @ForeignKey(name = "FK_CONSUMO_APARELHO"))
    private Aparelho aparelho;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "consumo_kwh", nullable = false)
    private Double consumoKwh;

    @Column(name = "custo_estimado", nullable = false)
    private Double custoEstimado;

    public Long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

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