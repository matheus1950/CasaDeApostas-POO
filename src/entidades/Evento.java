package entidades;

import java.util.Date;

public class Evento {
	private int id;
	private int idDeUsuario; //não entendi direito
	private String nome;
	private boolean permissao;
	private Date dataDeCriacao;
	private String descricao;
	
	
	public Evento() {
		super();
		this.dataDeCriacao = new Date();  //aqui ou na declaração de variável?
	}
	
	public Evento(int id, int idDeUsuario, String nome, boolean permissao, String descricao) {
		super();
		this.id = id;
		this.idDeUsuario = idDeUsuario;
		this.nome = nome;
		this.permissao = permissao;
		this.dataDeCriacao = new Date(); //aqui ou na declaração de variável?
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

	@Override
	public String toString() {
		return "Evento [id=" + id + ", idDeUsuario=" + idDeUsuario + ", nome=" + nome + ", permissao=" + permissao
				+ ", dataDeCriacao=" + dataDeCriacao + ", descricao=" + descricao + "]";
	}
	
	
}
