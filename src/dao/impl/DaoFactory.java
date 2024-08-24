package dao.impl;

public class DaoFactory{
	
	public static UsuarioDaoJDBC criarUsuarioDaoJDBC() {
		return new UsuarioDaoJDBC();
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
}

//para instanciar as implementações DAO! Pode ser apagado se optarmos por instanciar diretamente!