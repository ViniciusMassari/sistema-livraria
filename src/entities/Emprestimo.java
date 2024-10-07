package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Cliente recebedor;
    private Livro livro;
    private final LocalDateTime dataEmprestimo;
    private EmprestimoStatus emprestimoStatus;

    public Emprestimo(Cliente recebedor, Livro livro, LocalDateTime dataEmprestimo, EmprestimoStatus emprestimoStatus) {
        this.recebedor = recebedor;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.emprestimoStatus = emprestimoStatus;
    }

    public String getRecebedorNome() {
        return this.recebedor.getNome().toUpperCase();
    }

    public void setRecebedor(Cliente recebedor) {
        this.recebedor = recebedor;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public String getLivroTitulo() {
        return this.livro.getTitulo();
    }

    public String getDataEmprestimoFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.dataEmprestimo.format(formatador);
    }

    public EmprestimoStatus getEmprestimoStatus() {
        return this.emprestimoStatus;
    }

    public void setEmprestimoStatus(EmprestimoStatus emprestimoStatus) {
        this.emprestimoStatus = emprestimoStatus;
    }
}
