package dao;

import entidades.Evento;

public interface EventoDAO {
	void create();
	void read(Evento obj);
	void update(Evento obj);
	void deleteById(Evento id);
}
