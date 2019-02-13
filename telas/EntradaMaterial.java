package telas;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import business.ComboBoxSangue;
import dao.DoadorDAOImpl;
import dao.DoadorDAOItf;
import dao.ExameDAOImpl;
import dao.ExameDAOItf;
import dao.SolicitacaoDAOImpl;
import dao.SolicitacaoDAOItf;
import dao.SolicitanteDAOImpl;
import dao.SolicitanteDAOItf;
import dao.UsuarioDAOImpl;
import dao.UsuarioDAOItf;
import exception.UsuarioException;
import model.Doador;
import model.Exame;
import model.Solicitacao;
import model.Solicitante;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class EntradaMaterial extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private int idDoadorSelect = 0;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	int idTipoSangue;
	Component frame = null;
	private JTextField textField_Glicose;
	private JTextField textField_Colesterol;
	private JTextField textField_Hemacias;
	private JTextField textField_Leucocitos;
	private JTextField textField_Plaquetas;
	private JTextField textField_IdDoador;
	private JCheckBox chckbxPossuiDit;
	@SuppressWarnings("rawtypes")
	private final javax.swing.JComboBox comboBox = new javax.swing.JComboBox();

	public EntradaMaterial(int id) {
		// idUsuario = id;
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		Object[] items = new Object[] { new ComboBoxSangue("A+", 1), new ComboBoxSangue("A-", 2),
				new ComboBoxSangue("B+", 3), new ComboBoxSangue("B-", 4), new ComboBoxSangue("AB+", 5),
				new ComboBoxSangue("AB-", 6), new ComboBoxSangue("O+", 7), new ComboBoxSangue("O-", 8) };

		DefaultComboBoxModel mod = new DefaultComboBoxModel(items);
		final JButton buttonGravar = new JButton("Gravar");
		//buttonGravar.setEnabled(false);

		JLabel lblSadaDeMaterial = new JLabel("Entrada de Material:");
		lblSadaDeMaterial.setFont(new Font("Tahoma", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable(modelo) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() > -1) {
					idDoadorSelect = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					textField_IdDoador.setText(Integer.toString(idDoadorSelect)); 
				}
			}
		});
		scrollPane.setViewportView(table);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
						.addComponent(lblSadaDeMaterial, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSadaDeMaterial, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 960,
												Short.MAX_VALUE)
										.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 960,
												Short.MAX_VALUE))
								.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_1,
												GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		modelo.addColumn("Doador");
		modelo.addColumn("Data");

		JLabel lblNome = new JLabel("N\u00FAmero Coleta:");
		
	//	comboBox = new javax.swing.JComboBox();
		comboBox.setModel(mod);
		
		JLabel lblGlicose = new JLabel("Glicose:");
		
		JLabel lblColesterol = new JLabel("Colesterol:");
		
		JLabel lblHemacias = new JLabel("Hemacias:");
		
		textField_Glicose = new JTextField();
		textField_Glicose.setColumns(10);
		
		textField_Colesterol = new JTextField();
		textField_Colesterol.setColumns(10);
		
		textField_Hemacias = new JTextField();
		textField_Hemacias.setColumns(10);
		
		textField_Leucocitos = new JTextField();
		textField_Leucocitos.setColumns(10);
		
		textField_Plaquetas = new JTextField();
		textField_Plaquetas.setColumns(10);
		
		JLabel lblPlaquetas = new JLabel("Plaquetas:");
		
		JLabel lblLeuccitos = new JLabel("Leuc\u00F3citos:");
		
		chckbxPossuiDit = new JCheckBox("Possui DIT");
		
		JLabel label = new JLabel("Tipo Sangu\u00EDneo:");
		
		textField_IdDoador = new JTextField();
		textField_IdDoador.setColumns(10);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblGlicose, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblColesterol, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHemacias, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Hemacias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Colesterol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Glicose, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_IdDoador, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addGap(161)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblLeuccitos, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_Leucocitos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(chckbxPossuiDit)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonGravar))
							.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(lblPlaquetas, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(textField_Plaquetas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(56)
					.addContainerGap(267, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNome)
							.addComponent(textField_IdDoador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGlicose)
								.addComponent(textField_Glicose, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblColesterol)
								.addComponent(textField_Colesterol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHemacias)
								.addComponent(textField_Hemacias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLeuccitos)
								.addComponent(textField_Leucocitos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPlaquetas)
								.addComponent(textField_Plaquetas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxPossuiDit)
								.addComponent(buttonGravar))))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		
		carregarTabela();

		buttonGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravar();
			}
		});
	}
	
	public void gravar() {
		boolean apto, disponivel; 
		ComboBoxSangue ob = (ComboBoxSangue) comboBox.getSelectedItem();
		idTipoSangue = ob.getValue();
		
		textField_IdDoador.setText(validarCampo(textField_IdDoador.getText()));
		textField_Glicose.setText(validarCampo(textField_Glicose.getText()));
		textField_Colesterol.setText(validarCampo(textField_Colesterol.getText()));  
		textField_Hemacias.setText(validarCampo(textField_Hemacias.getText()));  
		textField_Leucocitos.setText(validarCampo(textField_Leucocitos.getText()));  
		textField_Plaquetas.setText(validarCampo(textField_Plaquetas.getText()));  

		if (chckbxPossuiDit.isSelected()){
			apto = false;
			disponivel = false;
		}
		else {
			apto = true;
			disponivel = true;
		}
		
		Exame exame = new Exame(0, Integer.parseInt(textField_Glicose.getText()), Integer.parseInt(textField_Colesterol.getText()), 
				                Integer.parseInt(textField_Hemacias.getText()), Integer.parseInt(textField_Leucocitos.getText()), 
				                Integer.parseInt(textField_Plaquetas.getText()), chckbxPossuiDit.isSelected(), 
				                apto, disponivel, idTipoSangue, Integer.parseInt(textField_IdDoador.getText()));
		
		ExameDAOItf dao2 = new ExameDAOImpl();
		try {
			dao2.save(exame);
		} catch (UsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DoadorDAOItf dao = new DoadorDAOImpl();
		try {
			dao.disponivel(Integer.parseInt(textField_IdDoador.getText()));
		} catch (NumberFormatException | UsuarioException e) {
			e.printStackTrace();
		}
		limparCampos();
		carregarTabela();
		

	}

	public void carregarTabela() {
		DoadorDAOItf dao = new DoadorDAOImpl();
		modelo.getDataVector().removeAllElements();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			for (Doador p : dao.listExame()) {

				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				Date data = p.getDataDoador();
				String dataTxt = fmt.format(data);

				modelo.addRow(new Object[] { p.getIdDoador(), dataTxt });
				idDoadorSelect = 0;

			}
		} catch (UsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String validarCampo(String texto) {
		if (texto.isEmpty())
		  return "0";
		else
			try {
				Integer.parseInt(texto);
				return texto;
			} catch (NumberFormatException e) {
				return "0";
			}	
	}
	
	public void limparCampos() {
		textField_IdDoador.setText(null);
		textField_Glicose.setText(null);
		textField_Colesterol.setText(null);  
		textField_Hemacias.setText(null);  
		textField_Leucocitos.setText(null);  
		textField_Plaquetas.setText(null);
		chckbxPossuiDit.setSelected(false);
	}
}
