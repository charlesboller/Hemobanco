package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import exception.UsuarioException;
import model.Solicitacao;

public class SolicitacaoDAOImpl implements SolicitacaoDAOItf{
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";


	@Override
	public List<Solicitacao> listAll() throws UsuarioException {
		
		
		return null;
	}

	@Override
	public void save(Solicitacao receptor) throws UsuarioException {
		// TODO Auto-generated method stub
		
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO SOLICITACAO VALUES(?,?,?)");
			
			stmt.setTimestamp(1,getCurrentTimeStamp());
			stmt.setInt(2, receptor.getIdSolicitante());
			stmt.setInt(3, receptor.getIdExame());
			
			stmt.executeUpdate();
			
			
			//Altera tabela doador informando que sangue foi usado
			int idExame = receptor.getIdExame();
			stmt = con.prepareStatement("UPDATE EXAME SET DISPONIVELSANGUE_EXAME = 0 WHERE ID_EXAME = " + idExame);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}

	@Override
	public Solicitacao select(int id) throws UsuarioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Solicitacao receptor) throws UsuarioException {
		// TODO Auto-generated method stub
		
	}
	
	
	private static java.sql.Timestamp getCurrentTimeStamp() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
	}
	

}
