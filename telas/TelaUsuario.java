package telas;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.UsuarioDAOImpl;
import dao.UsuarioDAOItf;
import exception.UsuarioException;
import model.Usuario;

public class TelaUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldId;
	private JPasswordField passwordFieldSenha;
	private JPasswordField passwordFieldSenhaRep;
	

	public TelaUsuario() {

		setClosable(true);
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 467, 206);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		JLabel lblNovoCadastro = new JLabel("Novo Usuario:");
		lblNovoCadastro.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("Nome Completo:");

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);

		JLabel lblCpf = new JLabel("Matricula:");

		textFieldId = new JTextField();
		textFieldId.setColumns(10);

		JLabel lblDataNascimento = new JLabel("Senha:");

		JLabel lblRepetirSenha = new JLabel("Repetir Senha:");

		JLabel lblNivelUsuario = new JLabel("Nivel Usu\u00E1rio:");

		// String texto = ("Admin", "Usuário");
		final JComboBox<String> comboBoxNivel = new JComboBox<String>();
		comboBoxNivel.addItem("Usuário");
		comboBoxNivel.addItem("Admin");

		passwordFieldSenha = new JPasswordField();

		passwordFieldSenhaRep = new JPasswordField();

		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				if (textFieldNome.getText().equals("") || textFieldId.getText().equals("")
						|| String.valueOf(passwordFieldSenha.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (String.valueOf(passwordFieldSenha.getPassword())
							.equals(String.valueOf(passwordFieldSenhaRep.getPassword()))) {
						Usuario usuario = new Usuario();
						int id = Integer.parseInt(textFieldId.getText());
						usuario.setId(id);
						usuario.setNome(textFieldNome.getText());
						usuario.setSenha(String.valueOf(passwordFieldSenha.getPassword()));
						if (comboBoxNivel.getSelectedItem().equals("Usuário")) {
							usuario.setNivel(1);
						} else {
							usuario.setNivel(2);
						}
						UsuarioDAOItf dao = new UsuarioDAOImpl();
						try {
							dao.save(usuario);
							JOptionPane.showMessageDialog(frame, "Usuário SALVO com sucesso");
							textFieldId.setText(null);
							textFieldNome.setText(null);
							passwordFieldSenha.setText(null);
							passwordFieldSenhaRep.setText(null);
						} catch (UsuarioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Senhas digitadas não são iguais!", "Inane error",
								JOptionPane.ERROR_MESSAGE);

					}

				}

			}
		});
		getContentPane().setLayout(null);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNovoCadastro)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDataNascimento)
								.addComponent(lblCpf)
								.addComponent(lblNewLabel)
								.addComponent(lblRepetirSenha))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(passwordFieldSenhaRep, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNivelUsuario)
									.addGap(18)
									.addComponent(comboBoxNivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNovoCadastro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblCpf)
									.addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBoxNivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNivelUsuario))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(125)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataNascimento)
								.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRepetirSenha)
								.addComponent(passwordFieldSenhaRep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), panel, lblNovoCadastro, lblNewLabel, lblCpf, textFieldId, lblDataNascimento, textFieldNome}));

	}
}
