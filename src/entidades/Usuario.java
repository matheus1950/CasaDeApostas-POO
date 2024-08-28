package entidades;

import java.sql.Date;

public class Usuario extends Pessoa{
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private double carteira;
	private int cpf; 
	private Date dataNascimento;
	
	public Usuario(int id, String nome, String email, String senha, double carteira, int cpf,
			Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.carteira = carteira;		
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	public Usuario(Pessoa pessoa) {
		super();
	}
	
	public Usuario() {
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
	public double getCarteira() {
		return carteira;
	}
	public void setCarteira(double carteira) {
		this.carteira = carteira;
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
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", carteira="
				+ carteira + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}
	
}
