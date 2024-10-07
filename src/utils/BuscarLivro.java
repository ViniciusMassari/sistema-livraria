package utils;

import entities.Livro;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BuscarLivro {
    public static Optional<Livro> buscarLivroPorGenero(String busca, List<Livro> listaDeLivros) {
        var livroEncontrado = listaDeLivros.stream().filter(livro -> livro.getGenero().contains(busca.toUpperCase()))
                .findFirst();

        return livroEncontrado;
    }

    public static Optional<Livro> buscarPorTitulo(String busca, List<Livro> listaDeLivros) {
        var livroEncontrado = listaDeLivros.stream().filter(livro -> livro.getTitulo().contains(busca.toUpperCase()))
                .findFirst();

        return livroEncontrado;
    }

    public static List<Livro> buscarLivrosPorTitulo(String busca, List<Livro> listaDeLivros) {
        var livroEncontrado = listaDeLivros.stream().filter(livro -> livro.getTitulo().contains(busca.toUpperCase()))
                .toList();

        return livroEncontrado;
    }

    public static List<Livro> buscarLivrosPorGenero(String busca, List<Livro> listaDeLivros) {
        var livrosEncontrados = listaDeLivros.stream().filter(livro -> livro.getGenero().contains(busca.toUpperCase()))
                .collect(Collectors.toList());

        return livrosEncontrados;
    }
}
