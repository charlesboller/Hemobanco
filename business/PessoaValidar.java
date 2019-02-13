package business;

import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import dao.PessoaDAOImpl;
import dao.PessoaDAOItf;
import exception.UsuarioException;
import model.Pessoa;

public class PessoaValidar {

	Component frame = null;
	Pessoa pessoa = new Pessoa();
	PessoaDAOItf dao = new PessoaDAOImpl();

	
	
	public void gravarCadastro() {

		try {

			dao.save(pessoa);
			JOptionPane.showMessageDialog(frame, "Cadastro SALVO com sucesso");

		} catch (HeadlessException | UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean Validar(String strCpf) {

		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;
		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;
		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
			d1 = d1 + (11 - nCount) * digitoCPF;
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		resto = (d1 % 11);
		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;
		d2 += 2 * digito1;
		resto = (d2 % 11);
		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;
		if (strCpf.length() >= 2) {
			String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
			return nDigVerific.equals(nDigResult);
		} else {
			return false;
		}
	}

	// importa o objeto e verifica se campos preenchidos e cpf correto?
	public boolean camposPreenchidos(Pessoa pessoaTela) {
		pessoa = (Pessoa) pessoaTela;
		

		if (pessoa.getNome().equals("") || pessoa.getCpf().equals("") || pessoa.getEndereco().equals("")
				|| pessoa.getEmail().equals("") || pessoa.getBairro().equals("") || pessoa.getCidade().equals("")
				|| pessoa.getCep().equals("")

		) {
			JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {

			if (Validar(pessoa.getCpf()) == true) {

				return true;

			} else {
				JOptionPane.showMessageDialog(frame, "CPF INVALIDO!", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}

		}
	}

	//Já existe CPF no Banco de dados?
	public boolean cpfExiste(Pessoa pessoaTela) {
		pessoa = (Pessoa) pessoaTela;
		try {
			if (pessoa.getCpf().equals(dao.select(pessoa.getCpf()).getCpf())) {
				JOptionPane.showMessageDialog(frame, "CPF ja cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
				return true;

			} else {
				return false;
			}
		} catch (HeadlessException | UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	public void alterarPessoa(Pessoa pessoaTela) {
		pessoa = (Pessoa) pessoaTela;
		try {
			dao.update(pessoa);
			JOptionPane.showMessageDialog(frame, "Cadastro ALTERADO com sucesso");
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
