package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Livro {
    private UUID id;
    private String titulo;
    private Autor autor;
    private boolean disponivel;
    private String genero;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public UUID getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getGenero() {
        return this.genero;
    }

    public String getAutorName() {
        return this.autor.getNome();
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public LocalDateTime getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Livro(String titulo, Autor autor, boolean disponivel, String genero, LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao) {
        this.id = UUID.randomUUID();
        this.titulo = titulo.toUpperCase();
        this.autor = autor;
        this.disponivel = disponivel;
        this.genero = genero.toUpperCase();
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;

    }
}
