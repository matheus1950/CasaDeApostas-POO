package entidades;

import java.sql.Date;

public class Evento {
	private int id;
	private int idDeUsuario;
	private String nome;
	private boolean permissao;
	private Date dataDeCriacao;
	private String descricao;
	
	
	public Evento() {
		super();
	}
	
	public Evento(int id, int idDeUsuario, String nome, boolean permissao, Date dataDeCriacao, String descricao) {
		super();
		this.id = id;
		this.idDeUsuario = idDeUsuario;
		this.nome = nome;
		this.permissao = permissao;
		this.dataDeCriacao = dataDeCriacao;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdDeUsuario() {
		return idDeUsuario;
	}
	public void setIdDeUsuario(int idDeUsuario) {
		this.idDeUsuario = idDeUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isPermissao() {
		return permissao;
	}
	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}
	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
