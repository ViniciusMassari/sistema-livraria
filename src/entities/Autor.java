package entities;

import java.util.UUID;

public class Autor {
    private UUID id;
    private String nome;

    public UUID getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Autor(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome.toUpperCase();
    }
}
