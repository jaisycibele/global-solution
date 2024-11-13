package br.com.fiap.global_solution.dto;

import br.com.fiap.global_solution.model.UserRole;

public record RegisterDTO(String login, String senha, UserRole role) {}
