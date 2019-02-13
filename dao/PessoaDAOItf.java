package dao;

import java.util.List;
import exception.UsuarioException;
import model.Pessoa;

public interface PessoaDAOItf {

	public List<Pessoa> listAll() throws UsuarioException;
	public void save(Pessoa pessoa) throws UsuarioException;
	public void delete(int id) throws UsuarioException;
	public Pessoa select(String cpf) throws UsuarioException;
	public Pessoa selectNome(String nome) throws UsuarioException;
	public void update(Pessoa pessoa) throws UsuarioException;
	
	
}
