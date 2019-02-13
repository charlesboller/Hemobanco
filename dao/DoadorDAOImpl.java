package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import exception.UsuarioException;
import model.Doador;

public class DoadorDAOImpl implements DoadorDAOItf {
	
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";

	@Override
	public List<Doador> listAll(int tiposangue) throws UsuarioException {
		// TODO Auto-generated method stub
		
		
		List<Doador> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DOADOR "
					+ "WHERE ID_TIPOSANGUE IN ("
					+ "SELECT ID_TIPOSANGUE from TIPOSANGUERECEPTOR where ID_RECEPTOR = " 
					+ tiposangue
					+ ") and DISPONIVEL_DOADOR = 1 ORDER BY DATA_DOADOR");
			while(rs.next()) {
				lista.add(new Doador(rs.getInt("id_doador"), rs.getDate("data_doador"), rs.getInt("id_tiposangue"), rs.getBoolean("disponivel_doador"), rs.getInt("id_pessoa")));
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

	@Override
	public void save(Doador doador) throws UsuarioException {
		// TODO Auto-generated method stub
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("insert into doador values(?,?,?,?)");
			
			stmt.setTimestamp(1,getCurrentTimeStamp());
			stmt.setInt(2, doador.getIdTipoSangue());
			stmt.setBoolean(3, doador.isDisponivelDoador());
			stmt.setInt(4, doador.getIdPessoa());
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}

	@Override
	public Doador select(int id) throws UsuarioException {
		// TODO Auto-generated method stub
		
		Doador doador = new Doador();
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("select * from doador where id_doador = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				doador.setIdDoador(rs.getInt("id_doador"));
				doador.setDataDoador(rs.getDate("data_doador"));
				doador.setIdTipoSangue(rs.getInt("id_tiposangue"));
				doador.setDisponivelDoador(rs.getBoolean("disponivel_doador"));
				doador.setIdPessoa(rs.getInt("id_pessoa"));
			}
			rs.close();
			stmt.close();
			con.close();
			
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		return doador;
	}

	@Override
	public void update(Doador doador) throws UsuarioException {
		
	}
	
	private static java.sql.Timestamp getCurrentTimeStamp() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
	}

	@Override
	public Doador selectPessoa(int id) throws UsuarioException {
		Doador doador = new Doador();
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("select * from doador where id_pessoa = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				doador.setIdDoador(rs.getInt("id_doador"));
				doador.setDataDoador(rs.getDate("data_doador"));
				doador.setIdTipoSangue(rs.getInt("id_tiposangue"));
				doador.setDisponivelDoador(rs.getBoolean("disponivel_doador"));
				doador.setIdPessoa(rs.getInt("id_pessoa"));
			}
			rs.close();
			stmt.close();
			con.close();
			
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		return doador;
	}

	public List<Doador> listExame() throws UsuarioException {
		
		List<Doador> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DOADOR "
					+ " WHERE DISPONIVEL_DOADOR = 0 ORDER BY DATA_DOADOR");
			while(rs.next()) {
				lista.add(new Doador(rs.getInt("id_doador"), rs.getDate("data_doador"), rs.getInt("id_tiposangue"), rs.getBoolean("disponivel_doador"), rs.getInt("id_pessoa")));
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

	@Override
	public void disponivel(int id) throws UsuarioException {
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("UPDATE DOADOR SET DISPONIVEL_DOADOR = 1 WHERE ID_DOADOR = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}
}
