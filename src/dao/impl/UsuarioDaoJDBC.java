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
				+ "(email, email, senha, carteira, idDeContrato, cpf, dataNascimento) "
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
	
	public Usuario findUsuarioById(int idUsuario) {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idUsuario);
	        Usuario usuario = new Usuario();
	        try (ResultSet rs = ps.executeQuery()) {
	        	while(rs.next()) {
	        		usuario.setId(rs.getInt("id"));
		        	usuario.setSenha(rs.getString("senha"));
		        	usuario.setNome(rs.getString("nome"));  
		            usuario.setCpf(rs.getInt("cpf"));
		            usuario.setCarteira(rs.getDouble("carteira"));
		            usuario.setEmail(rs.getString("email"));
		            return usuario;   
	        	}         
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return null; //não sei outra coisa para colocar aqui
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
	
	public boolean findPermissaoById(int idUsuario) {
		String sql = "SELECT permissao FROM usuario WHERE id = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idUsuario);
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

	public boolean editarNome(int id, String nome) {
		String sql = "UPDATE usuario SET nome = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, nome);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Nome de usuário atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editarEmail(int id, String email) {
		String sql = "UPDATE usuario SET email = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, email);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Email de usuário atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean editarSenha(int id, String senha) {
		String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, senha);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Senha de usuário atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	public boolean editarCarteira(int idUsuario, double adicional) {
		String sql = "UPDATE usuario SET carteira = ? WHERE id = ?";
		double saldoAtual = 0;		
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setDouble(1, (findUsuarioById(idUsuario).getCarteira() + adicional));
			ps.setInt(2, idUsuario);
			ps.executeUpdate();
			System.out.println("Carteira de usuário atualizada com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	
	
	

}
