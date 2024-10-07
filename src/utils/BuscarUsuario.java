package utils;

import java.util.List;
import java.util.Optional;

import entities.Cliente;

public class BuscarUsuario {

    public static Optional<Cliente> buscarPorNome(String busca, List<Cliente> listaDeClientes) {
        var clienteEncontrado = listaDeClientes.stream()
                .filter(cliente -> cliente.getNome().contains(busca.toUpperCase()))
                .findFirst();

        return clienteEncontrado;
    }
}
