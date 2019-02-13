package dao;

import java.sql.*;
import java.util.List;
import exception.UsuarioException;
import model.Pessoa;

public class PessoaDAOImpl implements PessoaDAOItf{
	
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=dbhemobanco;user=sa;password=123456";

	@Override
	public List<Pessoa> listAll() throws UsuarioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Pessoa pessoa) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("insert into pessoa values(?,?,?,?,?,?,?,?,?,?,?,?)");

			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getBairro());
			stmt.setString(6, pessoa.getCidade());
			stmt.setString(7, pessoa.getEstado());
			stmt.setString(8, pessoa.getCep());
			stmt.setString(9, pessoa.getCelular());
			stmt.setString(10, pessoa.getTelefone());
			stmt.setInt(11, pessoa.getIdUsuario());
			stmt.setTimestamp(12,getCurrentTimeStamp());
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws UsuarioException {
		
	}

	@Override
	public Pessoa select(String cpf) throws UsuarioException {
		// TODO Auto-generated method stub
		Pessoa pessoa = new Pessoa();
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("select * from pessoa where cpf_pessoa = ?");
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				pessoa.setId(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome_pessoa"));
				pessoa.setCpf(rs.getString("cpf_pessoa"));
				pessoa.setEmail(rs.getString("email_pessoa"));
				pessoa.setEndereco(rs.getString("endereco_pessoa"));
				pessoa.setBairro(rs.getString("bairro_pessoa"));
				pessoa.setCidade(rs.getString("cidade_pessoa"));
				pessoa.setCelular(rs.getString("cel_pessoa"));
				pessoa.setTelefone(rs.getString("tel_pessoa"));
				pessoa.setEstado(rs.getString("estado_pessoa"));
				pessoa.setIdUsuario(rs.getInt("id_usuario"));
				pessoa.setData(rs.getDate("data_pessoa"));
				pessoa.setCep(rs.getString("cep_pessoa"));
				
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}	
		return pessoa;
		
	}

	@Override
	public void update(Pessoa pessoa) throws UsuarioException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("UPDATE PESSOA "
					+ "SET NOME_PESSOA = ?, CPF_PESSOA = ?, EMAIL_PESSOA = ?, ENDERECO_PESSOA = ?, "
					+ "BAIRRO_PESSOA = ?, CIDADE_PESSOA = ?, ESTADO_PESSOA = ?, CEP_PESSOA = ?, "
					+ "CEL_PESSOA = ?,TEL_PESSOA = ?, ID_USUARIO = ?, DATA_PESSOA = ? WHERE ID_PESSOA = ?");

			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getBairro());
			stmt.setString(6, pessoa.getCidade());
			stmt.setString(7, pessoa.getEstado());
			stmt.setString(8, pessoa.getCep());
			stmt.setString(9, pessoa.getCelular());
			stmt.setString(10, pessoa.getTelefone());
			stmt.setInt(11, pessoa.getIdUsuario());
			stmt.setTimestamp(12,getCurrentTimeStamp());
			stmt.setInt(13, pessoa.getId());
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

	@Override
	public Pessoa selectNome(String nome) throws UsuarioException {
		// TODO Auto-generated method stub
		Pessoa pessoa = new Pessoa();
		
		try {
			Connection con = DriverManager.getConnection(connection);
			PreparedStatement stmt = con.prepareStatement("select * from pessoa where nome_pessoa like ?");
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				pessoa.setId(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome_pessoa"));
				pessoa.setCpf(rs.getString("cpf_pessoa"));
				pessoa.setEmail(rs.getString("email_pessoa"));
				pessoa.setEndereco(rs.getString("endereco_pessoa"));
				pessoa.setBairro(rs.getString("bairro_pessoa"));
				pessoa.setCidade(rs.getString("cidade_pessoa"));
				pessoa.setCelular(rs.getString("cel_pessoa"));
				pessoa.setTelefone(rs.getString("tel_pessoa"));
				pessoa.setEstado(rs.getString("estado_pessoa"));
				pessoa.setIdUsuario(rs.getInt("id_usuario"));
				pessoa.setData(rs.getDate("data_pessoa"));
				pessoa.setCep(rs.getString("cep_pessoa"));
				
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
				
		return pessoa;
	}

}
