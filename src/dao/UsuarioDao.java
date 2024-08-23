package dao;

import java.sql.Connection;
import java.sql.SQLException;

import entidades.Usuario;

public interface UsuarioDao {
	
	void insert(Usuario usuario) throws SQLException;
	String findPasswordByEmail(String email);
	void deleteById(int id) throws SQLException;
	public boolean editarNome(int id, String nome);
	public boolean editarEmail(int id, String email);
}
