package betTeste;

import Povoamento.PovoarBanco;
import db.DB;
import entidades.Usuario;

public class Programa {
	
		public static void main(String[] args) {
			Usuario user = new Usuario();
			
			System.out.println(user);
			
			//teste de conexão
			DB.getConnection();
			
			// teste de povoamento
			
			PovoarBanco povoacao = new PovoarBanco();
			
			povoacao.povoarInicialmente();
			
			
			
		}

}



