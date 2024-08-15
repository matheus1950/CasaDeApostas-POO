package entidades;

import java.sql.Date;

public class Administrador extends Usuario{
	private Evento[] eventos;

	public Administrador() {
		super();
	}
	
	//construtores sem passagem de eventos (somente possível por setEventos);
	public Administrador(int id, String nome, String email, String senha, double carteira, int idDeContrato, int cpf,
			Date dataNascimento) {
		super(id, nome, email, senha, carteira, idDeContrato, cpf, dataNascimento);
	}

	public Evento[] getEventos() {
		return eventos;
	}

	public void setEventos(Evento[] eventos) {
		this.eventos = eventos;
	}
	
	
}
