import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorNomesArq implements GerenciadorNomes {
    private static final String ARQUIVO_NOMES = "nomes.txt";

    @Override
    public List<String> obterNomes() {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_NOMES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                nomes.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro");
        }
        return nomes;
    }

    @Override
    public void adicionarNome(String nome) {
        // Obtém a lista através do método
        List<String> nomes = obterNomes();

        // Adiciona o novo nome
        nomes.add(nome);

        // Salva a lista atualizada
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_NOMES))) {
            for (String n : nomes) {
                writer.write(n);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }
}