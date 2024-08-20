package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	@Override
	public void update(Evento evento) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean deleteById(int id) {
		String sql = "DELETE FROM evento WHERE id = ?";
		
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
	
}
