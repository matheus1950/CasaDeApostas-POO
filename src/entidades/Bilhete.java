	package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Bilhete {
	private int id;
	private int idDeUsuario;
	private double oddTotal;
	private double retorno;
	private Date dataDeCriacao;
	private String status;
	private String resultado;
	private ArrayList <Aposta> apostas = new ArrayList();
	private boolean efetuado;
	private double valor;
		
	public Bilhete(int id, int idDeUsuario, String status,
			double valor) {
		super();
		this.id = id;
		this.idDeUsuario = idDeUsuario;		
		this.dataDeCriacao = new Date();
		this.status = status;
		this.resultado = "pendente";
		this.efetuado = false; //aqui ou na declaração
		this.status = "pendente"; //aqui ou na declaração
		this.valor = 0; //aqui ou na declaração		
		this.retorno = this.valor * oddTotal;
	}
	
	public Bilhete() {
		super();
		this.dataDeCriacao = new Date();
		this.resultado = "pendente";
		this.efetuado = false; //aqui ou na declaração
		this.status = "pendente"; //aqui ou na declaração
		this.valor = 0; //aqui ou na declaração	
		this.retorno = valor * oddTotal;
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
	public double getOddTotal() {
		return oddTotal;
	}
	public void setOddTotal(ArrayList<Aposta> apostas) {
		double multiplicador = 1;
		//multiplicando a odd de todas apostas para alcançar a odd total
		for(Aposta a : apostas) {
			multiplicador *= a.getOdd();
		}		
		this.oddTotal = multiplicador;
	}
	
	public void setOddTotal(double oddTotal) {
		this.oddTotal = oddTotal;
	}
	public double getRetorno() {
		return retorno;
	}
	public void setRetorno() {
		//retorno é a devolução da aposta, dada por valor(apostado) * oddTotal
		this.retorno = valor * oddTotal;
	}
	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}
	
	public void setDataDeCriacao() { //sem parametros
		this.dataDeCriacao = new Date();
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
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public ArrayList<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(ArrayList<Aposta> apostas) {
		this.apostas = apostas;
	}
	//adicionar uma única aposta!
	public void addAposta(Aposta aposta) {
		this.apostas.add(aposta);
	}

	public boolean isEfetuado() {
		return efetuado;
	}

	public void setEfetuado(boolean efetuado) {
		this.efetuado = efetuado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Bilhete [id=" + id + ", idDeUsuario=" + idDeUsuario + ", oddTotal=" + oddTotal + ", retorno=" + retorno
				+ ", dataDeCriacao=" + dataDeCriacao + ", status=" + status + ", resultado=" + resultado + ", apostas="
				+ apostas + ", efetuado=" + efetuado + ", valor=" + valor + "]";
	}
			
}
