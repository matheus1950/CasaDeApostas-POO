package dao;

import java.util.ArrayList;

import entidades.Evento;

public interface EventoDao {
	void insert(Evento obj);
	ArrayList<Evento> listarTodosEventos();
	void update(Evento obj);
	boolean deleteById(int id);
}
