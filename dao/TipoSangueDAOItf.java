package dao;

import java.util.List;
import exception.UsuarioException;
import model.TipoSangue;

public interface TipoSangueDAOItf {

	public List<TipoSangue> listAll() throws UsuarioException;
	public TipoSangue select(String id) throws UsuarioException;
	
	
}
