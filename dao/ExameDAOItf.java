package dao;

import java.util.List;
import exception.UsuarioException;
import model.Exame;

public interface ExameDAOItf {

	public List<Exame> listAll(int tiposangue) throws UsuarioException;
	public void save(Exame exame) throws UsuarioException;
	public void update(Exame exame) throws UsuarioException;
	
}
