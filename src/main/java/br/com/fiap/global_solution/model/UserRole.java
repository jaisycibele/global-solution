package br.com.fiap.global_solution.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {this.role = role;}

    public String getRole() {return role;}
}
