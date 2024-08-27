package dao;

import java.util.ArrayList;

import entidades.Aposta;

public interface ApostaDao {
	void insert(Aposta aposta);
	ArrayList<Aposta> ListarApostasPorEventoId(int EventoId);
	boolean deleteById(int id);
	boolean editarOdd(int id, double odd);
	ArrayList<Aposta> listarTodasApostas();
	Aposta findApostaById(int idAposta);
	boolean editarDescricao(int id, String descricao);
	ArrayList<Aposta> findApostasByBilheteId(int idBilhete);
}
