package telas;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import business.PessoaConsulta;
import business.PessoaValidar;
import dao.PessoaDAOImpl;
import dao.PessoaDAOItf;
import model.Pessoa;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.beans.PropertyVetoException;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroConsulta extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JTextField textFieldCidade;
	private JTextField textFieldCep;
	private JTextField textFieldBairro;
	private JTextField textFieldTelefone;
	private JTextField textFieldCelular;
	private JTextField textFieldEmail;
	private JTextField textFieldConsulta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int idUsuario;
	private int idPessoa;
	PessoaDAOItf dao = new PessoaDAOImpl();
	Pessoa pessoa = new Pessoa();
	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox<>();
	PessoaValidar regras = new PessoaValidar();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CadastroConsulta(int id) {
		idUsuario = id;
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

		JRadioButton rdbtnNome = new JRadioButton("Nome");
		buttonGroup.add(rdbtnNome);

		final JRadioButton rdbtnCpf = new JRadioButton("CPF");
		rdbtnCpf.setSelected(true);
		buttonGroup.add(rdbtnCpf);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PessoaConsulta regras = new PessoaConsulta();
				if (rdbtnCpf.isSelected() == true) {
					pessoa = regras.consultaPessoaCpf(textFieldConsulta.getText());
				} else {
					pessoa = regras.consultaPessoaNome(textFieldConsulta.getText());
				}
				if (pessoa != null) {

					idPessoa = pessoa.getId();
					textFieldNome.setText(pessoa.getNome());
					textFieldCpf.setText(pessoa.getCpf());
					textFieldEmail.setText(pessoa.getEmail());
					textFieldEndereco.setText(pessoa.getEndereco());
					textFieldBairro.setText(pessoa.getBairro());
					textFieldCidade.setText(pessoa.getCidade());
					textFieldCep.setText(pessoa.getCep());
					textFieldTelefone.setText(pessoa.getTelefone());
					textFieldCelular.setText(pessoa.getCelular());
					comboBox.setSelectedItem(pessoa.getEstado());
				}

			}
		});

		JLabel label = new JLabel("Consulta Cadastro:");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));

		textFieldConsulta = new JTextField();
		textFieldConsulta.setColumns(10);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(textFieldConsulta, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton)
							.addGap(82))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(rdbtnCpf)
									.addGap(18)
									.addComponent(rdbtnNome)))
							.addContainerGap(759, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnCpf)
						.addComponent(rdbtnNome)))
		);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("Nome Completo:");

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");

		textFieldCpf = new JTextField();
		textFieldCpf.setEditable(false);
		textFieldCpf.setColumns(10);

		JLabel lblLogradouro = new JLabel("Logradouro:");

		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");

		textFieldCidade = new JTextField();
		textFieldCidade.setColumns(10);

		textFieldCep = new JTextField();
		textFieldCep.setColumns(10);

		JLabel lblCep = new JLabel("CEP:");

		String[] items = new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
				"PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };

		comboBox = new JComboBox(items);
		comboBox.setToolTipText("");

		JLabel lblEstado = new JLabel("Estado:");

		JLabel lblBairro = new JLabel("Bairro:");

		textFieldBairro = new JTextField();
		textFieldBairro.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");

		JLabel lblCelular = new JLabel("Celular:");

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);

		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Pessoa pessoa = new Pessoa(idPessoa, textFieldNome.getText(), textFieldCpf.getText(),
						textFieldEmail.getText(), textFieldEndereco.getText(), textFieldBairro.getText(),
						textFieldCidade.getText(), textFieldCelular.getText(), textFieldTelefone.getText(),
						textFieldCep.getText(), (String) comboBox.getSelectedItem(), idUsuario);

				if (regras.camposPreenchidos(pessoa) == true) {
					regras.alterarPessoa(pessoa);
					limparCampos();

				}

			}
		});
		getContentPane().setLayout(null);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(67)
									.addComponent(lblBairro)
									.addGap(18)
									.addComponent(textFieldBairro, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(53)
									.addComponent(lblTelefone)
									.addGap(18)
									.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
									.addComponent(lblCelular)
									.addGap(18)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldCelular, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnAlterar)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblCidade)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldCidade, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
										.addGap(31)
										.addComponent(lblEstado)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addGap(29)
										.addComponent(lblCep)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblCpf)
								.addComponent(lblLogradouro))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldEndereco, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(322)
									.addComponent(lblEmail)
									.addGap(18)
									.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
								.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))))
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
						.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogradouro))
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(textFieldBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCidade)
						.addComponent(textFieldCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstado))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefone)
						.addComponent(textFieldCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCelular))
					.addGap(51)
					.addComponent(btnAlterar)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		getContentPane().add(panel_1);

	}

	public void limparCampos() {
		textFieldNome.setText(null);
		textFieldCpf.setText(null);
		textFieldEmail.setText(null);
		textFieldEndereco.setText(null);
		textFieldBairro.setText(null);
		textFieldCidade.setText(null);
		textFieldCelular.setText(null);
		textFieldTelefone.setText(null);
		textFieldCep.setText(null);
	}
}
