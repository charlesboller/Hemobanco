package telas;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.UsuarioDAOImpl;
import dao.UsuarioDAOItf;
import exception.UsuarioException;

public class Flogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jLogin;
	private JPasswordField jSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flogin frame = new Flogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Flogin() {

		setTitle("LOGIN - HEMOLIFE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		//Icone da Janela
		Image icon = Toolkit.getDefaultToolkit().getImage("image/hemolife_icon.png");  
		this.setIconImage(icon);  		
		jLogin = new JTextField();
		jLogin.setColumns(10);
		JLabel lblLogo = new JLabel();
		JLabel lblLogin = new JLabel("Login:");
		JLabel lblSenha = new JLabel("Senha:");
		JButton btnOk = new JButton("Ok");

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okLogar();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		jSenha = new JPasswordField();

		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon("image/hemolife_logo_100.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(99)
					.addComponent(lblLogin)
					.addGap(18)
					.addComponent(jLogin, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(93)
					.addComponent(lblSenha)
					.addGap(18)
					.addComponent(jSenha, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(btnCancelar))
				.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblLogin))
						.addComponent(jLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSenha))
						.addComponent(jSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOk)
						.addComponent(btnCancelar)))
		);
		contentPane.setLayout(gl_contentPane);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { jLogin, jSenha, btnOk, btnCancelar }));
	}

	public void okLogar() {
		String senha = String.valueOf(jSenha.getPassword());
		String id = jLogin.getText();
		UsuarioDAOItf dao = new UsuarioDAOImpl();
		try {
			Component frame = null;
//			String nome = dao.select(id).getNome();
			String senhaReal = dao.select(id).getSenha();
			int nivel = dao.select(id).getNivel();
			if (senha.equals(senhaReal)) {
//				JOptionPane.showMessageDialog(frame, "Bem vindo, " + nome);
				int idUsuario = Integer.parseInt(id);
				TelaPrincipal tela2 = new TelaPrincipal(idUsuario, nivel);
				tela2.setVisible(true);
				Flogin.this.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "Login ou senha está incorreta!.", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				jSenha.setText(null);
			}
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
	}
}
