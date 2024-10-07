package entities;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import utils.BuscarLivro;
import utils.BuscarUsuario;
import utils.EmprestarLivro;

public class Biblioteca {
    Scanner scanner = new Scanner(System.in);
    private final List<Livro> livros = new ArrayList<>();
    private final List<Autor> autores = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();

    public Biblioteca() {
        Autor autor1 = new Autor("George Orwell");
        Autor autor2 = new Autor("J.K. Rowling");
        Autor autor3 = new Autor("J.R.R. Tolkien");
        autores.add(autor1);
        autores.add(autor2);
        autores.add(autor3);

        livros.add(new Livro("1984", autor1, true, "Ficcao", LocalDateTime.now(), LocalDateTime.now()));
        livros.add(
                new Livro("Harry Potter e a Pedra Filosofal", autor2, true, "Romance", LocalDateTime.now(),
                        LocalDateTime.now()));
        livros.add(new Livro("O Senhor dos Aneis", autor3, true, "Fantasia", LocalDateTime.now(), LocalDateTime.now()));

        clientes.add(new Cliente("Alice Souza", "alice.souza@email.com", LocalDate.of(1990, 5, 15)));
        clientes.add(new Cliente("Bruno Martins", "bruno.martins@email.com", LocalDate.of(1985, 12, 30)));
        clientes.add(new Cliente("Carla Silva", "carla.silva@email.com", LocalDate.of(2000, 2, 20)));
    }

    public void init() {

        boolean isWorking = true;
        while (isWorking) {
            System.out.println("----------Sistema de Livraria----------");
            System.out.println("Selecione uma das opções");
            System.out.println("0 - Sair");
            System.out.println("1 - Ver livros disponíveis");
            System.out.println("2 - Ver histórico de empréstimos");
            System.out.println("3 - Ver clientes registrados");
            System.out.println("4 - Buscar por livro");
            System.out.println("5 - Registrar novo livro");
            System.out.println("6 - Registrar novo cliente");
            System.out.println("7 - Emprestar Livro");
            System.out.println("8 - Devolver Livro");
            Integer opcao = scanner.nextInt();
            // variável espaco criada apenas para evitar a exceção NoSuchElementException
            @SuppressWarnings("unused")
            String espaco = scanner.nextLine();

            switch (opcao) {
                case 0 -> isWorking = false;
                case 1 -> {
                    verLivros();
                    init();
                }
                case 2 -> {
                    verEmprestimos();
                    init();
                }
                case 3 -> {
                    verUsuariosCadastrados();
                    init();
                }
                case 4 -> {
                    buscarLivro();
                    init();
                }
                case 5 -> {
                    registrarNovoLivro();
                    init();
                }
                case 6 -> {
                    registrarNovoUsuario();
                    init();
                }
                case 7 -> {
                    emprestarLivro();
                    init();
                }
                case 8 -> {
                    devolverLivro();
                    init();
                }

                default -> {
                    System.out.println("Opção inválida, finalizando programa");
                    isWorking = false;
                    scanner.close();
                }
            }
        }
        System.out.println("Sistema finalizado");
        System.exit(0);
    }

    public void verLivros() {
        if (this.livros.isEmpty()) {
            System.out.println("Nenhum livro registrado");
            return;
        }
        System.out.println("Selecione uma das opções");
        System.out.println("1 - Ver todos");
        System.out.println("2 - Por gênero");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                List<Livro> livrosDisponiveis = livros.stream().filter(l -> l.isDisponivel() == true)
                        .collect(Collectors.toList());
                for (Livro livro : livrosDisponiveis) {
                    System.out.println("\n");
                    System.out.println("Título do livro: " + livro.getTitulo());
                    System.out.println("Autor(a): " + livro.getAutorName());
                    System.out.println("Gênero: " + livro.getGenero());
                }
                break;
            }
            case 2 -> {
                System.out.println("Digite o gênero desejado");
                @SuppressWarnings("unused")
                String espaco;
                espaco = scanner.nextLine();
                String genero = scanner.nextLine();
                List<Livro> livrosPorGenero = BuscarLivro.buscarLivrosPorGenero(genero, this.livros);
                if (livrosPorGenero.isEmpty()) {
                    System.out.println("Nenhum livro encontrado");
                    break;
                }
                for (Livro livro : livrosPorGenero) {
                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor(a): " + livro.getAutorName());
                    System.out.println("Gênero: " + livro.getGenero());
                    break;
                }
            }

            default -> {
                System.out.println("Comando não reconhecido");
                break;
            }
        }

    }

    public void buscarLivro() {

        System.out.println("Digite o nome do livro: ");
        var tituloLivro = scanner.nextLine();

        List<Livro> livrosEncontrados = BuscarLivro.buscarLivrosPorTitulo(tituloLivro, livros);
        System.out.println("Livros encontrados");
        for (Livro livro : livrosEncontrados) {
            System.out.println("\n");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor(a): " + livro.getAutorName());
            System.out.println("Gênero: " + livro.getGenero());
        }

    }

    public void verUsuariosCadastrados() {
        if (this.clientes.isEmpty()) {
            System.out.println("Nenhum cliente registrado");
            return;
        }
        System.out.println("Clientes Cadastrados:");
        for (Cliente cliente : clientes) {
            System.out.println("\n");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
        }
    }

    public void verEmprestimos() {
        if (this.emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado");
            return;
        }
        for (Emprestimo emprestimo : this.emprestimos) {
            System.out.println("\n");
            System.out.println("Recebedor: " + emprestimo.getRecebedorNome());
            System.out.println("Livro: " + emprestimo.getLivroTitulo());
            System.out.println("Status do empréstimo: " + emprestimo.getEmprestimoStatus());
            System.out.println("Data do empréstimo: " + emprestimo.getDataEmprestimoFormatada());
        }
    }

    public void emprestarLivro() {
        System.out.println("Digite o nome do(a) cliente:");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Digite o título do livro:");
        String tituloLivro = scanner.nextLine();
        Optional<Cliente> usuario = BuscarUsuario.buscarPorNome(nomeUsuario, this.clientes);
        Optional<Livro> livro = BuscarLivro.buscarPorTitulo(tituloLivro, livros);

        if (usuario.isEmpty()) {
            System.out.println("Usuário não encontrado, faça o cadastro");
            return;
        }

        if (livro.isEmpty()) {
            System.out.println("Livro não encontrado, faça o registro");
            return;
        }

        usuario.ifPresent((cliente) -> {
            livro.ifPresent((l) -> {
                Emprestimo novoEmprestimo = EmprestarLivro.emprestar(l, cliente);
                this.emprestimos.add(novoEmprestimo);
                System.out.println("Empréstimo realizado");
            });
        });
    }

    public void devolverLivro() {
        System.out.println("Digite o nome do usuário");
        String nome = scanner.nextLine();
        System.out.println("Digite o título do livro");
        String titulo = scanner.nextLine();
        Optional<Emprestimo> emprestimo = EmprestarLivro.buscarEmprestimo(nome, titulo, this.emprestimos);
        emprestimo.ifPresentOrElse((e) -> {
            EmprestarLivro.devolver(e);
            System.out.println("Livro devolvido");
        }, () -> System.out.println("Empréstimo não encontrado, verifique os dados"));

    }

    public void registrarNovoLivro() {
        System.out.println("Digite o título do livro (Sem acentos ou caracteres especiais)");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do(a) autor(a) do livro (Sem acentos ou caracteres especiais)");
        String nomeAutor = scanner.nextLine();

        System.out.println("Digite o gênero do livro");
        String genero = scanner.nextLine();

        var autor = new Autor(nomeAutor);

        this.livros.add(new Livro(titulo, autor, true, genero, LocalDateTime.now(), LocalDateTime.now()));
        System.out.println("Livro registrado !");
    }

    public void registrarNovoUsuario() {

        System.out.println("Digite o nome do novo cliente (Sem acentos ou caracteres especiais)");
        String nome = scanner.nextLine();
        System.out.println("Digite um email do novo cliente");
        String email = scanner.nextLine();
        System.out.println("Digite o dia do nascimento");
        Integer diaNascimento = scanner.nextInt();
        System.out.println("Digite o mês do nascimento");
        Integer mesNascimento = scanner.nextInt();
        System.out.println("Digite o ano do nascimento");
        Integer anoNascimento = scanner.nextInt();

        LocalDate dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);

        this.clientes.add(new Cliente(nome, email, dataNascimento));
        System.out.println("Usuário cadastrado !");
    }

    public List<Autor> getAutores() {
        return autores;
    }

}
