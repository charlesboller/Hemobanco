package telas;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import business.PessoaConsulta;
import business.PessoaValidar;
import business.ComboBoxSangue;
import dao.CheckListDAOImpl;
import dao.CheckListDAOItf;
import dao.DoadorDAOImpl;
import dao.DoadorDAOItf;
import dao.PessoaDAOImpl;
import dao.PessoaDAOItf;
import exception.UsuarioException;
import model.Doador;
import model.Pessoa;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Coleta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldConsulta;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Component frame = null;
	private int idPessoa;
	PessoaDAOItf dao = new PessoaDAOImpl();
	Pessoa pessoa = new Pessoa();
	PessoaValidar regras = new PessoaValidar();
	private int idTipoSangue = 0;
	Doador doador = new Doador();
	private JTextField textField;
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Coleta(int id) {
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		textFieldConsulta = new JTextField();
		textFieldConsulta.setText("03412359971");
		textFieldConsulta.setColumns(10);

		JButton button = new JButton("Buscar");

		JLabel lblColetaDeMaterial = new JLabel("Coleta de Material:");
		lblColetaDeMaterial.setFont(new Font("Tahoma", Font.BOLD, 20));

		final JRadioButton rdbtnCpf = new JRadioButton("CPF");
		buttonGroup.add(rdbtnCpf);

		rdbtnCpf.setSelected(true);

		JRadioButton rdbtnNome = new JRadioButton("Nome");
		buttonGroup.add(rdbtnNome);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 960, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(textFieldConsulta, GroupLayout.DEFAULT_SIZE, 777,
														Short.MAX_VALUE)
												.addGap(18).addComponent(button).addGap(82))
						.addGroup(gl_panel.createSequentialGroup().addComponent(rdbtnCpf).addGap(18)
								.addComponent(rdbtnNome).addContainerGap(818, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_panel.createSequentialGroup().addComponent(lblColetaDeMaterial,
										GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblColetaDeMaterial, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnCpf)
								.addComponent(rdbtnNome))));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(12, 13, 936, 82);
		panel_1.add(panel_2);

		JLabel label = new JLabel("Doador:");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel label_1 = new JLabel("Nome:");

		textFieldNome = new JTextField();
		textFieldNome.setEditable(false);
		textFieldNome.setColumns(10);

		JLabel label_2 = new JLabel("CPF:");

		textFieldCpf = new JTextField();
		textFieldCpf.setEditable(false);
		textFieldCpf.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 936, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(label)
								.addGroup(gl_panel_2.createSequentialGroup().addComponent(label_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 615,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(label_2).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 143,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(71, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 82, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2).addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 108, 936, 186);
		panel_1.add(panel_3);

		Object[] items = new Object[] { new ComboBoxSangue("A+", 1), new ComboBoxSangue("A-", 2),
				new ComboBoxSangue("B+", 3), new ComboBoxSangue("B-", 4), new ComboBoxSangue("AB+", 5),
				new ComboBoxSangue("AB-", 6), new ComboBoxSangue("O+", 7), new ComboBoxSangue("O-", 8) };
		DefaultComboBoxModel mod = new DefaultComboBoxModel(items);
		
		JLabel lblTipoDeSangue = new JLabel("Tipo de Sangue:");

		final JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date date = new Date();
				if (idTipoSangue != 0){
					doador = new Doador(0, date, idTipoSangue, false, idPessoa);
				} else {
					doador = new Doador(0, date, 0, false, idPessoa);
				}
				
				DoadorDAOItf dao = new DoadorDAOImpl();
				
				try {
					dao.save(doador);
					JOptionPane.showMessageDialog(frame, "Coleta SALVA com sucesso");
					
					
					
				} catch (UsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnGravar.setEnabled(false);
		
		final JLabel lblSangue = new JLabel("0");
		lblSangue.setVisible(false);
		
		JLabel lblDataDaColeta = new JLabel("Data da coleta:");
		
		textField = new JTextField();
		textField.setText("21/11/2015");
		textField.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblTipoDeSangue)
							.addGap(18)
							.addComponent(lblSangue)
							.addGap(180)
							.addComponent(btnGravar))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblDataDaColeta, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(553, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeSangue)
						.addComponent(btnGravar)
						.addComponent(lblSangue))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDaColeta)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(119, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 960,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 960, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 345,
												GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

		
		
		
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limparTela();
				idPessoa = 0;
				PessoaConsulta regras = new PessoaConsulta();
				if (rdbtnCpf.isSelected() == true) {
					pessoa = regras.consultaPessoaCpf(textFieldConsulta.getText());
				} else {
					pessoa = regras.consultaPessoaNome(textFieldConsulta.getText());
				}
				boolean apto=false;
				idPessoa = pessoa.getId();
				CheckListDAOItf dao = new CheckListDAOImpl();
				try {
					apto = dao.select(idPessoa).isApto();
					
				} catch (UsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (apto == true) {

					if (pessoa.getNome() != null) {
						
						textFieldNome.setText(pessoa.getNome());
						textFieldCpf.setText(pessoa.getCpf());
						btnGravar.setEnabled(true);
					} 
				} else {
					JOptionPane.showMessageDialog(frame, "CADASTRO NÃO APTO PARA DOAÇÃO!", "Erro", JOptionPane.ERROR_MESSAGE);
					btnGravar.setEnabled(false);
				}
				
				DoadorDAOItf dao2 = new DoadorDAOImpl();
				try {
					System.out.println(dao2.selectPessoa(idPessoa).getIdTipoSangue());
				} catch (UsuarioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (dao2.selectPessoa(idPessoa).getIdTipoSangue() != 0){
						
						idTipoSangue = dao2.selectPessoa(idPessoa).getIdTipoSangue();
						String SangueTxt = "";
						switch (idTipoSangue) {
						case 1:
							SangueTxt = "A+";
							break;
						case 2:
							SangueTxt = "A-";
							break;
						case 3:
							SangueTxt = "B+";
							break;
						case 4:
							SangueTxt = "B-";
							break;
						case 5:
							SangueTxt = "AB+";
							break;
						case 6:
							SangueTxt = "AB-";
							break;
						case 7:
							SangueTxt = "O+";
							break;
						case 8:
							SangueTxt = "O-";
							break;
						default:
							System.out.println("Este não é um dia válido!");
						}
						lblSangue.setVisible(true);
						lblSangue.setText(SangueTxt);
						
						}else{
						lblSangue.setVisible(false);
						
					}
				} catch (UsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		});

	}

	public void limparTela() {
		textFieldNome.setText("");
		textFieldCpf.setText("");

	}
}
