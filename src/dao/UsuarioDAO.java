package dao;

import entidades.Usuario;

public interface UsuarioDAO {
	void create();
	void read(Usuario obj);
	void update(Usuario obj);
	void deleteById(Usuario id);
}
