package dao;

import java.util.List;

import exception.UsuarioException;
import model.Solicitacao;


public interface SolicitacaoDAOItf {
	public List<Solicitacao> listAll() throws UsuarioException;
	public void save(Solicitacao solicitacao) throws UsuarioException;
	public Solicitacao select(int id) throws UsuarioException;
	public void update(Solicitacao solicitacao) throws UsuarioException;

}
