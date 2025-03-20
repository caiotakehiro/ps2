import java.sql.*;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        System.out.println("Boa noite");
        String url = "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.jqztwlnyqqhtbgqzjsji&password=Senhagrande1!";
        try (Connection conexao = DriverManager.getConnection(url)) {
            lerRegistros(conexao);
            create(conexao);
            update(conexao);
            delete(conexao);
            lerRegistros(conexao);
        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        }
    }

    public static void lerRegistros(Connection conexao) throws SQLException {
        String sql = "SELECT * FROM contas";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet rset = stm.executeQuery()) {
            while (rset.next()) {
                long nroConta = rset.getLong("nro_conta");
                double saldo = rset.getDouble("saldo");
                System.out.println("Conta: " + nroConta + "  Saldo: " + saldo);
            }
        }
    }

    public static void create(Connection conexao) throws SQLException {
        System.out.print("Numero da nova conta: ");
        long nro = Long.parseLong(System.console().readLine());
        System.out.print("Saldo da nova conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        String sql = "INSERT INTO contas VALUES (?, ?)";
        try (PreparedStatement prepstm = conexao.prepareStatement(sql)) {
            prepstm.setLong(1, nro);
            prepstm.setBigDecimal(2, saldo);
            prepstm.executeUpdate();
        }
    }

    public static void update(Connection conexao) throws SQLException {
        System.out.print("Conta que sera alterada: ");
        long nro = Long.parseLong(System.console().readLine());
        System.out.print("Novo saldo: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        String sql = "UPDATE contas SET saldo = ? WHERE nro_conta = ?";
        try (PreparedStatement prepstm = conexao.prepareStatement(sql)) {
            prepstm.setBigDecimal(1, saldo);
            prepstm.setLong(2, nro);
            prepstm.executeUpdate();
        }
    }

    public static void delete(Connection conexao) throws SQLException {
        System.out.print("Conta que sera excluida: ");
        long nro = Long.parseLong(System.console().readLine());

        String sql = "DELETE FROM contas WHERE nro_conta = ?";
        try (PreparedStatement prepstm = conexao.prepareStatement(sql)) {
            prepstm.setLong(1, nro);
            prepstm.executeUpdate();
        }
    }
}