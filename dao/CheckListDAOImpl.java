package dao;

import java.sql.*;
import exception.UsuarioException;
import model.Consulta;


public class CheckListDAOImpl implements CheckListDAOItf {
	
	java.util.Date date = new java.util.Date();
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";

	@Override
	public void verificar(int idpessoa) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("declare @DATA DATETIME "
					+ "Select @DATA = MAX(DATA_DOADOR) from DOADOR "
					+ "WHERE ID_PESSOA = ? "
					+ "if @DATA IS NULL "
					+ "BEGIN "
					+ "SET @DATA = '1900-01-01' "
					+ "END "
					+ "update CONSULTA "
					+ "SET APTO_CONSULTA = CASE WHEN "
					+ "GRAVIDEZ_CONSULTA = 0  AND "
					+ "GRIPE_CONSULTA = 0 AND "
					+ "TATUAGEM_CONSULTA = 0 AND "
					+ "CIRURGIA_CONSULTA = 0 AND "
					+ "DIT_CONSULTA = 0 AND "
					+ "DROGAS_CONSULTA = 0 AND "
					+ "PESO_CONSULTA >= 50 AND "
					+ "ALTURA_CONSULTA >= 1.5 AND "
					+ "FLOOR(DATEDIFF(DAY, NASCIMENTO_CONSULTA, GETDATE()) / 365.25) >= 18 AND "
					+ "FLOOR(DATEDIFF(DAY, @DATA, GETDATE())) >= 180 "
					+ "THEN 1 ELSE 0 END "
					+ "WHERE ID_PESSOA = ?");
			
			
			stmt.setInt(1, idpessoa);
			stmt.setInt(2, idpessoa);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			
			
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	@Override
	public void save(Consulta consulta) throws UsuarioException {
		// TODO Auto-generated method stub
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("insert into CONSULTA values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setInt(1, consulta.getIdPessoa());
			java.sql.Date date = (java.sql.Date) consulta.getNascimento();
			stmt.setDate(2, date);
			stmt.setFloat(3, consulta.getAltura());
			stmt.setFloat(4, consulta.getPeso());
			stmt.setBoolean(5, consulta.isGravidez());
			stmt.setBoolean(6, consulta.isGripe());
			stmt.setBoolean(7, consulta.isTatuagem());
			stmt.setBoolean(8, consulta.isCirurgia());
			stmt.setBoolean(9, consulta.isDit());
			stmt.setBoolean(10, consulta.isDrogas());
			stmt.setBoolean(11, consulta.isApto());
			stmt.setInt(12, consulta.getIdUsuario());
			stmt.setTimestamp(13,getCurrentTimeStamp());
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	@Override
	public Consulta select(int idpessoa) throws UsuarioException {
		// TODO Auto-generated method stub
		Consulta consulta = new Consulta();
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("select * from CONSULTA where id_pessoa = ?");
			stmt.setInt(1, idpessoa);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				consulta.setId(rs.getInt("ID_CONSULTA"));
				consulta.setIdPessoa(rs.getInt("ID_PESSOA"));
				consulta.setNascimento(rs.getDate("nascimento_CONSULTA"));
				consulta.setAltura(rs.getFloat("altura_CONSULTA"));
				consulta.setPeso(rs.getFloat("peso_CONSULTA"));
				consulta.setGravidez(rs.getBoolean("gravidez_CONSULTA"));
				consulta.setGripe(rs.getBoolean("gripe_CONSULTA"));
				consulta.setTatuagem(rs.getBoolean("tatuagem_CONSULTA"));
				consulta.setCirurgia(rs.getBoolean("cirurgia_CONSULTA"));
				consulta.setDit(rs.getBoolean("dit_CONSULTA"));
				consulta.setDrogas(rs.getBoolean("drogas_CONSULTA"));
				consulta.setApto(rs.getBoolean("apto_CONSULTA"));
				consulta.setIdUsuario(rs.getInt("id_usuario"));
				consulta.setData(rs.getDate("datareg_CONSULTA"));

			}
			rs.close();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
		return consulta;
	}

	@Override
	public void update(Consulta consulta) throws UsuarioException {
		// TODO Auto-generated method stub
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("UPDATE CONSULTA "
					+ "SET NASCIMENTO_CONSULTA = ?, ALTURA_CONSULTA = ?, PESO_CONSULTA = ?, "
					+ "GRAVIDEZ_CONSULTA = ?, GRIPE_CONSULTA = ?, TATUAGEM_CONSULTA = ?, CIRURGIA_CONSULTA = ?, "
					+ "DIT_CONSULTA = ?, DROGAS_CONSULTA = ?, APTO_CONSULTA = ?, ID_USUARIO= ?, DATAREG_CONSULTA = ? WHERE ID_PESSOA = ?");
			
			
			java.sql.Date date = (java.sql.Date) consulta.getNascimento();
			stmt.setDate(1, date);
			stmt.setFloat(2, consulta.getAltura());
			stmt.setFloat(3, consulta.getPeso());
			stmt.setBoolean(4, consulta.isGravidez());
			stmt.setBoolean(5, consulta.isGripe());
			stmt.setBoolean(6, consulta.isTatuagem());
			stmt.setBoolean(7, consulta.isCirurgia());
			stmt.setBoolean(8, consulta.isDit());
			stmt.setBoolean(9, consulta.isDrogas());
			stmt.setBoolean(10, consulta.isApto());
			stmt.setInt(11, consulta.getIdUsuario());
			stmt.setTimestamp(12,getCurrentTimeStamp());
			stmt.setInt(13, consulta.getIdPessoa());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			
			
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}

	}

	private static java.sql.Timestamp getCurrentTimeStamp() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
	}

	

}
