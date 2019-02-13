package telas;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import model.Pessoa;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import business.PessoaValidar;




public class CadastroNovo extends JInternalFrame {
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
	private int idUsuario;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox();
	Component frame = null;
	
	PessoaValidar regras = new PessoaValidar();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CadastroNovo(int id) {

		idUsuario = id;
		setClosable(true);
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 960, 289);

		JLabel lblNovoCadastro = new JLabel("Novo Cadastro:");
		lblNovoCadastro.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("Nome Completo:");

		textFieldNome = new JTextField();
		textFieldNome.setToolTipText("Nome");
		textFieldNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");

		textFieldCpf = new JTextField();
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

		
		
		
		
		
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Pessoa pessoa = new Pessoa(0,textFieldNome.getText(),textFieldCpf.getText(), textFieldEmail.getText(), 
						textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(),
						textFieldCelular.getText(), textFieldTelefone.getText(), textFieldCep.getText(),
						(String) comboBox.getSelectedItem(), idUsuario);
				
				if (regras.camposPreenchidos(pessoa) == true ){
					if ( regras.cpfExiste(pessoa) == false){
						
						regras.gravarCadastro();
						limparCampos();
					}
					
				}
			}
		});
		
		
		
		
		
		
		
		
		String[] items = new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
				"PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
		getContentPane().setLayout(null);
		comboBox = new JComboBox(items);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBairro)
								.addComponent(lblNewLabel)
								.addComponent(lblCpf)
								.addComponent(lblLogradouro)
								.addComponent(lblTelefone))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(322)
									.addComponent(lblEmail)
									.addGap(18)
									.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
								.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
								.addComponent(textFieldEndereco, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldTelefone)
										.addComponent(textFieldBairro, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCidade)
										.addComponent(lblCelular))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textFieldCidade, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
											.addGap(38)
											.addComponent(lblEstado)
											.addGap(18)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(53)
											.addComponent(lblCep))
										.addComponent(textFieldCelular, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(textFieldCep, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnGravar, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))))
							.addGap(102))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNovoCadastro)
							.addContainerGap(795, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNovoCadastro)
					.addGap(41)
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
						.addComponent(lblLogradouro)
						.addComponent(textFieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro)
						.addComponent(textFieldBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(lblEstado)
						.addComponent(textFieldCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCidade)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCelular)
								.addComponent(textFieldCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(28, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGravar)
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textFieldNome, textFieldCpf, textFieldEmail, textFieldEndereco, textFieldBairro,
						textFieldCidade, textFieldCep, textFieldTelefone, textFieldCelular, btnGravar }));
		getContentPane().add(panel);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textFieldNome, textFieldCpf, textFieldEmail, textFieldEndereco, textFieldBairro,
						textFieldCidade, textFieldCep, textFieldTelefone, textFieldCelular, btnGravar }));
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textFieldNome, textFieldCpf, textFieldEmail, textFieldEndereco, textFieldBairro,
						textFieldCidade, textFieldCep, textFieldTelefone, textFieldCelular, btnGravar }));

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
