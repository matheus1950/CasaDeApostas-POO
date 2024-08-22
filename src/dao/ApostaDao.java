package dao;

import java.util.ArrayList;

import entidades.Aposta;

public interface ApostaDao {
	void create();
	void read(Aposta obj);
	void update(Aposta obj);
	void deleteById(Aposta id);
	ArrayList<Aposta> ListarApostasPorEventoId(int EventoId);
	boolean editarOdd(int id, double odd);
	boolean editarDescricao(int id, String descricao);
}
