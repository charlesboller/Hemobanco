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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.UsuarioDAOImpl;
import dao.UsuarioDAOItf;
import exception.UsuarioException;
import model.Usuario;

public class TelaUsuarioAlterar extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldId;
	private JPasswordField passwordFieldSenha;
	private JPasswordField passwordFieldSenhaRep;
	private JTextField textFieldIdPes;
	private String idGlobal;
	private String nome;
	private String senha;
	private String senha2;
	private int id;
	private int nivel;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	Component frame;

	public TelaUsuarioAlterar() {
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 1000, 500);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 467, 207);
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 227, 467, 224);

		table = new JTable(modelo) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		modelo.addColumn("Matrícula");
		modelo.addColumn("Nome Completo");
		modelo.addColumn("Nível");
		carregarTabela();

		JLabel lblAlterarUsuario = new JLabel("Alterar Usuario:");
		lblAlterarUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");

		JLabel label = new JLabel("Nome Completo:");

		JLabel label_1 = new JLabel("Matricula:");

		JLabel label_2 = new JLabel("Senha:");

		JLabel label_3 = new JLabel("Repetir Senha:");

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);

		textFieldId = new JTextField();
		textFieldId.setColumns(10);

		JLabel label_4 = new JLabel("Nivel Usu\u00E1rio:");

		passwordFieldSenha = new JPasswordField();

		passwordFieldSenhaRep = new JPasswordField();

		final JComboBox<String> comboBoxNivel = new JComboBox<String>();
		comboBoxNivel.addItem("Usuário");
		comboBoxNivel.addItem("Admin");

		textFieldIdPes = new JTextField();
		textFieldIdPes.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idGlobal = textFieldIdPes.getText();
				UsuarioDAOItf dao = new UsuarioDAOImpl();
				try {
					if (dao.select(idGlobal).getId() == 0) {
						JOptionPane.showMessageDialog(frame, "Matrícula não existe no cadastro!", "Inane error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						nome = dao.select(idGlobal).getNome();
						senha = dao.select(idGlobal).getSenha();
						senha2 = dao.select(idGlobal).getSenha();
						id = dao.select(idGlobal).getId();
						nivel = dao.select(idGlobal).getNivel();
						textFieldId.setText(String.valueOf(id));
						textFieldNome.setText(nome);
						passwordFieldSenha.setText(senha);
						passwordFieldSenhaRep.setText(senha2);
						if (nivel == 2) {
							comboBoxNivel.setSelectedItem("Admin");
						} else {
							comboBoxNivel.setSelectedItem("Usuário");
						}
					}
				} catch (UsuarioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica campos vazios, se tiver mensagem de erro
				if (textFieldNome.getText().equals("") || textFieldId.getText().equals("")
						|| String.valueOf(passwordFieldSenha.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// verifica senhas são iguais
					if (String.valueOf(passwordFieldSenha.getPassword())
							.equals(String.valueOf(passwordFieldSenhaRep.getPassword()))) {
						Usuario usuario = new Usuario();

						int id = Integer.parseInt(textFieldId.getText());
						usuario.setId(id);
						usuario.setNome(textFieldNome.getText());
						usuario.setSenha(String.valueOf(passwordFieldSenha.getPassword()));
						if (comboBoxNivel.getSelectedItem().equals("Admin")) {
							usuario.setNivel(2);
						} else {
							usuario.setNivel(1);
						}
						UsuarioDAOItf dao = new UsuarioDAOImpl();
						try {
							dao.alterarid(idGlobal);
							dao.update(usuario);
							JOptionPane.showMessageDialog(frame, "Usuário ALTERADO com sucesso");
							textFieldId.setText(null);
							textFieldNome.setText(null);
							passwordFieldSenha.setText(null);
							passwordFieldSenhaRep.setText(null);
							textFieldIdPes.setText(null);
							carregarTabela();
						} catch (UsuarioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "SENHAS DIGITADAS não são iguais!", "Inane error",
								JOptionPane.ERROR_MESSAGE);

					}

				}

			}
		});

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UsuarioDAOItf dao = new UsuarioDAOImpl();
				try {
					dao.delete(id);
				} catch (UsuarioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, "Usuário DELETADO com sucesso");
				textFieldIdPes.setText(null);
				textFieldId.setText(null);
				textFieldNome.setText(null);
				passwordFieldSenha.setText(null);
				passwordFieldSenhaRep.setText(null);
				carregarTabela();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(
						gl_panel_2
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addGroup(gl_panel_2
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblAlterarUsuario)
												.addGap(18).addComponent(lblMatrcula).addGap(18)
												.addComponent(textFieldIdPes, GroupLayout.PREFERRED_SIZE, 104,
														GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
								.addComponent(btnBuscar))
						.addGroup(
								gl_panel_2.createSequentialGroup()
										.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 57,
														GroupLayout.PREFERRED_SIZE)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 325,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_2.createSequentialGroup()
												.addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, 104,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 79,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(comboBoxNivel, GroupLayout.PREFERRED_SIZE, 70,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
												.addComponent(passwordFieldSenhaRep, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
												.addComponent(btnDeletar).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnAlterar))
										.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(12, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(lblAlterarUsuario)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblMatrcula)
								.addComponent(textFieldIdPes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(
						textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
						.addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxNivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
				.addGap(18)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(label_2).addComponent(
						passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
						.addComponent(passwordFieldSenhaRep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAlterar).addComponent(btnDeletar))
				.addContainerGap(16, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		getContentPane().add(panel_2);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getContentPane() }));

	}

	public void carregarTabela() {
		UsuarioDAOItf dao = new UsuarioDAOImpl();
		modelo.getDataVector().removeAllElements();
		try {
			for (Usuario p : dao.listAll()) {
				modelo.addRow(new Object[] { p.getId(), p.getNome(), p.getNivel() });

			}
		} catch (UsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
