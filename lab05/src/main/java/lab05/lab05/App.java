package lab05.lab05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static java.lang.System.out;
import java.util.Scanner;
// jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.jqztwlnyqqhtbgqzjsji&password=Senhagrande1!
@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private TitularRepo titularRepo;

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) {
		boolean sair = false;
		while (!sair) {
			out.println("## GERENCIADOR DE TITULARES ##");
			out.println("(1) Criar titular");
			out.println("(2) Ler todos os titulares");
			out.println("(3) Ler um titular específico");
			out.println("(4) Alterar titular");
			out.println("(5) Apagar titular");
			out.println("(0) Sair");
			out.print("Escolha uma opção: ");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch(opcao) {
				case 1: criar(); break; 
				case 2: lerTodos(); break; 
				case 3: ler(); break;
				case 4: alterar(); break;
				case 5: apagar(); break;
				case 0: sair = true; break;
				default:
					out.println("## Opção inválida! ##");
			}
		}
	}	

	public void criar() {
		out.print("# Id do novo titular: ");
		long id = Long.parseLong(entrada.nextLine());
		out.print("# Nome do novo titular: ");
		String nome = entrada.nextLine();
		out.print("# CPF do novo titular: ");
		String cpf = entrada.nextLine();
		Titular t = new Titular(id, nome, cpf);
		titularRepo.save(t);
	}
	public void lerTodos() {
		Iterable<Titular> titulares=titularRepo.findAll();
		out.println("titulares cadastrados: ");
		for(Titular t: titulares){
			out.println(t.getId() + " - " + t.getNome() + " - " + t.getCpf() );
		}
	}
	public void ler() {
        out.print("# Digite o id do titular que deseja ler: ");
        long id = Long.parseLong(entrada.nextLine());
        Titular t = titularRepo.findById(id).orElse(null);
        if (t != null) {
            out.println("Titular encontrado:");
            out.println(t.getId() + " - " + t.getNome() + " - " + t.getCpf());
        } else {
            out.println("## Titular com id " + id + " não encontrado! ##");
        }
    }

    public void alterar() {
        out.print("# Digite o ID do titular que deseja alterar: ");
        long id = Long.parseLong(entrada.nextLine());
        Titular t = titularRepo.findById(id).orElse(null);
        if (t != null) {
            out.println("Titular encontrado:");
            out.println(t.getId() + " - " + t.getNome() + " - " + t.getCpf());
            out.print("# Novo nome do titular: ");
            String novoNome = entrada.nextLine();
            out.print("# Novo CPF do titular: ");
            String novoCpf = entrada.nextLine();
            t.setNome(novoNome);
            t.setCpf(novoCpf);
            titularRepo.save(t);
            out.println("## Titular atualizado com sucesso! ##");
        } else {
            out.println("## Titular com ID " + id + " não encontrado! ##");
        }
    }

    public void apagar() {
        out.print("# Digite o ID do titular que deseja apagar: ");
        long id = Long.parseLong(entrada.nextLine());
        Titular t = titularRepo.findById(id).orElse(null);
        if (t != null) {
            out.println("Titular encontrado:");
            out.println(t.getId() + " - " + t.getNome() + " - " + t.getCpf());
            titularRepo.delete(t);
            out.println("## Titular apagado com sucesso! ##");
        } else {
            out.println("## Titular com ID " + id + " não encontrado! ##");
        }
    }
}