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
}
