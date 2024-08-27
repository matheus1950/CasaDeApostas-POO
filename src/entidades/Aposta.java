package entidades;

import java.util.Date;

public class Aposta {
	private int id;
	private int idDeEvento;
	private double odd;
	private Date dataDeCriacao;
	private String status;
	private String descricao;
	
	public Aposta(int id, double odd, Date dataDeCriacao, 
			String status, String resultado, int idDeEvento) {
		super();
		this.id = id;
		this.odd = odd;	
		this.dataDeCriacao = new Date();
		this.status = status;
		this.setIdDeEvento(idDeEvento);
	}
	
	public Aposta() {
		super();
		this.dataDeCriacao = new Date();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getOdd() {
		return odd;
	}
	public void setOdd(double odd) {
		this.odd = odd;
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdDeEvento() {
		return idDeEvento;
	}

	public void setIdDeEvento(int idDeEvento) {
		this.idDeEvento = idDeEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Aposta [id=" + id + ", idDeEvento=" + idDeEvento + ", odd=" + odd + ", dataDeCriacao=" + dataDeCriacao
				+ ", status=" + status + ", descricao=" + descricao + "]";
	}

	
}
