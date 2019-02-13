package telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

import business.PessoaConsulta;
import business.PessoaValidar;
import dao.CheckListDAOImpl;
import dao.CheckListDAOItf;
import dao.PessoaDAOImpl;
import dao.PessoaDAOItf;
import exception.UsuarioException;
import model.Consulta;
import model.Pessoa;


public class TelaConsulta extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldConsulta;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JFormattedTextField textFieldNascimento;
	private JTextField textFieldAltura;
	private JTextField textFieldPeso;
	JCheckBox chckbxGravidez = new JCheckBox("Gravidez");
	JCheckBox chckbxGripe = new JCheckBox("Gripe (\u00FAltimos 7 dias)");
	JCheckBox chckbxTatuagem = new JCheckBox("Tatuagem (12 meses)");
	JCheckBox chckbxCirurgia = new JCheckBox("Cirurgia (12 meses)");
	JCheckBox chckbxDit = new JCheckBox("Doen\u00E7as infecciosas transmiss\u00EDveis");
	JCheckBox chckbxDrogas = new JCheckBox("Drogas il\u00EDcitas injet\u00E1veis");
	JButton button = new JButton("Buscar");
	JButton btnGravar = new JButton("Gravar");
	
	private int idUsuario;
	private int idPessoa;
	private boolean aptoDoador;
	
	PessoaDAOItf dao = new PessoaDAOImpl();
	Pessoa pessoa = new Pessoa();
	PessoaValidar regras = new PessoaValidar();
	Consulta consulta = new Consulta(); 
	private int existeCheck = 0;
	Component frame = null;
	
	/**
	 * Create the frame.
	 * @param idUsuario 
	 */
	public TelaConsulta(int id) {
		idUsuario = id;
		setBounds(0, 0, 1000, 540);
		getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 13, 960, 118);
		
		JLabel lblCheckList = new JLabel("Check List:");
		lblCheckList.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JRadioButton rdbtnNome = new JRadioButton("Nome");
		buttonGroup.add(rdbtnNome);
		
		final JRadioButton rdbtnCpf = new JRadioButton("CPF");
		buttonGroup.add(rdbtnCpf);
		
		rdbtnCpf.setSelected(true);
		
		textFieldConsulta = new JTextField();
		textFieldConsulta.setText("03412359971");
		textFieldConsulta.setColumns(10);
		
		
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
				if (pessoa.getNome() != null) {
					idPessoa = pessoa.getId();
					textFieldNome.setText(pessoa.getNome());
					textFieldCpf.setText(pessoa.getCpf());
					btnGravar.setEnabled(true);
				} else {
					btnGravar.setEnabled(false);
					
				}

				CheckListDAOItf dao = new CheckListDAOImpl();
				
				try {
					if (dao.select(idPessoa).getIdPessoa() != 0) {
						consulta = dao.select(idPessoa);
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date nascimento = consulta.getNascimento();        
						String dataTxt = df.format(nascimento);
						textFieldNascimento.setText(dataTxt);
						String alturaTxt = String.valueOf(consulta.getAltura() );
						textFieldAltura.setText(alturaTxt);
						String pesoTxt = String.valueOf(consulta.getPeso() );
						textFieldPeso.setText(pesoTxt);
						chckbxGravidez.setSelected(consulta.isGravidez());
						chckbxGripe.setSelected(consulta.isGripe());
						chckbxTatuagem.setSelected(consulta.isTatuagem());
						chckbxCirurgia.setSelected(consulta.isCirurgia());
						chckbxDit.setSelected(consulta.isDit());
						chckbxDrogas.setSelected(consulta.isDrogas());
						
						existeCheck = 1;
						
					}else{
						existeCheck = 0;
						
					}
				} catch (UsuarioException e) {
					e.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textFieldConsulta, GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(button))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(rdbtnCpf)
							.addGap(18)
							.addComponent(rdbtnNome))
						.addComponent(lblCheckList, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCheckList, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnCpf)
						.addComponent(rdbtnNome)))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 144, 960, 345);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(12, 13, 936, 82);
		panel_1.add(panel_2);
		
		JLabel lblDoador = new JLabel("Doador:");
		lblDoador.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		textFieldNome = new JTextField();
		textFieldNome.setEditable(false);
		textFieldNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblCpf = new JLabel("CPF:");
		
		textFieldCpf = new JTextField();
		textFieldCpf.setEditable(false);
		textFieldCpf.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDoador)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCpf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDoador)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 108, 936, 186);
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 3, 0 , 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.add(panel_4);
		
		JLabel lblRequisitosBsicos = new JLabel("Requisitos b\u00E1sicos:");
		
		JLabel lblNascimento = new JLabel("Nascimento:");
		
		
		
		MaskFormatter mascaradata = null;
		try {
			mascaradata  = new MaskFormatter("##/##/####");
			mascaradata .setPlaceholderCharacter('_');
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		textFieldNascimento = new JFormattedTextField(mascaradata);
		textFieldNascimento.setColumns(10);
		
		
		
		JLabel lblAltura = new JLabel("Altura:");
		
		textFieldAltura = new JTextField();
		textFieldAltura.setColumns(10);
		
		JLabel lblPeso = new JLabel("Peso:");
		
		textFieldPeso = new JTextField();
		textFieldPeso.setColumns(10);
		
		JLabel lblMetros = new JLabel("Metro");
		
		JLabel lblKg = new JLabel("Kg");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(100)
							.addComponent(lblRequisitosBsicos))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAltura)
								.addComponent(lblNascimento)
								.addComponent(lblPeso))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldPeso, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(textFieldAltura, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addComponent(lblKg)
										.addComponent(lblMetros))))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(5)
					.addComponent(lblRequisitosBsicos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNascimento)
						.addComponent(textFieldNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAltura)
						.addComponent(textFieldAltura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMetros))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeso)
						.addComponent(textFieldPeso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKg))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_3.add(panel_5);
		
		JLabel lblNewLabel = new JLabel("Impedimentos tempor\u00E1rios:");
		
		
		
		
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(76)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxGravidez))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxGripe))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxTatuagem))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxCirurgia)))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxGravidez)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxGripe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxTatuagem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxCirurgia)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.add(panel_6);
		
		JLabel lblImpedimentosDefinitivos = new JLabel("Impedimentos definitivos:");
		
		
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(81)
							.addComponent(lblImpedimentosDefinitivos))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxDit))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxDrogas)))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(5)
					.addComponent(lblImpedimentosDefinitivos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxDit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxDrogas)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		btnGravar.setEnabled(false);
		
		
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CheckListDAOItf dao = new CheckListDAOImpl();
				float altura = Float.parseFloat(textFieldAltura.getText());
				float peso = Float.parseFloat(textFieldPeso.getText());
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		        Date parsed = null;
				try {
					parsed = (Date) format.parse(textFieldNascimento.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        java.sql.Date dataSql = new java.sql.Date(parsed.getTime());
				
				Consulta consulta = new Consulta(0,idPessoa, dataSql, altura, peso, 
						chckbxGravidez.isSelected(), chckbxGripe.isSelected(), chckbxTatuagem.isSelected(),
						chckbxCirurgia.isSelected(), chckbxDit.isSelected(), chckbxDrogas.isSelected(),
						aptoDoador, idUsuario);
				
				
				
				if (existeCheck == 1){
					try {
						dao.update(consulta);
						dao.verificar(idPessoa);
					} catch (UsuarioException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					
					try {
						dao.save(consulta);
						dao.verificar(idPessoa);
					} catch (UsuarioException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
				
				try {
					if (dao.select(idPessoa).isApto() == true){
						JOptionPane.showMessageDialog(frame, "Cliente APTO para doação");
					} else{
						JOptionPane.showMessageDialog(frame, "Cliente NÃO APTO para doação!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				} catch (UsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGravar.setBounds(851, 307, 97, 25);
		panel_1.add(btnGravar);

	}
	
	public void abrirCampos(boolean abrir) {
			
		btnGravar.setEnabled(abrir);
		textFieldNascimento.setEnabled(abrir);
		textFieldAltura.setEnabled(abrir);
		textFieldPeso.setEnabled(abrir);
		chckbxGravidez.setEnabled(abrir);
		chckbxGripe.setEnabled(abrir);
		chckbxTatuagem.setEnabled(abrir);
		chckbxCirurgia.setEnabled(abrir);
		chckbxDit.setEnabled(abrir);
		chckbxDrogas.setEnabled(abrir);
		textFieldNome.setEnabled(abrir);
		textFieldCpf.setEnabled(abrir);
	}
	
	public void limparTela() {
		textFieldNascimento.setText("");
		textFieldAltura.setText("");
		textFieldPeso.setText("");
		chckbxGravidez.setSelected(false);
		chckbxGripe.setSelected(false);
		chckbxTatuagem.setSelected(false);
		chckbxCirurgia.setSelected(false);
		chckbxDit.setSelected(false);
		chckbxDrogas.setSelected(false);
		textFieldNome.setText("");
		textFieldCpf.setText("");
	}
}
