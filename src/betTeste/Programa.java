package betTeste;

import java.sql.Date;
import java.sql.SQLException;

import dao.impl.PovoamentoDAO;
import dao.impl.UsuarioDaoJDBC;
import db.DB;
import entidades.Usuario;

public class Programa {
	
		public static void main(String[] args){
			Date nascimento = Date.valueOf("1985-02-01");
			Usuario user = new Usuario(1, "Ronaldo", "Ronaldo@fenomeno", "123", 0.0, 9, 22892332, nascimento);
			UsuarioDaoJDBC banco = new UsuarioDaoJDBC();
			
			System.out.println(user);
			
			//teste de conexão
			DB.getConnection();
			
			// teste de povoamento
			
			/*
			PovoamentoDAO povoacao = new PovoamentoDAO();
			
			povoacao.povoarInicialmente();
			*/
			
			//teste de deleção
			
			//banco.deleteById(1);
			
			//teste de inserção de usuario
			
			/*
			try {
				banco.insert(user);
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
			
			
			
		}

}



