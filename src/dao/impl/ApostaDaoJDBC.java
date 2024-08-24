package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DB;
import entidades.Aposta;
import entidades.Evento;

public class ApostaDaoJDBC {
	
	private Connection conn = DB.getConnection();
		
	public void insert(Aposta aposta) {
        String sql = "INSERT INTO Aposta (descricao, idDeEvento, odd, dataDeCriacao, status) "
        			+ "VALUES (?, ?, ?, ?, ?)";
        
        java.sql.Date sqlDate = new java.sql.Date(aposta.getDataDeCriacao().getTime()); //linha de cast de util.Date para sql.Date
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {         
            ps.setDate(4, sqlDate);
            ps.setString(1, aposta.getDescricao());  
            ps.setDouble(3, aposta.getOdd());
            ps.setInt(2, aposta.getIdDeEvento());
            ps.setString(5, "pendente");  //por enquanto o status vai começar como pendente!!
            ps.executeUpdate();
            System.out.println("Aposta inserida com sucesso!");
            ps.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Aposta> ListarApostasPorEventoId(int EventoId){
		
		String sql = "SELECT * FROM aposta WHERE iddeevento = ?";
		
		ArrayList<Aposta> apostas = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, EventoId);
        	ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aposta aposta = new Aposta();
                aposta.setId(rs.getInt("id"));
                aposta.setStatus(rs.getString("status"));
                aposta.setResultado(rs.getString("resultado"));
                aposta.setDataDeCriacao(rs.getDate("datadecriacao"));
                aposta.setOdd(rs.getDouble("odd"));
                aposta.setDescricao(rs.getString("descricao"));
                apostas.add(aposta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Aposta aposta : apostas) {
        	System.out.println(aposta);
        }
        return apostas;
	}
	
	public boolean deleteById(int id) {
		String sql = "DELETE FROM aposta WHERE id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Aposta deletado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editarOdd(int id, double odd) {
		String sql = "UPDATE aposta SET odd = ? WHERE id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setDouble(1, odd);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Aposta atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Aposta> listarTodasApostas() {
		String sql = "SELECT * FROM Aposta";
		ArrayList<Aposta> apostas = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	//não coloquei permissão aqui, não acho que seja necessário que o usuário saiba
                Aposta aposta = new Aposta();
                aposta.setId(rs.getInt("id"));
                aposta.setOdd(rs.getDouble("odd"));
                aposta.setDescricao(rs.getString("descricao"));
                aposta.setDataDeCriacao(rs.getDate("datadecriacao"));
                apostas.add(aposta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apostas;
    }
	
	public Aposta findApostaById(int idAposta) {
	    String sql = "SELECT * FROM Aposta WHERE id = ?";
	    Aposta aposta = null;

	    try (PreparedStatement ps = conn.prepareStatement(sql)) { 
	        ps.setInt(1, idAposta);
	        try (ResultSet rs = ps.executeQuery()) {  
	            if (rs.next()) {  
	                aposta = new Aposta();
	                aposta.setId(rs.getInt("id"));
	                aposta.setOdd(rs.getDouble("odd"));
	                aposta.setDescricao(rs.getString("descricao"));
	                aposta.setDataDeCriacao(rs.getDate("datadecriacao"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return aposta;
	}
	
	public boolean editarDescricao(int id, String descricao) {
		String sql = "UPDATE aposta SET descricao = ? WHERE id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, descricao);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Aposta atualizado com sucesso!");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Aposta> findApostasByBilheteId(int idBilhete) {
		System.out.println("entrou! 3");
        ArrayList<Aposta> apostas = new ArrayList<Aposta>();
        String sql = "SELECT Aposta.* FROM Aposta " +
                     "JOIN Bilhete_Aposta ON Aposta.id = Bilhete_Aposta.idAposta " +
                     "WHERE Bilhete_Aposta.idBilhete = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBilhete);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Aposta aposta = new Aposta();
                    aposta.setId(rs.getInt("id"));
                    aposta.setDescricao(rs.getString("descricao"));
                    aposta.setIdDeEvento(rs.getInt("idDeEvento"));
                    aposta.setOdd(rs.getDouble("odd"));
                    aposta.setDataDeCriacao(rs.getDate("dataDeCriacao"));
                    aposta.setStatus(rs.getString("status"));
                    aposta.setResultado(rs.getString("resultado"));
                    apostas.add(aposta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //teste
        for(Aposta a : apostas) {
        	System.out.println(a);
        }
        return apostas;
    }
}
