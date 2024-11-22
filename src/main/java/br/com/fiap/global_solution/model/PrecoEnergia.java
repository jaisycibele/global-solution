package br.com.fiap.global_solution.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_preco_energia")
public class PrecoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrecoEnergia;

    @Column(name = "data")
    private String data;

    @Column(name = "preco_kwh")
    private Double precoKwh;

    public Long getIdPrecoEnergia() {
        return idPrecoEnergia;
    }

    public void setIdPrecoEnergia(Long idPrecoEnergia) {
        this.idPrecoEnergia = idPrecoEnergia;
    }

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
