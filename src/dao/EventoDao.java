package dao;

import java.util.ArrayList;

import entidades.Evento;

public interface EventoDao {
	void insert(Evento evento);
	ArrayList<Evento> listarTodosEventos();
	ArrayList<Evento> listarTodosEventosNaoEncerrados();
	boolean deleteById(int id);
	boolean editarDescricao(int id, String descricao);
	boolean editarNome(int id, String nome);
	boolean editarStatus(int id, String status);
	boolean editarResultado(int id, String resultado);
	Evento findEventoById(int idEvento);
	
}
