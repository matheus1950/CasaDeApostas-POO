package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EventoDao;
import db.DB;
import entidades.Aposta;
import entidades.Evento;
import entidades.Pessoa;

public class EventoDaoJDBC implements EventoDao{
	

	private Connection conn = DB.getConnection();
	
	@Override
	public void insert(Evento evento) {
	        String sql = "INSERT INTO Evento (nome, dataDeCriacao, descricao, permissao, idDeUsuario, resultado, status) "
	        			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        java.sql.Date sqlDate = new java.sql.Date(evento.getDataDeCriacao().getTime()); //linha de cast de util.Date para sql.Date
	        
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, evento.getNome());
	            ps.setDate(2, sqlDate);
	            ps.setString(3, evento.getDescricao());
	            ps.setBoolean(4, evento.isPermissao());
	            ps.setInt(5, evento.getIdDeUsuario());
	            ps.setString(6, evento.getResultado());
	            ps.setString(7, evento.getStatus());
	            ps.executeUpdate();
	            System.out.println("Evento inserido com sucesso!");	            
	        } catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public ArrayList<Evento> listarTodosEventos() {
		String sql = "SELECT * FROM Evento";
		ArrayList<Evento> eventos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	//não coloquei permissão aqui, não acho que seja necessário que o usuário saiba
                Evento evento = new Evento();
                evento.setId(rs.getInt("id"));
                evento.setIdDeUsuario(rs.getInt("iddeusuario"));
                evento.setNome(rs.getString("nome"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setDataDeCriacao(rs.getDate("datadecriacao"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }
	
	public ArrayList<Evento> listarTodosEventosNaoEncerrados() {
		String sql = "SELECT * FROM Evento WHERE status != 'encerrado'";
	    ArrayList<Evento> eventos = new ArrayList<>();

	    try (PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Evento evento = new Evento();
	            evento.setId(rs.getInt("id"));
	            evento.setIdDeUsuario(rs.getInt("iddeusuario"));
	            evento.setNome(rs.getString("nome"));
	            evento.setDescricao(rs.getString("descricao"));
	            evento.setDataDeCriacao(rs.getDate("datadecriacao"));
	            evento.setStatus(rs.getString("status")); 
	            eventos.add(evento);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return eventos;
    }

	@Override
	public boolean editarDescricao(int id, String descricao) {
		String sql = "UPDATE evento SET descricao = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, descricao);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Evento atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editarNome(int id, String nome) {
		String sql = "UPDATE evento SET nome = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, nome);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Evento atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editarStatus(int id, String status) {
		String sql = "UPDATE evento SET status = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Evento atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editarResultado(int id, String resultado) {
		String sql = "UPDATE evento SET resultado = ? WHERE id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, resultado);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Evento atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	@Override
	public boolean deleteById(int id) {
		DaoFactory dao = new DaoFactory();
		ArrayList<Aposta> apostas = apostas = dao.criarApostaDaoJDBC().ListarApostasPorEventoId(id);
		String sql = "DELETE FROM evento WHERE id = ?";
		
		for(Aposta aposta : apostas) {
			dao.criarApostaDaoJDBC().deleteById(aposta.getId());
		}
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Evento deletado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Evento findEventoById(int idEvento) {
		String sql = "SELECT * FROM evento WHERE id = ?";
		DaoFactory dao = new DaoFactory();
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idEvento);
	        Evento evento = new Evento();
	        try (ResultSet rs = ps.executeQuery()) {
	        	if(rs.next()) {
	        		evento.setId(rs.getInt("id"));
	                evento.setIdDeUsuario(rs.getInt("iddeusuario"));
	                evento.setNome(rs.getString("nome"));
	                evento.setDescricao(rs.getString("descricao"));
	                evento.setDataDeCriacao(rs.getDate("datadecriacao"));
	                evento.setDescricao(rs.getString("descricao"));
	                evento.setResultado(rs.getString("resultado"));
	                evento.setStatus(rs.getString("status"));
	                evento.setAssociadas(dao.criarApostaDaoJDBC().ListarApostasPorEventoId(idEvento));	                
		            return evento;   
	        	}         
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return null; //não sei outra coisa para colocar aqui
	}

	
}
