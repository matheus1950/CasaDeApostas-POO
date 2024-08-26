package betTeste;

import java.sql.Date;
import java.sql.SQLException;

import dao.impl.DaoFactory;
import dao.impl.PovoamentoDaoJDBC;
import db.DB;
import entidades.Usuario;

public class Programa {
	
		public static void main(String[] args){
			Date nascimento = Date.valueOf("1985-02-01");
			//UsuarioDaoJDBC banco = new UsuarioDaoJDBC(); - método de fazer sem a classe DaoFactory, podemos tirar qualquer coisa
			DaoFactory dao = new DaoFactory();
				
			
			//teste de conexão
			DB.getConnection();
			
			// teste de povoamento
			
			/*
			PovoamentoDAO povoacao = new PovoamentoDAO();
			
			povoacao.povoarInicialmente();
			
			*/
		
			//teste de deleção
			
			//dao.criarEventoDaoJDBC().deleteById(4);
			
			//banco.deleteById(1);
			
			//teste de inserção de usuario - usando a classe daofactory
			
			/*
			try {
				dao.criarUsuarioDaoJDBC().insert(user2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			*/
			
			//teste de update de usuário - dando problema com a questão do ID mencionado em UsuarioDaoJDBC, acima do método create;
			//como o id de Ronaldo era 1 e foi ao banco como 5, o update não funciona. Precisei trocar manualmente o id para 5!	(daí funcionou!)
			//falta tornar os atributos que serão atualizados em algo seletivo
			/*
			user.setId(5);
			user.setEmail("Ronaldofenomeno@email.com");
			user.setCarteira(1000.0);
			banco.update(user);
			*/
			
			//teste de listarTodosEventos
			//dao.criarEventoDaoJDBC().listarTodosEventos();
			
			//teste de listarApostasPorEvento
			//dao.criarApostaDaoJDBC().ListarApostasPorEventoId(1);
		}

}



