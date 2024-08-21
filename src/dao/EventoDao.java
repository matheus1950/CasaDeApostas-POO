package dao;

import java.util.ArrayList;

import entidades.Evento;

public interface EventoDao {
	void insert(Evento obj);
	ArrayList<Evento> listarTodosEventos();
	boolean deleteById(int id);
	boolean editarDescricao(int id, String descricao);
}
