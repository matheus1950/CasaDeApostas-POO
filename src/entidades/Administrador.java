package entidades;

import java.sql.Date;
import java.util.ArrayList;

public class Administrador extends Pessoa{
	ArrayList <Evento> eventos = new ArrayList();

	public Administrador(Pessoa pessoa) {
		super();
	}
	
	//construtores sem passagem de eventos (somente possível por setEventos);
	public Administrador(int id, String nome, String email, String senha, int cpf,
			Date dataNascimento) {
		super(id, nome, email, senha, cpf, dataNascimento);
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}	
	
}
