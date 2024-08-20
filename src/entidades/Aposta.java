package entidades;

import java.util.Date;

public class Aposta {
	private int id;
	private int idDeUsuario;
	private int idDeAposta; //não é o mesmo que id?
	private double odd;
	private double valor;
	private Date dataDeCriacao;
	private double retorno;
	private String status;
	private String escolha;
	private String resultado;
	
	public Aposta(int id, int idDeUsuario, int idDeAposta, double odd, double valor, Date dataDeCriacao, double retorno,
			String status, String escolha, String resultado) {
		super();
		this.id = id;
		this.idDeUsuario = idDeUsuario;
		this.idDeAposta = idDeAposta;
		this.odd = odd;
		this.valor = valor;
		this.dataDeCriacao = new Date();
		this.retorno = retorno;
		this.status = status;
		this.escolha = escolha;
		this.resultado = resultado;
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
	public int getIdDeUsuario() {
		return idDeUsuario;
	}
	public void setIdDeUsuario(int idDeUsuario) {
		this.idDeUsuario = idDeUsuario;
	}
	public int getIdDeAposta() {
		return idDeAposta;
	}
	public void setIdDeAposta(int idDeAposta) {
		this.idDeAposta = idDeAposta;
	}
	public double getOdd() {
		return odd;
	}
	public void setOdd(double odd) {
		this.odd = odd;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public double getRetorno() {
		return retorno;
	}
	public void setRetorno(double retorno) {
		this.retorno = retorno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEscolha() {
		return escolha;
	}
	public void setEscolha(String escolha) {
		this.escolha = escolha;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Aposta [id=" + id + ", idDeUsuario=" + idDeUsuario + ", idDeAposta=" + idDeAposta + ", odd=" + odd
				+ ", valor=" + valor + ", dataDeCriacao=" + dataDeCriacao + ", retorno=" + retorno + ", status="
				+ status + ", escolha=" + escolha + ", resultado=" + resultado + "]";
	}
	
	
}
