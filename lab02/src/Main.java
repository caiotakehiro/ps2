import banco.*;

public class Main {
    public static void main(String[] args) {
        Conta[] contas = {new ContaInfinita(1111, 50),
                          new ContaInfinita(3333, 1000),
                          new ContaBancaria(2222, 99)};
        for (int i = 0; i<contas.length; i++ ){
            contas[i].sacar(900);
            System.out.print(contas[i]);
        }
    }
}
