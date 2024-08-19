package dao;

import entidades.Evento;

public interface EventoDao {
	void insert(Evento obj);
	void read(Evento obj);
	void update(Evento obj);
	void deleteById(int id);
}
