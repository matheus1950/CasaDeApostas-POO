package dao;

import java.sql.SQLException;

public interface PessoaDao {

	void deleteById(int id) throws SQLException;

	String findPasswordByPessoaEmail(String email);

	void deleteByPessoaId(int id) throws SQLException;

	String findPasswordByEmail(String email);

}
