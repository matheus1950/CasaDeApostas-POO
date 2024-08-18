package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.impl.DaoFactory;
import entidades.Usuario;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class CadastroDeUsuario {

	private JFrame frame;
	private JTextArea txtCadastrar;
	private JTextField txtNome;
	private JTextField txtDatanascimento;
	private JTextField txtSenha;
	private JTextField txtConfirmarASenha;
	private JTextField txtEmail;
	private JButton btnCadastrar;
	private JPasswordField campoSenha;
	private JPasswordField campoConfirmarSenha;
	private JTextField campoDataDeNascimento;
	private JTextField campoEmail;
	private JTextField campoNome;
	private JTextField txtCPF;
	private JTextField campoCPF;
	
	//melhorar a tela - alguns campos e botões aparecem incompletos

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
		frame.getContentPane().setBackground(new Color(0, 64, 0));
		frame.setBounds(100, 100, 413, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCadastrar = new JTextArea();
		txtCadastrar.setForeground(new Color(128, 255, 255));
		txtCadastrar.setEditable(false);
		txtCadastrar.setBackground(new Color(0, 64, 0));
		txtCadastrar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtCadastrar.setText("Cadastrar");
		txtCadastrar.setBounds(96, 11, 221, 44);
		frame.getContentPane().add(txtCadastrar);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setBackground(new Color(0, 128, 128));
		txtNome.setEditable(false);
		txtNome.setText("Nome de usuário");
		txtNome.setBounds(96, 66, 103, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtDatanascimento = new JTextField();
		txtDatanascimento.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatanascimento.setBackground(new Color(0, 128, 128));
		txtDatanascimento.setEditable(false);
		txtDatanascimento.setText("Data de nascimento");
		txtDatanascimento.setBounds(96, 122, 103, 20);
		frame.getContentPane().add(txtDatanascimento);
		txtDatanascimento.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setBackground(new Color(0, 128, 128));
		txtSenha.setEditable(false);
		txtSenha.setText("Senha");
		txtSenha.setBounds(96, 153, 103, 20);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		txtConfirmarASenha = new JTextField();
		txtConfirmarASenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfirmarASenha.setBackground(new Color(0, 128, 128));
		txtConfirmarASenha.setEditable(false);
		txtConfirmarASenha.setText("Confirmar a senha");
		txtConfirmarASenha.setBounds(96, 180, 103, 20);
		frame.getContentPane().add(txtConfirmarASenha);
		txtConfirmarASenha.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setBackground(new Color(0, 128, 128));
		txtEmail.setEditable(false);
		txtEmail.setText("E-mail");
		txtEmail.setBounds(96, 91, 103, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		//configurar situação em que a senha não é inserida(por algum motivo o equals não funcionou como em campoNome)
		//configurar data de nascimento também!(só aceita formato yyyy-mm-dd)
		//tirar linhas de confirmação!
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				DaoFactory dao = new DaoFactory();
				Random idContrato = new Random();
				
				//verificação senha
				if(campoSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(btnCadastrar, "Senha não digitada!"); //obviamente tirar essa linha depois
					return; //para interromper a ação
				}
				else if(campoSenha.getText().equals(campoConfirmarSenha.getText())) {
					usuario.setSenha(campoSenha.getText());
					JOptionPane.showMessageDialog(btnCadastrar, "Senha: " + usuario.getSenha()); //obviamente tirar essa linha depois
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Senhas não correspondem!");
					return;
				}
				
				//verificação nome
				if(!campoNome.getText().equals("")) {
					usuario.setNome(campoNome.getText());
					JOptionPane.showMessageDialog(btnCadastrar, "Nome: " + usuario.getNome());
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Nome não inserido!");
					return;
				}
				
				//verificação data de nascimento
				if(!campoDataDeNascimento.getText().equals("")) {
					usuario.setDataNascimento(Date.valueOf(campoDataDeNascimento.getText()));
					JOptionPane.showMessageDialog(btnCadastrar, "Data de nascimento: " + usuario.getDataNascimento());
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Data de nascimento não digitada!");
					return;
				}
				
				//verificação email
				if(!campoEmail.getText().equals("")) {
					usuario.setEmail(campoEmail.getText());
					JOptionPane.showMessageDialog(btnCadastrar, "Email: " + usuario.getEmail());
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Email não digitado!");
					return;
				}
				
				//verificação cpf - faltam melhorias
				if(!campoCPF.getText().equals("")) {
					usuario.setCpf(Integer.parseInt(campoCPF.getText()));
					JOptionPane.showMessageDialog(btnCadastrar, "CPF: " + usuario.getCpf());
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "CPF não digitado!");
					return;
				}
				
				//passando um inteiro aleatório para o id de contrato!
				usuario.setIdDeContrato(idContrato.nextInt(Integer.MAX_VALUE)); //intervalo de 0 até o maior inteiro suportado em int
				JOptionPane.showMessageDialog(btnCadastrar, usuario.getIdDeContrato());
				
				//inserindo no banco de dados!
				try {
					dao.criarUsuarioDaoJDBC().insert(usuario);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBounds(96, 284, 89, 23);
		frame.getContentPane().add(btnCadastrar);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(214, 153, 103, 20);
		frame.getContentPane().add(campoSenha);
		
		campoConfirmarSenha = new JPasswordField();
		campoConfirmarSenha.setBounds(209, 180, 108, 20);
		frame.getContentPane().add(campoConfirmarSenha);
		
		campoDataDeNascimento = new JTextField();
		campoDataDeNascimento.setBounds(209, 122, 108, 20);
		frame.getContentPane().add(campoDataDeNascimento);
		campoDataDeNascimento.setColumns(10);
		
		campoEmail = new JTextField();
		campoEmail.setBounds(209, 91, 108, 20);
		frame.getContentPane().add(campoEmail);
		campoEmail.setColumns(10);
		
		campoNome = new JTextField();
		campoNome.setBounds(214, 66, 103, 20);
		frame.getContentPane().add(campoNome);
		campoNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setText("CPF");
		txtCPF.setHorizontalAlignment(SwingConstants.CENTER);
		txtCPF.setEditable(false);
		txtCPF.setColumns(10);
		txtCPF.setBackground(new Color(0, 128, 128));
		txtCPF.setBounds(96, 211, 103, 20);
		frame.getContentPane().add(txtCPF);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(209, 211, 108, 20);
		frame.getContentPane().add(campoCPF);
	}
}
