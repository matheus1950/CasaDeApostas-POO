package Povoamento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DB;

public class PovoarBanco {
    
    public void povoarInicialmente(){
        try {
        	//Usando o getConnection da classe db.DB
            Connection connection = DB.getConnection();
            if (connection != null) {
                //Criar as tabelas
                criarTabelas(connection);

                //Inserir dados nas tabelas
                inserirUsuarios(connection);
                inserirEventos(connection);
                inserirApostas(connection);

                System.out.println("Banco de dados criado e povoado com sucesso!");

                //Fechar a conexão
                connection.close();
            } else {
                System.err.println("A conexão com o banco de dados não foi estabelecida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void criarTabelas(Connection connection) throws SQLException {
        //Criar tabela Usuario
        String createUsuarioTable = "CREATE TABLE IF NOT EXISTS Usuario ("
                + "id SERIAL PRIMARY KEY, "
                + "nome VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "senha VARCHAR(100) NOT NULL, "
                + "carteira DECIMAL(10, 2) DEFAULT 0.00, "
                + "idDeContrato INT, "
                + "cpf INT UNIQUE NOT NULL, "
                + "dataNascimento DATE NOT NULL"
                + ")";
        connection.createStatement().execute(createUsuarioTable);

        //Criar tabela Evento
        String createEventoTable = "CREATE TABLE IF NOT EXISTS Evento ("
                + "id SERIAL PRIMARY KEY, "
                + "nome VARCHAR(100) NOT NULL, "
                + "data DATE NOT NULL"
                + ")";
        connection.createStatement().execute(createEventoTable);

        //Criar tabela Aposta
        String createApostaTable = "CREATE TABLE IF NOT EXISTS Aposta ("
                + "id SERIAL PRIMARY KEY, "
                + "id_usuario INT REFERENCES Usuario(id), "
                + "id_evento INT REFERENCES Evento(id), "
                + "valor_apostado DECIMAL(10, 2) NOT NULL, "
                + "resultado VARCHAR(50)"
                + ")";
        connection.createStatement().execute(createApostaTable);
    }

    //Método para inserir dados na tabela Usuario
    private static void inserirUsuarios(Connection connection) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, email, senha, carteira, idDeContrato, cpf, dataNascimento) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "Carlos Silva");
            ps.setString(2, "carlos@exemplo.com");
            ps.setString(3, "senha123");
            ps.setDouble(4, 100.50);
            ps.setInt(5, 101);
            ps.setInt(6, 123456789);
            ps.setDate(7, Date.valueOf("1990-05-10"));
            ps.executeUpdate();

            ps.setString(1, "Maria Oliveira");
            ps.setString(2, "maria@exemplo.com");
            ps.setString(3, "senha456");
            ps.setDouble(4, 200.75);
            ps.setInt(5, 102);
            ps.setInt(6, 987654321);
            ps.setDate(7, Date.valueOf("1985-09-15"));
            ps.executeUpdate();

            ps.setString(1, "João Souza");
            ps.setString(2, "joao@exemplo.com");
            ps.setString(3, "senha789");
            ps.setDouble(4, 50.00);
            ps.setInt(5, 103);
            ps.setInt(6, 112233445);
            ps.setDate(7, Date.valueOf("1995-11-22"));
            ps.executeUpdate();
        }
    }

    //Método para inserir dados na tabela Evento
    private static void inserirEventos(Connection connection) throws SQLException {
        String sql = "INSERT INTO Evento (nome, data) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "Final da Copa do Mundo");
            ps.setDate(2, Date.valueOf("2024-12-18"));
            ps.executeUpdate();

            ps.setString(1, "Campeonato de Tênis");
            ps.setDate(2, Date.valueOf("2013-09-04"));
            ps.executeUpdate();

            ps.setString(1, "Corrida de F1");
            ps.setDate(2, Date.valueOf("2024-08-30"));
            ps.executeUpdate();
        }
    }

    //Método para inserir dados na tabela Aposta
    private static void inserirApostas(Connection connection) throws SQLException {
        String sql = "INSERT INTO Aposta (id_usuario, id_evento, valor_apostado, resultado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 1); // ID do usuário (Carlos Silva)
            ps.setInt(2, 1); // ID do evento (Final da Copa do Mundo)
            ps.setDouble(3, 50.00);
            ps.setString(4, "Pendente");
            ps.executeUpdate();

            ps.setInt(1, 2); // ID do usuário (Maria Oliveira)
            ps.setInt(2, 2); // ID do evento (Campeonato de Tênis)
            ps.setDouble(3, 100.00);
            ps.setString(4, "Pendente");
            ps.executeUpdate();

            ps.setInt(1, 3); // ID do usuário (João Souza)
            ps.setInt(2, 3); // ID do evento (Corrida de F1)
            ps.setDouble(3, 20.00);
            ps.setString(4, "Pendente");
            ps.executeUpdate();
        }
    }
}
