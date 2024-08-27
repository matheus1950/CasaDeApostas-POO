package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
	private int id;
	private int idDeUsuario; //não entendi direito
	private String nome;
	private boolean permissao;
	private Date dataDeCriacao;
	private String status;
	private String descricao;
	private String resultado;
	private ArrayList<Aposta> associadas;
	
	
	public Evento() {
		super();
		this.dataDeCriacao = new Date();  //aqui ou na declaração de variável?
	}
	
	public Evento(int id, int idDeUsuario, String nome, boolean permissao, String descricao, String status) {
		super();
		this.id = id;
		this.idDeUsuario = idDeUsuario;
		this.nome = nome;
		this.permissao = permissao;
		this.dataDeCriacao = new Date(); //aqui ou na declaração de variável?
		this.descricao = descricao;
		this.status = status;
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

	public ArrayList<Aposta> getAssociadas() {
		return associadas;
	}

	public void setAssociadas(ArrayList<Aposta> associadas) {
		this.associadas = associadas;
	}


	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", idDeUsuario=" + idDeUsuario + ", nome=" + nome + ", permissao=" + permissao
				+ ", dataDeCriacao=" + dataDeCriacao + ", status=" + status + ", descricao=" + descricao
				+ ", resultado=" + resultado + ", associadas=" + associadas + "]";
	}	
	
}
