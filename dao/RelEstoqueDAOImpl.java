package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import exception.UsuarioException;
import model.RelEstoque;
import model.RelSolicitar;
import model.SangueDisponivel;


public class RelEstoqueDAOImpl implements RelEstoqueDAOItf {
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";

	@Override
	public List<RelEstoque> listAll() throws UsuarioException {
		// TODO Auto-generated method stub
		
		List<RelEstoque> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			
			
			
			ResultSet rs = stmt.executeQuery("select E.ID_EXAME, D.data_doador, e.id_tiposangue from EXAME E INNER JOIN DOADOR D ON E.ID_DOADOR = D.ID_DOADOR where e.DISPONIVELSANGUE_EXAME = 1");
			while(rs.next()) {
				lista.add(new RelEstoque(rs.getInt("id_exame"), rs.getDate("data_doador"), rs.getInt("id_tiposangue")));
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
	public List<RelSolicitar> listAllSolicitar(int tiposangue) throws UsuarioException {
		// TODO Auto-generated method stub
		
		List<RelSolicitar> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT P.ID_PESSOA, P.NOME_PESSOA, P.EMAIL_PESSOA, P.TEL_PESSOA, E.ID_TIPOSANGUE \r\n" + 
					                         "  FROM PESSOA P                                                                           \r\n" + 
					                         "  LEFT JOIN DOADOR D ON P.ID_PESSOA = D.ID_PESSOA                                         \r\n" + 
					                         "  LEFT JOIN EXAME E ON E.ID_DOADOR = D.ID_DOADOR                                          \r\n" + 
					                         "  WHERE P.ID_PESSOA NOT IN (SELECT D.ID_PESSOA FROM DOADOR D                              \r\n" + 
					                         "                             WHERE D.DATA_DOADOR >= DATEADD(MONTH, -1, GETDATE())         \r\n" + 
					                         "  						 )                                                              \r\n" + 
					                         "    AND E.ID_TIPOSANGUE IN ( SELECT ID_TIPOSANGUE                                         \r\n" + 
					                         "                               from TIPOSANGUERECEPTOR                                    \r\n" + 
					                         "                              where ID_RECEPTOR =                                         \r\n" + 
					                                                                            tiposangue +
					                         "					         )                                                              \r\n");
			while(rs.next()) {
				lista.add(new RelSolicitar(rs.getInt("id_pessoa"), rs.getString("nome_pessoa"), rs.getString("email_pessoa"), rs.getString("tel_PESSOA"), rs.getInt("id_tiposangue")));
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
	public List<SangueDisponivel> listarTodos() throws UsuarioException {
		List<SangueDisponivel> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT T.ID_TIPOSANGUE, (SELECT ISNULL(SUM(1),0)             " + 
					                          "                           FROM EXAME                        " + 
					                          "						  WHERE ID_TIPOSANGUE = T.ID_TIPOSANGUE " + 
					                          "						    AND APTO_EXAME = 1) SOMA            " + 
					                          "  FROM TIPOSANGUE T                                          " + 
					                          " ORDER BY T.ID_TIPOSANGUE                                    ");
			while(rs.next()) {
				lista.add(new SangueDisponivel(rs.getInt("ID_TIPOSANGUE"), rs.getInt("SOMA")));
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
	public List<SangueDisponivel> listarDoadores() throws UsuarioException {
		List<SangueDisponivel> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(connection);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(  " SELECT X.ID_TIPOSANGUE, SUM(X.SOMA) SOMA                                           " +
                                               "   FROM(                                                                            " + 
                                               "       SELECT DISTINCT P.ID_PESSOA, E.ID_TIPOSANGUE, 1 SOMA                         " + 
                                               "         FROM PESSOA P                                                              " + 
                                               "         LEFT JOIN DOADOR D ON D.ID_PESSOA = P.ID_PESSOA                            " + 
                                               "         LEFT JOIN EXAME E ON E.ID_DOADOR = D.ID_DOADOR                             " + 
                                               "        WHERE COALESCE (DATEADD(MONTH,-3,D.DATA_DOADOR),GETDATE ())  <=  GETDATE () " + 
                                               "          AND E.ID_TIPOSANGUE IS NOT NULL                                           " + 
                                               "          AND E.APTO_EXAME = 1                                                      " + 
                                               "     UNION                                                                          " + 
                                               "       SELECT DISTINCT P.ID_PESSOA, 9 ID_TIPOSANGUE, 1 SOMA                         " + 
                                               "         FROM PESSOA P                                                              " + 
                                               "         LEFT JOIN CONSULTA C ON C.ID_PESSOA = P.ID_PESSOA                          " + 
                                               "         LEFT JOIN DOADOR D ON D.ID_PESSOA = P.ID_PESSOA                            " + 
                                               "         LEFT JOIN EXAME E ON E.ID_DOADOR = D.ID_DOADOR                             " + 
                                               "        WHERE COALESCE (DATEADD(MONTH,-3,D.DATA_DOADOR),GETDATE ())  <=  GETDATE () " + 
                                               "          AND E.ID_TIPOSANGUE IS NULL                                               " + 
                                               "          AND COALESCE(C.DIT_CONSULTA,0)  = 0                                       " +
                                               "       ) X                                                                          " + 
                                               "  GROUP BY X.ID_TIPOSANGUE                                                          ");

			while(rs.next()) {
				lista.add(new SangueDisponivel(rs.getInt("ID_TIPOSANGUE"), rs.getInt("SOMA")));
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
	

}
