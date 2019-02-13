package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.UsuarioException;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAOItf{
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";
	private String idGlobal;
	
//	public UsuarioDAOImpl() {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public List<Usuario> listAll() throws UsuarioException {
		// TODO Auto-generated method stub
		List<Usuario> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from usuario");
			while(rs.next()) {
				lista.add(new Usuario(rs.getInt("id_usuario"), rs.getString("nome_usuario"), rs.getString("senha_usuario"), rs.getInt("nivel_usuario")));
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
	public void save(Usuario usuario) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("insert into usuario values(?,?,?,?)");
			
			stmt.setInt(1, usuario.getId());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());
			stmt.setInt(4, usuario.getNivel());
			stmt.executeUpdate();

			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}

	@Override
	public void delete(int id) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("delete from usuario where id_usuario = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			} catch(SQLException e) {
				throw new UsuarioException(e.getMessage());
			}
		
	}
	
	@Override
	public Usuario select(String id) throws UsuarioException {
		// TODO Auto-generated method stub
		
		Usuario usuario = new Usuario();
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("select * from usuario where id_usuario = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setSenha(rs.getString("senha_usuario"));
				usuario.setNivel(rs.getInt("nivel_usuario"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
		return usuario;
		
		
	}

	@Override
	public void update(Usuario usuario) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("UPDATE USUARIO SET ID_USUARIO = ?, NOME_USUARIO = ?, SENHA_USUARIO = ?, NIVEL_USUARIO = ? WHERE ID_USUARIO = ?");
			stmt.setInt(1, usuario.getId());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());
			stmt.setInt(4, usuario.getNivel());
			stmt.setString(5, idGlobal);
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}

	@Override
	public void alterarid(String id) throws UsuarioException {
		idGlobal = id;
		
	}







}
