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
                aposta.setIdDeEvento(rs.getInt("iddeevento"));
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
}
