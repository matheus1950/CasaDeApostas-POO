package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.PovoamentoDao;
import db.DB;

public class PovoamentoDaoJDBC implements PovoamentoDao{
    
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
                //inserirApostas(connection);

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
    
    public void criarTabelas(Connection connection) throws SQLException {
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
                + "permissao BOOLEAN"
                + ")";
        connection.createStatement().execute(createUsuarioTable);

        //Criar tabela Evento
        String createEventoTable = "CREATE TABLE IF NOT EXISTS Evento ("
                + "id SERIAL PRIMARY KEY, "
                + "nome VARCHAR(100) NOT NULL, "
                + "dataDeCriacao DATE NOT NULL, "
                + "idDeUsuario INT, "
                + "permissao BOOLEAN, "
                + "descricao VARCHAR(100) NOT NULL "
                + ")";
                
        connection.createStatement().execute(createEventoTable);

        //Criar tabela Aposta
        String createApostaTable = "CREATE TABLE aposta ("
        		+ "id SERIAL PRIMARY KEY,"
        		+ "idDeEvento INT NOT NULL,"
        		+ "odd DOUBLE PRECISION NOT NULL,"
        		+ "dataDeCriacao DATE NOT NULL,"
        		+ "status VARCHAR(50) NOT NULL,"
        		+ "resultado VARCHAR(50),"
        		+ "FOREIGN KEY (idDeEvento) REFERENCES evento(id)"
        		+ "descricao VARCHAR(100), "
        		+ ")";
        connection.createStatement().execute(createApostaTable);
    }

    //Método para inserir dados na tabela Usuario
    public void inserirUsuarios(Connection connection) throws SQLException {
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
    public void inserirEventos(Connection connection) throws SQLException {
    	String sql = "INSERT INTO Evento (nome, dataDeCriacao, idDeUsuario, permissao, descricao) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            // Povoar a tabela Evento
            ps.setString(1, "Evento de Tecnologia");
            ps.setDate(2, java.sql.Date.valueOf("2024-01-15"));
            ps.setInt(3, 1);
            ps.setBoolean(4, true);
            ps.setString(5, "Conferência de tecnologia sobre IA e Machine Learning");
            ps.executeUpdate();

            ps.setString(1, "Workshop de Java");
            ps.setDate(2, java.sql.Date.valueOf("2024-02-20"));
            ps.setInt(3, 2);
            ps.setBoolean(4, false);
            ps.setString(5, "Workshop intensivo de programação em Java");
            ps.executeUpdate();

            ps.setString(1, "Seminário de Segurança");
            ps.setDate(2, java.sql.Date.valueOf("2024-03-10"));
            ps.setInt(3, 3);
            ps.setBoolean(4, true);
            ps.setString(5, "Seminário sobre novas práticas em cibersegurança");
            ps.executeUpdate();

            ps.setString(1, "Hackathon");
            ps.setDate(2, java.sql.Date.valueOf("2024-04-05"));
            ps.setInt(3, 4);
            ps.setBoolean(4, true);
            ps.setString(5, "Maratona de desenvolvimento de software");
            ps.executeUpdate();

            ps.setString(1, "Feira de Inovação");
            ps.setDate(2, java.sql.Date.valueOf("2024-05-25"));
            ps.setInt(3, 5);
            ps.setBoolean(4, false);
            ps.setString(5, "Feira de inovações tecnológicas");
            ps.executeUpdate();
        }
    }

    //Método para inserir dados na tabela Aposta - refazer
    public void inserirApostas(Connection connection) throws SQLException {
        String sql = "INSERT INTO Aposta (id_usuario, id_evento, valor_apostado, resultado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 1); 
            ps.setInt(2, 1); 
            ps.setDouble(3, 50.00);
            ps.setString(4, "Pendente");
            ps.executeUpdate();

            ps.setInt(1, 2); 
            ps.setInt(2, 2); 
            ps.setDouble(3, 100.00);
            ps.setString(4, "Pendente");
            ps.executeUpdate();

            ps.setInt(1, 3); 
            ps.setInt(2, 3);
            ps.setDouble(3, 20.00);
            ps.setString(4, "Pendente");
            ps.executeUpdate();
        }
    }
}
