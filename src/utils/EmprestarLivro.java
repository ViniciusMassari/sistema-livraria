package utils;

import entities.Cliente;
import entities.Emprestimo;
import entities.EmprestimoStatus;
import entities.Livro;
import java.time.LocalDateTime;
import java.util.*;

public class EmprestarLivro {
    public static Emprestimo emprestar(Livro livro, Cliente cliente) {
        var dataHoje = LocalDateTime.now();
        Emprestimo novoEmprestimo = new Emprestimo(cliente, livro, dataHoje, EmprestimoStatus.EMPRESTADO);
        livro.setDisponivel(false);
        return novoEmprestimo;
    }

    public static void devolver(Emprestimo emprestimo) {
        emprestimo.setEmprestimoStatus(EmprestimoStatus.DEVOLVIDO);
        emprestimo.getLivro().setDisponivel(true);
    }

    public static Optional<Emprestimo> buscarEmprestimo(String nomeCliente, String tituloLivro,
            List<Emprestimo> listaEmprestimos) {
        Optional<Emprestimo> emprestimo = listaEmprestimos.stream()
                .filter((e) -> e.getRecebedorNome().contains(nomeCliente.toUpperCase())
                        && e.getLivroTitulo().contains(tituloLivro.toUpperCase())
                        && e.getEmprestimoStatus() == EmprestimoStatus.EMPRESTADO)
                .findFirst();

        return emprestimo;
    }
}
