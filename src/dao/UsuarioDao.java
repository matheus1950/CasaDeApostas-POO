package dao;

import java.sql.Connection;
import java.sql.SQLException;

import entidades.Usuario;

public interface UsuarioDao {
	
	void insert(Usuario usuario) throws SQLException;
	String findPasswordByEmail(String email);
	void update(Usuario obj);
	void deleteById(int id) throws SQLException;
}
