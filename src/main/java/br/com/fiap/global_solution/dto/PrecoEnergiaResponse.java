package br.com.fiap.global_solution.dto;

import org.springframework.hateoas.Link;

public class PrecoEnergiaResponse {

    private Long idPrecoEnergia;
    private String data;
    private Double precoKwh;
    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

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
