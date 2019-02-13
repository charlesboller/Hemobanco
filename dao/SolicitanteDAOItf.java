package dao;

import java.util.List;

import exception.UsuarioException;
import model.Solicitante;

public interface SolicitanteDAOItf {

	public List<Solicitante> listAll() throws UsuarioException;
	public void save(Solicitante solicitante) throws UsuarioException;
	public void update(Solicitante solicitante) throws UsuarioException;
	public Solicitante selectNome(String nome) throws UsuarioException;
}

