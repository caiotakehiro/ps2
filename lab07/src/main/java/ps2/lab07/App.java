package ps2.lab07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private MusicaRepo musicaRepo;

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        boolean sair = false;
        while (!sair) {
            out.println("(1) Cadastrar Música");
            out.println("(2) Listar Músicas");
            out.println("(3) Atualizar Música");
            out.println("(4) Deletar Música");
            out.println("(5) Sair");
            out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarMusica();
                    break;
                case 2:
                    listarMusicas();
                    break;
                case 3:
                    atualizarMusica();
                    break;
                case 4:
                    deletarMusica();
                    break;
                case 5:
                    sair = true;
                    out.println("Saindo...");
                    break;
                default:
                    out.println("Opção inválida");
            }
        }
    }

    private void cadastrarMusica() {
        out.print("Digite o título da música: ");
        String titulo = entrada.nextLine();
        out.print("Digite o compositor da música: ");
        String compositor = entrada.nextLine();
        out.print("Digite o ano da música: ");
        int ano = Integer.parseInt(entrada.nextLine());

        Musica musica = new Musica(titulo, compositor, ano);
        musicaRepo.save(musica);
        out.println("Música cadastrada com ID: " + musica.getId());
    }

    private void listarMusicas() {
        out.printf("%-5s %-30s %-20s %-10s%n", "ID", "TÍTULO", "COMPOSITOR", "ANO");
        Iterable<Musica> musicasIterable = musicaRepo.findAll();
        List<Musica> musicas = new ArrayList<>();
        musicasIterable.forEach(musicas::add);

        if (musicas.isEmpty()) {
            out.println("Não há músicas cadastradas.");
            return;
        }

        musicas.forEach(m -> {
            out.printf("%-5d %-30s %-20s %-10d%n",
                    m.getId(), m.getTitulo(), m.getCompositor(), m.getAno());
        });
    }

    private void atualizarMusica() {
        out.print("Digite o ID da música que deseja atualizar: ");
        Long id = Long.parseLong(entrada.nextLine());

        Musica musica = musicaRepo.findById(id).orElse(null);
        if (musica == null) {
            out.println("Música não encontrada!");
            return;
        }

        out.print("Novo título da música [" + musica.getTitulo() + "]: ");
        String novoTitulo = entrada.nextLine();
        if (!novoTitulo.isEmpty()) {
            musica.setTitulo(novoTitulo);
        }

        out.print("Novo compositor da música [" + musica.getCompositor() + "]: ");
        String novoCompositor = entrada.nextLine();
        if (!novoCompositor.isEmpty()) {
            musica.setCompositor(novoCompositor);
        }

        out.print("Novo ano da música [" + musica.getAno() + "]: ");
        String novoAnoInput = entrada.nextLine();
        if (!novoAnoInput.isEmpty()) {
            int novoAno = Integer.parseInt(novoAnoInput);
            musica.setAno(novoAno);
        }

        musicaRepo.save(musica);
        out.println("Música atualizada com sucesso!");
    }

    private void deletarMusica() {
        out.print("Digite o ID da música que deseja deletar: ");
        Long id = Long.parseLong(entrada.nextLine());

        Musica musica = musicaRepo.findById(id).orElse(null);
        if (musica == null) {
            out.println("Música não encontrada!");
            return;
        }

        musicaRepo.delete(musica);
        out.println("Música deletada com sucesso!");
    }
}