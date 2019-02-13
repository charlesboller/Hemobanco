package dao;

import exception.UsuarioException;
import model.Consulta;

public interface CheckListDAOItf {
	
	public void verificar(int idpessoa) throws UsuarioException;
	public void save(Consulta checklist) throws UsuarioException;
	public Consulta select(int id) throws UsuarioException;
	public void update(Consulta checklist) throws UsuarioException;

}
