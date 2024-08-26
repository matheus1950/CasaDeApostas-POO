package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField campoEmail;
	private JTextField txtSenha;
	private JPasswordField campoSenha;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 382, 379);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrLoginDeUsurio = new JTextArea();
		txtrLoginDeUsurio.setBounds(34, 11, 263, 42);
		txtrLoginDeUsurio.setText("Login de usuário");
		txtrLoginDeUsurio.setForeground(new Color(128, 255, 255));
		txtrLoginDeUsurio.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrLoginDeUsurio.setEditable(false);
		txtrLoginDeUsurio.setBackground(new Color(0, 64, 0));
		contentPane.add(txtrLoginDeUsurio);
		
		Login essaTela = this;
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar(essaTela);			
			}
		});
		btnLogar.setBounds(78, 261, 59, 23);
		btnLogar.setForeground(new Color(0, 0, 128));
		btnLogar.setBackground(UIManager.getColor("CheckBox.focus"));
		contentPane.add(btnLogar);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(64, 128, 128));
		txtEmail.setBounds(51, 109, 86, 20);
		txtEmail.setText("Email");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);
		
		campoEmail = new JTextField();
		campoEmail.setBounds(203, 109, 86, 20);
		campoEmail.setColumns(10);
		contentPane.add(campoEmail);
		
		txtSenha = new JTextField();
		txtSenha.setBackground(new Color(64, 128, 128));
		txtSenha.setBounds(51, 191, 86, 20);
		txtSenha.setText("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setEditable(false);
		txtSenha.setColumns(10);
		contentPane.add(txtSenha);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(203, 191, 86, 20);
		contentPane.add(campoSenha);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		new BoasVindas().setVisible(true);
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(203, 261, 71, 23);
		contentPane.add(btnVoltar);
	}
	
	public void logar(Login essaTela) {
		DaoFactory dao = new DaoFactory();
		
		//verificacao do campo login - por email	
		if(!campoEmail.getText().equals("")) {
			
		}
		else {
			JOptionPane.showMessageDialog(this, "Login não digitado!");
			return;
		}
		
		//verificacao do campo senha
		if(!campoSenha.getText().equals("")) {
			
		}
		else {
			JOptionPane.showMessageDialog(this, "Senha não digitada!");
			return;
		}
		
		//verificação da combinação email e senha
		if(campoSenha.getText().equals(dao.criarPessoaDaoJDBC().findPasswordByEmail(campoEmail.getText()))) {
			JOptionPane.showMessageDialog(this, "Logado!");
			//se o campo permissão for falso -> abrir tela de usuário
			if(dao.criarPessoaDaoJDBC().findPermissaoByEmailSenha(campoEmail.getText(), campoSenha.getText()) == false) {
				//necessário colocar a instância numa variável para poder utilizar o método de preencher a tabela(caso contrário vem vazia)
				TelaPrincipalUsuario telaUsuario = new TelaPrincipalUsuario(dao.criarPessoaDaoJDBC().findIdByEmailSenha(campoEmail.getText(), campoSenha.getText()));
				
			    telaUsuario.preencherTabela(dao.criarEventoDaoJDBC().listarTodosEventos());
			    telaUsuario.setVisible(true);
			    essaTela.setVisible(false);
		        telaUsuario.setVisible(true);
			}//se o campo permissão for verdadeiro -> abrir tela de adm
			else {
				TelaPrincipalAdm telaAdm = new TelaPrincipalAdm(dao.criarPessoaDaoJDBC().findIdByEmailSenha(campoEmail.getText(), campoSenha.getText()));
				
		        telaAdm.preencherTabela(dao.criarEventoDaoJDBC().listarTodosEventos());
		        essaTela.setVisible(false);
		        telaAdm.setVisible(true);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Combinação incorreta de email e senha!");
		}
	}
}
