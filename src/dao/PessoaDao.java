package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import entidades.Pessoa;
import entidades.Usuario;

public interface PessoaDao {
	
	//----------------------Pessoa---------------------------------------

	void insert(Pessoa pessoa) throws SQLException, PSQLException;
	
	// --------------------------Usuário-------------------------------
	
	void insert(Usuario usuario) throws SQLException, PSQLException;
	Usuario findUsuarioById(int idUsuario);
	String findPasswordByEmail(String email);
	boolean findPermissaoByEmailSenha(String email, String senha);
	boolean findPermissaoById(int idUsuario);
	int findIdByEmailSenha(String email, String senha);
	Integer findIdByEmail(String email);
	ArrayList<Usuario> todosUsuarios();
	void deleteById(int id) throws SQLException;
	boolean editarNome(int id, String nome);
	boolean editarEmail(int id, String email) throws SQLException;
	boolean editarSenha(int id, String senha) throws SQLException;
	boolean editarCarteira(int idUsuario, double adicional);
	
	//-----------------------------------------Métodos de ADM -------------------------------------------------------------

	void insertAdm(Pessoa adm) throws SQLException, PSQLException;

}
