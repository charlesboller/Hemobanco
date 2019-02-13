package dao;

import java.util.List;
import exception.UsuarioException;
import model.Doador;

public interface DoadorDAOItf {
	
		public List<Doador> listAll(int tiposangue) throws UsuarioException;
		public List<Doador> listExame() throws UsuarioException;
		public void save(Doador doador) throws UsuarioException;
		public Doador select(int id) throws UsuarioException;
		public Doador selectPessoa(int id) throws UsuarioException;
		public void update(Doador doador) throws UsuarioException;
		public void disponivel(int id) throws UsuarioException;

}
