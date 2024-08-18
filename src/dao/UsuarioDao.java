package dao;

import java.sql.Connection;
import java.sql.SQLException;

import entidades.Usuario;

public interface UsuarioDao {
	
	void insert(Usuario usuario) throws SQLException;
	void read(Usuario obj);
	void update(Usuario obj);
	void deleteById(int id) throws SQLException;
}
