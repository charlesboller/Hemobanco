package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import business.ComboBoxSangue;
import dao.RelEstoqueDAOImpl;
import dao.RelEstoqueDAOItf;
import exception.UsuarioException;
import model.RelSolicitar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;

public class RelatorioSolicitar extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;

	public static void main(String[] args) {
		try {
			RelatorioSolicitar dialog = new RelatorioSolicitar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RelatorioSolicitar() {
		setTitle("Solicitar Doador");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			scrollPane = new JScrollPane();
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
			modelo.addColumn("Id Doador");
			modelo.addColumn("Nome Completo");
			modelo.addColumn("E-mail");
			modelo.addColumn("Telefone");
			modelo.addColumn("Tipo de Sangue");
			
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		Object[] items = new Object[] { new ComboBoxSangue("A+", 1), new ComboBoxSangue("A-", 2),
				new ComboBoxSangue("B+", 3), new ComboBoxSangue("B-", 4), new ComboBoxSangue("AB+", 5),
				new ComboBoxSangue("AB-", 6), new ComboBoxSangue("O+", 7), new ComboBoxSangue("O-", 8) };
		final javax.swing.JComboBox comboBox = new javax.swing.JComboBox();

		DefaultComboBoxModel mod = new DefaultComboBoxModel(items);
		comboBox.setModel(mod);
		
		JLabel lblSelecioneOTipo = new JLabel("Selecione o tipo de sangue:");
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxSangue ob = (ComboBoxSangue) comboBox.getSelectedItem();
				int tiposangue = ob.getValue();
				carregarTabela(tiposangue);
	
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSelecioneOTipo, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(518, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelecioneOTipo)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Sair");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						RelatorioSolicitar.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void carregarTabela(int tiposangue) {
		RelEstoqueDAOItf dao = new RelEstoqueDAOImpl();
		modelo.getDataVector().removeAllElements();
		try {
			for (RelSolicitar p : dao.listAllSolicitar(tiposangue)) {
				
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
				
				
				modelo.addRow(new Object[] { p.getIdPessoa(), p.getNome(), p.getEmail(), p.getTelefone(), SangueTxt  });

			}
		} catch (UsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
