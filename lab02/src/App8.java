import banco.*;

public class App8 {
    public static void main(String[] args) {
        Banco meuBanco = new Banco("Java");

        meuBanco.adicionar(new ContaInfinita(1111, 50));
        meuBanco.adicionar(new ContaEspecial(1234, 100, 300));
        meuBanco.adicionar(new ContaBancaria(2222, 90));
        meuBanco.adicionar(new ContaInfinita(3333, 70));
        meuBanco.adicionar(new ContaBancaria(4444, 99));
        meuBanco.adicionar(new ContaEspecial(5555, 40, 200));

        meuBanco.premiarInfinitas();
        meuBanco.aumentarLimiteEspeciais(200);
        meuBanco.tentarSacar(100);
        meuBanco.mostrar();
    }
}

