package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.UsuarioException;
import model.Exame;

public class ExameDAOImpl implements ExameDAOItf{
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";
	
	
//	public UsuarioDAOImpl() {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public List<Exame> listAll(int tiposangue) throws UsuarioException {
		// TODO Auto-generated method stub
		
		
		List<Exame> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT E.ID_EXAME, D.data_doador, e.id_tiposangue     "
					                       + "  FROM EXAME E                                        "
					                       + "  JOIN DOADOR D ON E.ID_DOADOR = D.ID_DOADOR          "
					                       + " WHERE E.ID_TIPOSANGUE IN (                           "
					                       + "                            SELECT ID_TIPOSANGUE      "
					                       + "                              from TIPOSANGUERECEPTOR "
					                       + "                             where ID_RECEPTOR =      " 
					                                                                   + tiposangue
					                       + "                           )                          "
					                       + "   and E.DISPONIVELSANGUE_EXAME = 1                   "
					                       + " ORDER BY D.data_doador                               ");
			
			while(rs.next()) {
				lista.add(new Exame(rs.getInt("ID_EXAME"), rs.getDate("data_doador"), rs.getInt("id_tiposangue")));
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
	public void save(Exame exame) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("insert into exame values(?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setInt(1, exame.getGlicoseExame());
			stmt.setInt(2, exame.getColesterolExame());
			stmt.setInt(3, exame.getHemaciasExame());
			stmt.setInt(4, exame.getLeucocitosExame());
			stmt.setInt(5, exame.getPlaquetasExame());
			stmt.setBoolean(6, exame.getPossuiDITExame());
			stmt.setBoolean(7, exame.getAptoExame());
			stmt.setBoolean(8, exame.getDisponivelSangueExame());
			stmt.setInt(9, exame.getIdTipoSangue());
			stmt.setInt(10, exame.getIdDoador());

			stmt.executeUpdate();

			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		
	}


	@Override
	public void update(Exame exame) throws UsuarioException {
		// TODO Auto-generated method stub
		/*	
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("UPDATE EXAME SET ID_USUARIO = ?, NOME_USUARIO = ?, SENHA_USUARIO = ?, NIVEL_USUARIO = ? WHERE ID_USUARIO = ?");
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
		*/
	}








}
