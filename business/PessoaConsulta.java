package business;

import java.awt.Component;

import javax.swing.JOptionPane;

import dao.PessoaDAOImpl;
import dao.PessoaDAOItf;
import exception.UsuarioException;
import model.Pessoa;

public class PessoaConsulta {
	
	PessoaDAOItf dao = new PessoaDAOImpl();
	Pessoa pessoa = new Pessoa();
	Component frame;
	
	public Pessoa consultaPessoaCpf(String cpf) {
		try {
			if (cpf.equals(dao.select(cpf).getCpf())){
				pessoa = dao.select(cpf);
			} else {
				JOptionPane.showMessageDialog(frame, "CPF não localizado!", "Erro", JOptionPane.ERROR_MESSAGE);
				
			}
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pessoa;	
	}
	
	public Pessoa consultaPessoaNome(String nome){
		
		try {
			
				pessoa = dao.selectNome(nome);
				if (pessoa.getCpf() == null){
					JOptionPane.showMessageDialog(frame, "Nome não localizado!", "Erro", JOptionPane.ERROR_MESSAGE);
				}

		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pessoa;
	}

}
