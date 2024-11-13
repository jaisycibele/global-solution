package br.com.fiap.global_solution.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Size(min = 2, max = 255)
    @Column(name = "email")
    private String email;

    @NotBlank(message = "A senha deve ter no minimo 8 caracteres")
    @Size(min = 8, max = 100)
    @Column(name = "senha")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
