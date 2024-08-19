package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.EventoDao;
import db.DB;
import entidades.Evento;

public class EventoDaoJDBC implements EventoDao{
	

	private Connection conn = DB.getConnection();
	
	@Override
	public void insert(Evento evento) {
	        String sql = "INSERT INTO Evento (nome, dataDeCriacao, descricao, permissao, idDeUsuario) "
	        			+ "VALUES (?, ?, ?, ?, ?)";
	        
	        java.sql.Date sqlDate = new java.sql.Date(evento.getDataDeCriacao().getTime()); //linha de cast de util.Date para sql.Date
	        
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, evento.getNome());
	            ps.setDate(2, sqlDate);
	            ps.setString(3, evento.getDescricao());
	            ps.setBoolean(4, evento.isPermissao());
	            ps.setInt(5, evento.getIdDeUsuario());
	            ps.executeUpdate();
	            System.out.println("Evento inserido com sucesso!");
	            ps.close();
	        } catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void read(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM evento WHERE id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Evento deletado com sucesso!");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
