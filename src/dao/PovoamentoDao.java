package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface PovoamentoDao {
	 void povoarInicialmente();
	 void criarTabelas(Connection connection) throws SQLException;
	 void inserirUsuarios(Connection connection) throws SQLException;
	 void inserirEventos(Connection connection) throws SQLException;
	 void inserirApostas(Connection connection) throws SQLException;
}
