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
import dao.ExameDAOImpl;
import dao.ExameDAOItf;
import dao.SolicitacaoDAOImpl;
import dao.SolicitacaoDAOItf;
import dao.SolicitanteDAOImpl;
import dao.SolicitanteDAOItf;
import exception.UsuarioException;
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

public class SaidaMaterial extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private int idExameSelect = 0;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxSolicitante = new JComboBox();

	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	int idTipoSangue;
	Component frame = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SaidaMaterial(int id) {
		// idUsuario = id;
		setBounds(0, 0, 1000, 500);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		Object[] items = new Object[] { new ComboBoxSangue("A+", 1), new ComboBoxSangue("A-", 2),
				new ComboBoxSangue("B+", 3), new ComboBoxSangue("B-", 4), new ComboBoxSangue("AB+", 5),
				new ComboBoxSangue("AB-", 6), new ComboBoxSangue("O+", 7), new ComboBoxSangue("O-", 8) };
		final javax.swing.JComboBox comboBox = new javax.swing.JComboBox();

		DefaultComboBoxModel mod = new DefaultComboBoxModel(items);
		comboBox.setModel(mod);
		final JButton buttonGravar = new JButton("Gravar");
		buttonGravar.setEnabled(false);
		final JButton buttonBuscar = new JButton("Buscar");

		JLabel lblSadaDeMaterial = new JLabel("Sa\u00EDda de Material:");
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
					idExameSelect = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				}
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblSelecioneOTipo = new JLabel("Selecione o tipo de sangue solicitado:");

		// JComboBox comboBox = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSadaDeMaterial, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(buttonBuscar))
						.addComponent(lblSelecioneOTipo))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING, gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblSadaDeMaterial, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addGap(27).addComponent(lblSelecioneOTipo).addGap(6)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(buttonBuscar)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(81, Short.MAX_VALUE)));
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
		modelo.addColumn("Tipo Sangue");

		JLabel lblNome = new JLabel("Solicitante:");
		
		SolicitanteDAOItf dao = new SolicitanteDAOImpl();
		try {
			for (Solicitante p : dao.listAll()) {
				comboBoxSolicitante.addItem( p.getNomeSolicitante());
			}
		} 
		catch (UsuarioException e1) {
			e1.printStackTrace();
		}
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNome)
							.addGap(18)
							.addComponent(comboBoxSolicitante, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonGravar))
					.addContainerGap(374, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(comboBoxSolicitante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonGravar)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);

		buttonGravar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				int idExame = idExameSelect;
				if (idExame != 0) { // verifica se selecionou algum dos items no Jtable
					Date date = new Date();
					int idSolicitante = 0;
					SolicitanteDAOItf daoSolicitante = new SolicitanteDAOImpl();
					try {
						Solicitante solicitante = daoSolicitante.selectNome((String)comboBoxSolicitante.getSelectedItem());
						idSolicitante = solicitante.getIdSolicitante();
					} catch (UsuarioException e2) {
						e2.printStackTrace();
					}
					
					Solicitacao solicitacao = new Solicitacao(0, date, idExame, idSolicitante);
					SolicitacaoDAOItf dao = new SolicitacaoDAOImpl();
					try {
						dao.save(solicitacao);
						//System.out.println("gravado no banco");
					} catch (UsuarioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
						"DESEJA CONTINUAR COM MAIS MATERIAL AO MESMO CLIENTE?", "Warning", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						// ira dar continuidade a mais saidas ao mesmo
						// fornecedor, por isso o nome é travado.
						comboBoxSolicitante.disable();
					} else {
						// ira fechar a janela caso nao deseje continuar
						SaidaMaterial.this.setVisible(false);
					}
					carregarTabela(idTipoSangue);
				} else {
					// System.out.println("erro banco de dados 0");
					JOptionPane.showMessageDialog(frame, "SELECIONAR O SANGUE QUE SERÁ ENVIADO!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ComboBoxSangue ob = (ComboBoxSangue) comboBox.getSelectedItem();
				idTipoSangue = ob.getValue();
				carregarTabela(idTipoSangue);
				buttonBuscar.setEnabled(false);
				comboBox.setEnabled(false);
				buttonGravar.setEnabled(true);

			}
		});

	}

	public void carregarTabela(int tiposangue) {
		ExameDAOItf dao = new ExameDAOImpl();
		modelo.getDataVector().removeAllElements();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			for (Exame p : dao.listAll(tiposangue)) {
				int sangue = p.getIdTipoSangue();
				String SangueTxt = "";
				switch (sangue) {
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
					System.out.println("Este não é um sangue válido!");
				}
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				Date data = p.getDataDoador();
				String dataTxt = fmt.format(data);

				modelo.addRow(new Object[] { p.getIdExame(), dataTxt, SangueTxt });
				idExameSelect = 0;

			}
		} catch (UsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
