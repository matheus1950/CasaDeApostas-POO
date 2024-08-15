package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class CadastroDeUsuario {

	private JFrame frame;
	private JTextField txtCadastrar;
	private JTextField txtNome;
	private JTextField txtDatanascimento;
	private JTextField txtSenha;
	private JTextField txtConfirmarASenha;
	private JTextField txtEmail;
	private JButton btnNewButton;
	
	//completar botões

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeUsuario window = new CadastroDeUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroDeUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCadastrar = new JTextField();
		txtCadastrar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtCadastrar.setText("Cadastrar");
		txtCadastrar.setBounds(96, 11, 221, 44);
		frame.getContentPane().add(txtCadastrar);
		txtCadastrar.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setText("Nome de usuário");
		txtNome.setBounds(96, 66, 103, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtDatanascimento = new JTextField();
		txtDatanascimento.setText("Data de nascimento");
		txtDatanascimento.setBounds(96, 122, 113, 20);
		frame.getContentPane().add(txtDatanascimento);
		txtDatanascimento.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setText("Senha");
		txtSenha.setBounds(96, 153, 86, 20);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		txtConfirmarASenha = new JTextField();
		txtConfirmarASenha.setText("Confirmar a senha");
		txtConfirmarASenha.setBounds(96, 180, 103, 20);
		frame.getContentPane().add(txtConfirmarASenha);
		txtConfirmarASenha.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setText("E-mail");
		txtEmail.setBounds(96, 91, 86, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBounds(96, 211, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
