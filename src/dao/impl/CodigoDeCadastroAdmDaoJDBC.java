package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CodigoDeCadastroAdmDao;
import db.DB;
import entidades.Pessoa;

public class CodigoDeCadastroAdmDaoJDBC implements CodigoDeCadastroAdmDao {
	private Connection conn = DB.getConnection();
	
	public Boolean findCodigoAdmById(String idCodigo) {
		String sql = "SELECT * FROM Codigodecadastroadm";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {	              
	        try (ResultSet rs = ps.executeQuery()) {
	        	while(rs.next()) {
	        		if(idCodigo.equals(rs.getString("codigodecadastroadm"))) {
	        			return true;
	        		}
	        	}         
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false; 
	}
}
