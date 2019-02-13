package telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Panel fundo;
	private Panel barra;
	private Panel barra2;
	private Panel barra3;
	private CadastroNovo telacadastro = null;
	private CadastroConsulta telacadastroconsulta = null;
	private TelaUsuario telausuario = null;
	private TelaUsuarioAlterar telausuarioalterar = null;
	private TelaConsulta consulta = null;
	private Coleta coleta = null;
	private EntradaMaterial entrada = null;
	private SaidaMaterial saida = null;
	private RelatorioSolicitar relSolicitar = null;
	private RelatorioEstoque relEstoque = null;
	private SolicitanteNovo solicitanteNovo = null;
	private SolicitanteConsulta solicitanteConsulta = null;
	private int idUsuario;
	private JMenuItem mntmUsuarios = new JMenuItem("Usu\u00E1rios");
	private JMenuItem mntmAlterardeletar = new JMenuItem("Alterar/Deletar");
	private JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
	private JMenuItem mntmSolicitanteNovo = new JMenuItem("Solicitante Novo");
	private JMenuItem mntmSolicitanteConsulta = new JMenuItem("Solicitante Alterar");

	public TelaPrincipal(int id, int nivel) {
		idUsuario = id;
		
		setTitle("HEMOLIFE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 920);
		
		//Icone da Janela
		Image icon = Toolkit.getDefaultToolkit().getImage("image/hemolife_icon.png");  
		this.setIconImage(icon);  	

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmNovo = new JMenuItem("Paciente Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoPaciente();
			}
		});
		mnCadastro.add(mntmNovo);

		JMenuItem mntmConsulta = new JMenuItem("Paciente Alterar");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaPaciente();
			}
		});
		mnCadastro.add(mntmConsulta);
		
		//JMenuItem mntmSolicitanteNovo = new JMenuItem("Solicitante Novo");
		mntmSolicitanteNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solicitanteNovo();
			}
		});
		mnCadastro.add(mntmSolicitanteNovo);
		
		//JMenuItem mntmSolicitanteConsulta = new JMenuItem("Solicitante Alterar");
		mntmSolicitanteConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solicitanteConsulta();
			}
		});
		mnCadastro.add(mntmSolicitanteConsulta);

		JMenu mnConsultorio = new JMenu("Consult\u00F3rio");
		menuBar.add(mnConsultorio);
		
		JMenuItem mntmConsultorio = new JMenuItem("Efetuar Consulta");
		mntmConsultorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consulta();
			}
		});
		mnConsultorio.add(mntmConsultorio);

		JMenu mnLaboratrio = new JMenu("Laborat\u00F3rio");
		menuBar.add(mnLaboratrio);

		JMenuItem mntmColetaDeMaterial = new JMenuItem("Coleta de Material");
		mntmColetaDeMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coletaMaterial();
			}
		});
		mnLaboratrio.add(mntmColetaDeMaterial);

		JMenuItem mntmEntradaDeMaterial = new JMenuItem("Entrada de Material");
		mntmEntradaDeMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entradaMaterial();
			}
		});
		mnLaboratrio.add(mntmEntradaDeMaterial);
		
		JMenuItem mntmSaidaDeMaterial = new JMenuItem("Sa\u00EDda de Material");
		mntmSaidaDeMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saidaMaterial();
			}
		});
		mnLaboratrio.add(mntmSaidaDeMaterial);
		
		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
				
		JMenuItem mntmSaldo = new JMenuItem("Saldo");
		mntmSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioEstoque();
			}
		});
		mnRelatrios.add(mntmSaldo);
						
		JMenuItem mntmEntradadata = new JMenuItem("Solicitar Doador");
		mntmEntradadata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioSolicitar();
			}
		});			
		mnRelatrios.add(mntmEntradadata);
		
		//JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(mnConfiguraes);

		//JMenuItem mntmUsuarios = new JMenuItem("Usu\u00E1rios");
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaUsuario();
			}
		});
		mnConfiguraes.add(mntmUsuarios);

		//JMenuItem mntmAlterardeletar = new JMenuItem("Alterar/Deletar");		
		mntmAlterardeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaUsuarioAlterar();

			}
		});
		mnConfiguraes.add(mntmAlterardeletar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		fundo = new Panel();
		fundo.setBackground(Color.WHITE);
		contentPane.add(fundo, BorderLayout.CENTER);
		fundo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogo.setIcon(new ImageIcon("image/fundo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		fundo.add(lblLogo);
		
		barra = new Panel();
		barra.setBackground(Color.WHITE);
		contentPane.add(barra, BorderLayout.SOUTH);
		barra.setLayout(new GridLayout(1, 2, 0, 0));
		
		barra2 = new Panel();
		FlowLayout flowLayout = (FlowLayout) barra2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		barra3 = new Panel();
		barra.add(barra2, BorderLayout.EAST);
		barra.add(barra3, BorderLayout.SOUTH);
		
		JLabel lblBarra = new JLabel("");
		lblBarra.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBarra.setIcon(new ImageIcon("image/barra.png"));
		lblBarra.setHorizontalAlignment(SwingConstants.CENTER);
        barra3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		//barra.add(lblBarra);
        
        GrafEstoque demo = new GrafEstoque("Estoque", "Saldo Disponível");
        GrafDoadores demo2 = new GrafDoadores("Doadores", "Doadores Disponíveis para nova doação");
        demo.pack();
        demo2.pack();
        barra2.add(demo);
        barra3.add(demo2);
        demo.setVisible(true);
		demo2.setVisible(true);
        
		
		
		if (nivel == 2){
			nivelMenu(true);
		}
		else {
			nivelMenu(false);
		}
	}

	private void limparTela() {
		
		fundo.setVisible(false);
		
		if (telacadastroconsulta != null) {
			telacadastroconsulta.setVisible(false);
			telacadastroconsulta = null;
		}
		if (telacadastro != null) {
			telacadastro.setVisible(false);
			telacadastro = null;
		}

		if (telausuario != null) {
			telausuario.setVisible(false);
			telausuario = null;
		}

		if (telausuarioalterar != null) {
			telausuarioalterar.setVisible(false);
			telausuarioalterar = null;
		}

		if (consulta != null) {
			consulta.setVisible(false);
			consulta = null;
		}

		if (coleta != null) {
			coleta.setVisible(false);
			coleta = null;
		}
		
		if (entrada != null) {
			entrada.setVisible(false);
			entrada = null;
		}
		
		if (saida != null) {
			saida.setVisible(false);
			saida = null;
		}
		
		if (solicitanteNovo != null) {
			solicitanteNovo.setVisible(false);
			solicitanteNovo = null;
		}
		
		if (solicitanteConsulta != null) {
			solicitanteConsulta.setVisible(false);
			solicitanteConsulta = null;
		}
	}
	
	private void nivelMenu(boolean Nivel) {
		mntmAlterardeletar.setVisible(Nivel);
		mntmUsuarios.setVisible(Nivel);
		mnConfiguraes.setVisible(Nivel);
		mntmSolicitanteConsulta.setVisible(Nivel);
		mntmSolicitanteNovo.setVisible(Nivel);
		
	}
	
	private void novoPaciente() {
		limparTela();
		telacadastro = new CadastroNovo(idUsuario);
		contentPane.add(telacadastro);
		telacadastro.setVisible(true);
	}
	
	private void consultaPaciente() {
		limparTela();
		telacadastroconsulta = new CadastroConsulta(idUsuario);
		contentPane.add(telacadastroconsulta);
		telacadastroconsulta.setVisible(true);
	}
	
	private void solicitanteNovo() {
		limparTela();
		solicitanteNovo = new SolicitanteNovo(idUsuario);
		contentPane.add(solicitanteNovo);
		solicitanteNovo.setVisible(true);
	}
	
	private void solicitanteConsulta() {
		limparTela();
		solicitanteConsulta = new SolicitanteConsulta(idUsuario);
		contentPane.add(solicitanteConsulta);
		solicitanteConsulta.setVisible(true);
	}
	
	private void consulta() {
		limparTela();
		consulta = new TelaConsulta(idUsuario);
		contentPane.add(consulta);
		consulta.setVisible(true);
	}
	
	private void coletaMaterial() {
		limparTela();
		coleta = new Coleta(idUsuario);
		contentPane.add(coleta);
		coleta.setVisible(true);
	}
	
	private void entradaMaterial() {
		limparTela();
		entrada = new EntradaMaterial(idUsuario);
		contentPane.add(entrada);
		entrada.setVisible(true);
	}
	
	private void saidaMaterial() {
		limparTela();
		saida = new SaidaMaterial(idUsuario);
		contentPane.add(saida);
		saida.setVisible(true);
	}
	
	private void relatorioEstoque() {
		relEstoque = null;
		relEstoque = new RelatorioEstoque();
		relEstoque.setVisible(true);
	}
	
	private void relatorioSolicitar() {
		relSolicitar = null;
		relSolicitar = new RelatorioSolicitar();
		relSolicitar.setVisible(true);
	}
	
	private void telaUsuario() {
		limparTela();
		telausuario = new TelaUsuario();
		contentPane.add(telausuario);
		telausuario.setVisible(true);
	}
	
	private void telaUsuarioAlterar() {
		limparTela();
		telausuarioalterar = new TelaUsuarioAlterar();
		contentPane.add(telausuarioalterar);
		telausuarioalterar.setVisible(true);
	}
}
