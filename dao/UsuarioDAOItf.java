package dao;

import java.util.List;
import exception.UsuarioException;
import model.Usuario;

public interface UsuarioDAOItf {

	public List<Usuario> listAll() throws UsuarioException;
	public void save(Usuario usuario) throws UsuarioException;
	public void delete(int id) throws UsuarioException;
	public Usuario select(String id) throws UsuarioException;
	public void update(Usuario usuario) throws UsuarioException;
	public void alterarid(String id) throws UsuarioException;
	
}
