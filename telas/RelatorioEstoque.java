package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.RelEstoqueDAOImpl;
import dao.RelEstoqueDAOItf;
import exception.UsuarioException;
import model.RelEstoque;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RelatorioEstoque extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RelatorioEstoque dialog = new RelatorioEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RelatorioEstoque() {
		setTitle("Relat\u00F3rio de Estoque");
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
			modelo.addColumn("Id Exame");
			modelo.addColumn("Data de Coleta");
			modelo.addColumn("Tipo de Sangue");
			carregarTabela();
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Sair");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						RelatorioEstoque.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void carregarTabela() {
		RelEstoqueDAOItf dao = new RelEstoqueDAOImpl();
		modelo.getDataVector().removeAllElements();
		try {
			for (RelEstoque p : dao.listAll()) {
				
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
					System.out.println("Este não é um dia válido!");
				}
				
				
				modelo.addRow(new Object[] { p.getIdExame(), p.getDataDoador(), SangueTxt  });

			}
		} catch (UsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
