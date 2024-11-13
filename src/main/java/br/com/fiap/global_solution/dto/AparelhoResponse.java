package br.com.fiap.global_solution.dto;

import br.com.fiap.global_solution.model.Usuario;
import org.springframework.hateoas.Link;

public class AparelhoResponse {

    private Long idAparelho;
    private String nome;
    private Long potencia;
    private String tipo;
    private Usuario usuario;
    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Long getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(Long idAparelho) {
        this.idAparelho = idAparelho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPotencia() {
        return potencia;
    }

    public void setPotencia(Long potencia) {
        this.potencia = potencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
