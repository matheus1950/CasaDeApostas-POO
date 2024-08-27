package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import dao.BilheteDao;
import db.DB;
import entidades.Aposta;
import entidades.Bilhete;
import entidades.Usuario;

public class BilheteDaoJDBC implements BilheteDao{
	private Connection conn = DB.getConnection();
	
	@Override //retorna o id de bilhete
	public int usuarioTemBilhetePendente(int idUsuario) {
		String sql = "SELECT * FROM bilhete WHERE iddeusuario = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idUsuario);;
	        try (ResultSet rs = ps.executeQuery()) {
	        	while(rs.next()) {
	                if(rs.getBoolean("efetuado") == false) {	                
	                	System.out.println("id do bilhete:" + rs.getInt("id"));
	                	return rs.getInt("id");	                	
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return -1; //-1 padrão para simbolizar o nulo
	}
	
	public ArrayList<Bilhete> todosBilhetesPorUsuarioId(int idUsuario){
		String sql = "SELECT * FROM bilhete WHERE iddeusuario = ?";
		ArrayList<Bilhete> bilhetes = new ArrayList<Bilhete>();
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idUsuario);;
	        try (ResultSet rs = ps.executeQuery()) {
	        	while(rs.next()) {
	                if(rs.getBoolean("efetuado") == true) {	                
	                	Bilhete bilhete = new Bilhete();
	                    bilhete.setId(rs.getInt("id"));
	                    bilhete.setValor(rs.getDouble("valor"));
	                    bilhete.setOddTotal(rs.getDouble("oddTotal"));
	                    bilhete.setIdDeUsuario(rs.getInt("idDeUsuario"));                    
	                    bilhete.setDataDeCriacao(rs.getDate("dataDeCriacao"));
	                    bilhete.setStatus(rs.getString("status"));	                    
	                    bilhete.setEfetuado(rs.getBoolean("efetuado"));
	                    bilhete.setRetorno(); //método de cálculo da própria entidade
	                    bilhetes.add(bilhete);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return bilhetes;
	}
	
	public Bilhete findBilheteById(int idBilhete) {
        Bilhete bilhete = null;
        String sql = "SELECT * FROM Bilhete WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBilhete);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bilhete = new Bilhete();
                    bilhete.setId(rs.getInt("id"));
                    bilhete.setIdDeUsuario(rs.getInt("idDeUsuario"));                    
                    bilhete.setDataDeCriacao(rs.getDate("dataDeCriacao"));
                    bilhete.setStatus(rs.getString("status"));                    
                    bilhete.setEfetuado(rs.getBoolean("efetuado"));                   
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bilhete;
    }
	
	public void inserirBilhete(Bilhete bilhete) {
		String sql = "INSERT INTO Bilhete (retorno, oddTotal, status, efetuado, iddeusuario) "
    			+ "VALUES (?, ?, ?, ?, ?)";	    
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setDouble(1, bilhete.getRetorno());
	        ps.setDouble(2, bilhete.getOddTotal());
	        ps.setString(3, bilhete.getStatus());	       
	        ps.setBoolean(4, bilhete.isEfetuado());
	        ps.setInt(5, bilhete.getIdDeUsuario());
	        ps.executeUpdate();        
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean apostar(Bilhete bilhete) {
		String sql = "UPDATE bilhete SET efetuado = ?, datadecriacao = ?, valor = ?, oddTotal = ?, retorno = ? WHERE id = ?";
		
		java.sql.Date sqlDate = new java.sql.Date(bilhete.getDataDeCriacao().getTime()); //linha de cast de util.Date para sql.Date
						
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setBoolean(1, true);
			ps.setDate(2, sqlDate);
			ps.setDouble(3, bilhete.getValor());
			ps.setDouble(4, bilhete.getOddTotal());			
			ps.setDouble(5, bilhete.getValor() * bilhete.getOddTotal());
			ps.setDouble(6, bilhete.getId());
			ps.executeUpdate();
			System.out.println("Bilhete atualizado com sucesso!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Aqui não sei se deveria estar nessa classe ou numa ApostaBilheteDaoJDBC, já que são operações pra tabela que lhes conecta
	public void inserirApostaNoBilheteById(int idBilhete, int idAposta) throws SQLException, PSQLException {
	    String sql = "INSERT INTO bilhete_aposta (idbilhete, idaposta) VALUES (?, ?)";
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idBilhete);
	        ps.setInt(2, idAposta);	        
	        ps.executeUpdate();	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	public ArrayList<Aposta> obterApostasPorBilheteId(int idBilhete) throws SQLException {	    
	    String sql = "SELECT aposta.* FROM aposta INNER JOIN bilhete_aposta ON aposta.id = bilhete_aposta.idaposta WHERE bilhete_aposta.idbilhete = ?";
	    
	    ArrayList<Aposta> apostas = new ArrayList<Aposta>();

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idBilhete);

	        try (ResultSet rs = ps.executeQuery()) {	            
	            while (rs.next()) {	                
	                Aposta aposta = new Aposta();
	                aposta.setId(rs.getInt("id")); 
	                aposta.setDescricao(rs.getString("descricao")); 
	                aposta.setOdd(rs.getDouble("odd"));
	                aposta.setDataDeCriacao(rs.getDate("datadecriacao"));
	                aposta.setIdDeEvento(rs.getInt("iddeevento"));
	                aposta.setStatus(rs.getString("status"));
                
	                apostas.add(aposta);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    // Converte a lista de apostas para um array e retorna
	    return apostas;
	}

		
	public void removerDoBilhete(int idAposta, int idBilhete) {		
			String sql = "DELETE FROM bilhete_aposta WHERE idaposta = ? AND idbilhete = ?";
					
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1, idAposta);
				ps.setInt(2, idBilhete);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public boolean bilheteNaoTemApostas(int idBilhete) {
		String sql = "SELECT COUNT(idAposta) AS NumeroDeApostas FROM bilhete_aposta WHERE idbilhete = ?";
		int qtd = 0;
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, idBilhete);
			//ps.executeUpdate();
			System.out.println("Contagem de apostas no bilhete realizada!");
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					qtd = rs.getInt("numerodeapostas");
				}
			}			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		if(qtd <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void removerBilhete(int idBilhete) {
		String sql = "DELETE FROM bilhete WHERE id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, idBilhete);
			ps.executeUpdate();
			System.out.println("Bilhete removido por não conter apostas!");		
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
	}
	
	public void editarStatusBilhete(int idBilhete, String novoStatus) throws SQLException {
	    
	    String sql = "UPDATE bilhete SET status = ? WHERE id = ?";

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {	       
	        ps.setString(1, novoStatus);
	        ps.setInt(2, idBilhete);	        	        
	        ps.executeUpdate();        	        
	    } catch (SQLException e) {        
	        e.printStackTrace();
	        throw e;
	    }
	}


}
