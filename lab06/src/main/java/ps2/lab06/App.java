package ps2.lab06;

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
    private ProfessorRepo professorRepo;

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        boolean sair = false;
        while (!sair) {
            out.println("(1) Cadastrar Faculdade");
            out.println("(2) Listar Faculdades");
            out.println("(3) Cadastrar Professor");
            out.println("(4) Listar Professores");
            out.println("(5) Sair");
            out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarFaculdade();
                    break;
                case 2:
                    listarFaculdades();
                    break;
                case 3:
                    cadastrarProfessor();
                    break;
                case 4:
                    listarProfessores();
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

    private void cadastrarFaculdade() {
        out.print("Digite o nome da faculdade: ");
        String nome = entrada.nextLine();
        out.print("Digite o ano de fundação: ");
        int anoFundacao = Integer.parseInt(entrada.nextLine());

        Faculdade faculdade = new Faculdade(nome, anoFundacao);
        faculdadeRepo.save(faculdade);
        out.println("Faculdade cadastrada com ID: " + faculdade.getId());
    }

    private void listarFaculdades() {
        out.println("Lista de Faculdades");
        Iterable<Faculdade> faculdadesIterable = faculdadeRepo.findAll();
        List<Faculdade> faculdades = new ArrayList<>();
        faculdadesIterable.forEach(faculdades::add); 

        if (faculdades.isEmpty()) {
            out.println("## Não há faculdades cadastradas. ##");
            return;
        }

        faculdades.forEach(f -> {
            out.println("ID: " + f.getId() + ", Nome: " + f.getNome() + ", Ano de Fundação: " + f.getAnoFundacao());
        });
    }

    private void cadastrarProfessor() {
        out.print("Nome do novo professor: ");
        String nome = entrada.nextLine();
        out.print("CPF do novo professor: ");
        String cpf = entrada.nextLine();
        out.print("Matrícula do novo professor: ");
        int matricula = Integer.parseInt(entrada.nextLine());

        Iterable<Faculdade> faculdadesIterable = faculdadeRepo.findAll();
        List<Faculdade> faculdades = new ArrayList<>();
        faculdadesIterable.forEach(faculdades::add); // 

        if (faculdades.isEmpty()) {
            out.println("Não há faculdades cadastradas! Cadastre uma faculdade antes de cadastrar um professor.");
            return;
        }

        out.println("Faculdade do novo professor (selecione um dos números abaixo):");
        for (int i = 0; i < faculdades.size(); i++) {
            out.println("- (" + (i + 1) + ") " + faculdades.get(i).getNome() + " (" + faculdades.get(i).getAnoFundacao() + ")");
        }
        out.print("Entre o número da faculdade: ");
        int escolha = Integer.parseInt(entrada.nextLine()) - 1;

        if (escolha >= 0 && escolha < faculdades.size()) {
            Faculdade faculdade = faculdades.get(escolha);
            Professor professor = new Professor(nome, cpf, matricula, faculdade);
            professorRepo.save(professor);
            out.println("Professor cadastrado com ID: " + professor.getId());
        } else {
            out.println("Escolha inválida! Professor não cadastrado. ");
        }
    }

    private void listarProfessores() {
        out.printf("%-5s %-15s %-15s %-10s %-30s%n", "ID", "NOME", "CPF", "MATRÍCULA", "FACULDADE");

        Iterable<Professor> professoresIterable = professorRepo.findAll();
        List<Professor> professores = new ArrayList<>();
        professoresIterable.forEach(professores::add);

        professores.forEach(p -> {
            out.printf("%-5d %-15s %-15s %-10d %-30s%n",
                    p.getId(), p.getNome(), p.getCpf(), p.getMatricula(),
                    p.getFaculdade().getNome() + " (" + p.getFaculdade().getAnoFundacao() + ")");
        });
    }
}