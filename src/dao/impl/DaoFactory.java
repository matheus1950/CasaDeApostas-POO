package dao.impl;

public class DaoFactory{
	
	public static PessoaDaoJDBC criarPessoaDaoJDBC() {
		return new PessoaDaoJDBC();
	}
	
	public static ApostaDaoJDBC criarApostaDaoJDBC() {
		return new ApostaDaoJDBC();
	}
	
	public static EventoDaoJDBC criarEventoDaoJDBC() {
		return new EventoDaoJDBC();
	}
	
	public static BilheteDaoJDBC criarBilheteDaoJDBC() {
		return new BilheteDaoJDBC();
	}
	
	public static  CodigoDeCadastroAdmDaoJDBC criarCodigoDeCadastroAdmDaoJDBC() {
		return new CodigoDeCadastroAdmDaoJDBC();
	}
}

//para instanciar as implementações DAO! 