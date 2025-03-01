public class Main {
    public static void main(String[] args) {
        GerenciadorNomes gerenciador = new GerenciadorNomesArq();
        Ihm ihm = new Ihm(gerenciador);
        ihm.dialogar();
    }
}