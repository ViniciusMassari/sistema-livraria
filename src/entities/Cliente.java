package entities;

import java.time.LocalDate;
import java.util.UUID;

public class Cliente {
    private UUID id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Cliente(String nome, String email, LocalDate dataNascimento) {
        this.id = UUID.randomUUID();
        this.nome = nome.toUpperCase();
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

}
