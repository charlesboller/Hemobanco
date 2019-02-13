package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.UsuarioException;
import model.Solicitante;

public class SolicitanteDAOImpl implements SolicitanteDAOItf{
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";

	public List<Solicitante> listAll() throws UsuarioException {
		List<Solicitante> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM SOLICITANTE");
			while(rs.next()) {
				lista.add(new Solicitante(rs.getInt("ID_SOLICITANTE"), rs.getString("NOME_SOLICITANTE"), rs.getString("CNPJ_SOLICITANTE"), rs.getString("ENDERECO_SOLICITANTE"), rs.getString("TEL_SOLICITANTE")));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new UsuarioException(e.getMessage());
		}
		
		return lista;
	}

	public void save(Solicitante solicitante) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO SOLICITANTE VALUES (?,?,?,?)");
			
			stmt.setString(1, solicitante.getNomeSolicitante());
			stmt.setString(2, solicitante.getCnpjSolicitante());
			stmt.setString(3, solicitante.getEnderecoSolicitante());
			stmt.setString(4, solicitante.getTelSolicitante());
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}


	public void update(Solicitante solicitante) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("UPDATE SOLICITANTE SET "
					                                    + " NOME_SOLICITANTE = ?, "
					                                    + " CNPJ_SOLICITANTE = ?, "
					                                    + " ENDERECO_SOLICITANTE = ?, "
					                                    + " TEL_SOLICITANTE = ? "
					                                    + " WHERE ID_SOLICITANTE = ?");
					
			stmt.setString(1, solicitante.getNomeSolicitante());
			stmt.setString(2, solicitante.getCnpjSolicitante());
			stmt.setString(3, solicitante.getEnderecoSolicitante());
			stmt.setString(4, solicitante.getTelSolicitante());
			stmt.setInt   (5, solicitante.getIdSolicitante());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			
			
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
		
	}

	@Override
	public Solicitante selectNome(String nome) throws UsuarioException {
		Solicitante solicitante = new Solicitante();
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM SOLICITANTE WHERE NOME_SOLICITANTE like ?");
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				solicitante.setIdSolicitante(rs.getInt("id_solicitante"));
				solicitante.setNomeSolicitante(rs.getString("nome_solicitante"));
				solicitante.setCnpjSolicitante(rs.getString("cnpj_solicitante"));
				solicitante.setEnderecoSolicitante(rs.getString("endereco_solicitante"));
				solicitante.setTelSolicitante(rs.getString("tel_solicitante"));	
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
				
		return solicitante;
	}

}
