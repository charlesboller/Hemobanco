package telas;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import dao.SolicitanteDAOImpl;
import dao.SolicitanteDAOItf;
import exception.UsuarioException;
import model.Solicitante;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import business.PessoaValidar;

public class SolicitanteNovo extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCnpj;
	private JTextField textFieldEndereco;
	private JTextField textFieldTelefone;
	Component frame = null;

	PessoaValidar regras = new PessoaValidar();

	public SolicitanteNovo(int id) {

		setClosable(true);
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 960, 289);

		JLabel lblNovoCadastro = new JLabel("Novo Solicitante:");
		lblNovoCadastro.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("Nome:");

		textFieldNome = new JTextField();
		textFieldNome.setToolTipText("Nome");
		textFieldNome.setColumns(10);

		JLabel lblCpf = new JLabel("CNPJ:");

		textFieldCnpj = new JTextField();
		textFieldCnpj.setColumns(10);

		JLabel lblLogradouro = new JLabel("Logradouro:");

		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					gravarSolicitante();
				} catch (UsuarioException e) {
					e.printStackTrace();
				}
			}
		});

		getContentPane().setLayout(null);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
										.addComponent(lblCpf).addComponent(lblLogradouro))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldCnpj, GroupLayout.PREFERRED_SIZE, 104,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
										.addComponent(textFieldEndereco, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
										.addComponent(btnGravar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 83,
												GroupLayout.PREFERRED_SIZE))
								.addGap(102))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNovoCadastro).addContainerGap(777,
								Short.MAX_VALUE))))
				.addGroup(gl_panel.createSequentialGroup().addGap(26).addComponent(lblTelefone).addGap(18)
						.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(745, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblNovoCadastro).addGap(41)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(textFieldNome,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblCpf).addComponent(textFieldCnpj,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblLogradouro).addComponent(
						textFieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblTelefone).addComponent(
						textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(30).addComponent(btnGravar).addContainerGap()));
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textFieldNome, textFieldCnpj, textFieldEndereco, textFieldTelefone, btnGravar }));
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textFieldNome, textFieldCnpj, textFieldEndereco, textFieldTelefone, btnGravar }));
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textFieldNome, textFieldCnpj, textFieldEndereco, textFieldTelefone, btnGravar }));

	}

	public void limparCampos() {
		textFieldNome.setText(null);
		textFieldCnpj.setText(null);
		textFieldEndereco.setText(null);
		textFieldTelefone.setText(null);
	}

	public void gravarSolicitante() throws UsuarioException {
		
		Solicitante solicitante = new Solicitante(  0,
													textFieldNome.getText(), 
													textFieldCnpj.getText(),
													textFieldEndereco.getText(), 
													textFieldTelefone.getText());
		
		SolicitanteDAOItf dao = new SolicitanteDAOImpl();	
		
		Component frame = null;

		if (textFieldNome.getText().equals("") || (textFieldCnpj.getText().equals("")) ||
			(textFieldEndereco.getText().equals("")) || (textFieldTelefone.getText()).equals("")) {
			
			JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.", "Inane error",
			JOptionPane.ERROR_MESSAGE);
		}
		else {
			dao.save(solicitante);
			JOptionPane.showMessageDialog(frame, "Solicitante SALVO com sucesso");
			limparCampos();
		}
		
	}

}
