package entidades;


import java.sql.Date;
import java.text.SimpleDateFormat;

public class Pessoa {

	private int id;
	private String nome;
	private String email;
	private String senha;	
	private int cpf; 
	private Date dataNascimento;
	
	public Pessoa(int id, String nome, String email, String senha, int cpf,
			Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;				
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	public Pessoa() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimentoSQL) {
		this.dataNascimento = dataNascimentoSQL;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", dataNascimento=" + dataNascimento + "]";
	}
	
}
