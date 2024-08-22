package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UsuarioDao;
import db.DB;
import entidades.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{

	private Connection conn = DB.getConnection();
	
	//funcionando, mas, provavelmente por questão do valor único do id, não está indo ao banco com o id especificado!
	//exemplo: testei inserir Ronaldo com id = 1, mas foi ao banco com id = 5
	@Override
	public void insert(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO Usuario "
				+ "(nome, email, senha, carteira, idDeContrato, cpf, dataNascimento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDouble(4, usuario.getCarteira());
            ps.setInt(5, usuario.getIdDeContrato());
            ps.setInt(6, usuario.getCpf());
            ps.setDate(7, usuario.getDataNascimento());
            ps.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
            ps.close();
        }
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public String findPasswordByEmail(String email) {
		String sql = "SELECT senha FROM usuario WHERE email = ?";
		String senha = null;
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, email);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					senha = rs.getString("senha");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return senha;
	}
	
	public boolean findPermissaoByEmailSenha(String email, String senha) {
		String sql = "SELECT permissao FROM usuario WHERE email = ? AND senha = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, email);
	        ps.setString(2, senha);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getBoolean("permissao");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return (Boolean) null; //não sei outra coisa para colocar aqui
	}
	
	public int findIdByEmailSenha(String email, String senha) {
		String sql = "SELECT id FROM usuario WHERE email = ? AND senha = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, email);
	        ps.setString(2, senha);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return (Integer) null; //não sei outra coisa para colocar aqui
	}
	
	 //sofrendo problemas para atualizar em decorrência do armanezamento de id já explicado acima do método create
	@Override
	public void update(Usuario usuario) {
		String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getId());
            System.out.println("Update concluído com sucesso!");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	//tive um problema para excluir por id enquanto havia a tabela "aposta"(violação da chave estrangeira),
	//fiz drop table pra testar, depois ver como deletar com a tabela aposta no lugar! 
	@Override
	public void deleteById(int id) throws SQLException{  
		String sql = "DELETE FROM usuario WHERE id = ?";
				
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Usuário deletado com sucesso!");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}