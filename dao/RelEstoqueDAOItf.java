package dao;

import java.util.List;
import exception.UsuarioException;
import model.RelEstoque;
import model.RelSolicitar;
import model.SangueDisponivel;


public interface RelEstoqueDAOItf {
	
	public List<RelEstoque> listAll() throws UsuarioException;
	public List<RelSolicitar> listAllSolicitar(int tiposangue) throws UsuarioException;
	public List<SangueDisponivel> listarTodos() throws UsuarioException;
	public List<SangueDisponivel> listarDoadores() throws UsuarioException;

}
