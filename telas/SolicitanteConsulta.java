package telas;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import business.PessoaValidar;
import dao.PessoaDAOImpl;
import dao.PessoaDAOItf;
import dao.SolicitanteDAOImpl;
import dao.SolicitanteDAOItf;
import exception.UsuarioException;
import model.Pessoa;
import model.Solicitante;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyVetoException;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SolicitanteConsulta extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JTextField textFieldTelefone;
	private JTextField textFieldConsulta;
	private int idPessoa;
	PessoaDAOItf dao = new PessoaDAOImpl();
	Pessoa pessoa = new Pessoa();
	PessoaValidar regras = new PessoaValidar();

	public SolicitanteConsulta(int id) {
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBounds(12, 146, 960, 305);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 13, 960, 118);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BuscarSolicitante();
				} catch (UsuarioException e) {
					e.printStackTrace();
				}

			}
		});

		JLabel lblConsultaSolicitante = new JLabel("Consulta Solicitante:");
		lblConsultaSolicitante.setFont(new Font("Tahoma", Font.BOLD, 20));

		textFieldConsulta = new JTextField();
		textFieldConsulta.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblConsultaSolicitante, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textFieldConsulta, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton)
							.addGap(82))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(852, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConsultaSolicitante, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(28))
		);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("Nome Completo:");

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);

		JLabel lblCpf = new JLabel("CNPJ:");

		textFieldCpf = new JTextField();
		textFieldCpf.setEditable(false);
		textFieldCpf.setColumns(10);

		JLabel lblLogradouro = new JLabel("Logradouro:");

		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GravarSolicitante();
				} catch (UsuarioException e) {
					e.printStackTrace();
				}


			}
		});
		getContentPane().setLayout(null);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAlterar)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblCpf)
								.addComponent(lblLogradouro)
								.addComponent(lblTelefone))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldEndereco, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(81))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogradouro))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefone))
					.addGap(83)
					.addComponent(btnAlterar)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		getContentPane().add(panel_1);

	}

	public void limparCampos() {
		textFieldNome.setText(null);
		textFieldCpf.setText(null);
		textFieldEndereco.setText(null);
		textFieldTelefone.setText(null);
	}
	
	public void BuscarSolicitante() throws UsuarioException {
		Solicitante solicitante = new Solicitante();
		limparCampos();
		SolicitanteDAOItf dao = new SolicitanteDAOImpl();
		
		solicitante = dao.selectNome(textFieldConsulta.getText());
		
		if (solicitante != null) {

			idPessoa = solicitante.getIdSolicitante();
			textFieldNome.setText(solicitante.getNomeSolicitante());
			textFieldCpf.setText(solicitante.getCnpjSolicitante());
			textFieldEndereco.setText(solicitante.getEnderecoSolicitante());
			textFieldTelefone.setText(solicitante.getTelSolicitante());

		}

	}
	
	public void GravarSolicitante() throws UsuarioException {
		Solicitante solicitante = new Solicitante(  idPessoa, 
													textFieldNome.getText(), 
													textFieldCpf.getText(),
													textFieldEndereco.getText(),
													textFieldTelefone.getText());
		SolicitanteDAOItf dao = new SolicitanteDAOImpl();
		Component frame = null;
		
		if (solicitante.getIdSolicitante() > 0) {
			if (textFieldNome.getText().equals("") || 
				(textFieldCpf.getText().equals("")) ||
				(textFieldEndereco.getText().equals("")) ||
				(textFieldTelefone.getText()).equals("")) {
				JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.", "Inane error",
				JOptionPane.ERROR_MESSAGE);
			}
			else {
				dao.update(solicitante);
				JOptionPane.showMessageDialog(frame, "Solicitante SALVO com sucesso");
				idPessoa = -1;
				limparCampos();
			}
			
		}
		else {
			
			JOptionPane.showMessageDialog(frame, "Busque um Solicitante primeiro!.", "Inane error",
			JOptionPane.ERROR_MESSAGE);
		}
		

	}
}

