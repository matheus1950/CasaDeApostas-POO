package dao;

import entidades.Aposta;

public interface ApostaDAO {
	void create();
	void read(Aposta obj);
	void update(Aposta obj);
	void deleteById(Aposta id);
}
