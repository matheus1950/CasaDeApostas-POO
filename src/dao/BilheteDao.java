package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import entidades.Aposta;
import entidades.Bilhete;

public interface BilheteDao {
	
	int usuarioTemBilhetePendente(int idUsuario);
	ArrayList<Bilhete> todosBilhetesPorUsuarioId(int idUsuario);
	Bilhete findBilheteById(int idBilhete);
	void inserirBilhete(Bilhete bilhete);
	boolean apostar(Bilhete bilhete);
	void inserirApostaNoBilheteById(int idBilhete, int idAposta) throws SQLException, PSQLException;
	ArrayList<Aposta> obterApostasPorBilheteId(int idBilhete) throws SQLException;
	void removerDoBilhete(int idAposta, int idBilhete);
	boolean bilheteNaoTemApostas(int idBilhete);
	void removerBilhete(int idBilhete);
	void editarStatusBilhete(int idBilhete, String novoStatus) throws SQLException;	
	
}
