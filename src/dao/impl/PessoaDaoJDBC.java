package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import dao.PessoaDao;
import dao.UsuarioDao;
import db.DB;
import entidades.Administrador;
import entidades.Pessoa;
import entidades.Usuario;

public class PessoaDaoJDBC implements PessoaDao{

	private Connection conn = DB.getConnection();
	
	//funcionando, mas, provavelmente por questão do valor único do id, não está indo ao banco com o id especificado!
	//exemplo: testei inserir Ronaldo com id = 1, mas foi ao banco com id = 5
	
	public void insert(Pessoa pessoa) throws SQLException, PSQLException {
		String sql = "INSERT INTO Pessoa "
				+ "(nome, email, senha, cpf, dataNascimento, permissao) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getSenha());                      
            ps.setInt(4, pessoa.getCpf());
            ps.setDate(5, pessoa.getDataNascimento());
            ps.setBoolean(6, false); //inicia em falso - depois ver se mudo essa lógica
            ps.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
            ps.close();
        }
		catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Pessoa findPessoaById(int idPessoa) {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idPessoa);
	        Pessoa pessoa = new Pessoa();
	        try (ResultSet rs = ps.executeQuery()) {
	        	while(rs.next()) {
	        		pessoa.setId(rs.getInt("id"));
		        	pessoa.setSenha(rs.getString("senha"));
		        	pessoa.setNome(rs.getString("nome"));  
		            pessoa.setCpf(rs.getInt("cpf"));		           
		            pessoa.setEmail(rs.getString("email"));
		            return pessoa;   
	        	}         
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return null; //não sei outra coisa para colocar aqui
	}

	@Override
	public String findPasswordByPessoaEmail(String email) {
		String sql = "SELECT senha FROM pessoa WHERE email = ?";
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
	
	public boolean findPermissaoByPessoaEmailSenha(String email, String senha) {
		String sql = "SELECT permissao FROM pessoa WHERE email = ? AND senha = ?";
		
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
	
	public boolean findPermissaoByPessoaId(int idPessoa) {
		String sql = "SELECT permissao FROM pessoa WHERE id = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idPessoa);
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
	
	public int findIdByPessoaEmailSenha(String email, String senha) {
		String sql = "SELECT id FROM pessoa WHERE email = ? AND senha = ?";
		
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
	public void deleteByPessoaId(int id) throws SQLException{  
		String sql = "DELETE FROM pessoa WHERE id = ?";
				
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Usuário deletado com sucesso!");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	//------------------- Métodos de Usuário ------------------------------------------------------------------------------//
	
	public void insert(Usuario usuario) throws SQLException, PSQLException {
		String sql = "INSERT INTO Pessoa "
				+ "(nome, email, senha, carteira, cpf, dataNascimento, permissao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDouble(4, usuario.getCarteira());            
            ps.setInt(5, usuario.getCpf());
            ps.setDate(6, usuario.getDataNascimento());
            ps.setBoolean(7, false); //falso padrão para a permissão
            ps.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
            ps.close();
        }
		catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Usuario findUsuarioById(int idUsuario) {
		String sql = "SELECT * FROM Pessoa WHERE id = ?";
		
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
		String sql = "SELECT senha FROM Pessoa WHERE email = ?";
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
		String sql = "SELECT permissao FROM Pessoa WHERE email = ? AND senha = ?";
		
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
		String sql = "SELECT permissao FROM Pessoa WHERE id = ?";
		
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
		String sql = "SELECT id FROM Pessoa WHERE email = ? AND senha = ?";
		
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
		String sql = "DELETE FROM Pessoa WHERE id = ?";
				
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
		String sql = "UPDATE Pessoa SET nome = ? WHERE id = ?";
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
		String sql = "UPDATE Pessoa SET email = ? WHERE id = ?";
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
		String sql = "UPDATE Pessoa SET senha = ? WHERE id = ?";
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
		String sql = "UPDATE Pessoa SET carteira = ? WHERE id = ?";
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
	
	//-----------------------------------------Métodos de ADM -------------------------------------------------------------

	public void insertAdm(Pessoa adm) throws SQLException, PSQLException{
		String sql = "INSERT INTO Pessoa "
				+ "(nome, email, senha, cpf, dataNascimento, permissao) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, adm.getNome());
            ps.setString(2, adm.getEmail());
            ps.setString(3, adm.getSenha());                      
            ps.setInt(4, adm.getCpf());
            ps.setDate(5, adm.getDataNascimento());
            ps.setBoolean(6, true); //inicia em true - depois ver se mudo essa lógica
            ps.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
            ps.close();
        }
		catch(SQLException e){
			e.printStackTrace();
			throw e;
		}	
	}
}
