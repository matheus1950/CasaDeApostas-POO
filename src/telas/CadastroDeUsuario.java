package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;
import entidades.Usuario;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class CadastroDeUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDataNascimento;
	private JTextField txtSenha;
	private JTextField txtConfirmarSenha;
	private JTextField txtEmail;
	private JPasswordField campoSenha;
	private JPasswordField campoConfirmarSenha;
	private JTextField campoEmail;
	private JTextField campoNome;
	private JTextField campoCPF;
	private JTextField txtCPF;
	private JTextField campoDataDeNascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeUsuario frame = new CadastroDeUsuario();
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
	public CadastroDeUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtCadastrar = new JTextArea();
		txtCadastrar.setText("Cadastrar");
		txtCadastrar.setForeground(new Color(128, 255, 255));
		txtCadastrar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtCadastrar.setEditable(false);
		txtCadastrar.setBackground(new Color(0, 64, 0));
		txtCadastrar.setBounds(101, 0, 221, 44);
		contentPane.add(txtCadastrar);
		
		txtNome = new JTextField();
		txtNome.setText("Nome de usuário");
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		txtNome.setBackground(new Color(0, 128, 128));
		txtNome.setBounds(83, 45, 103, 20);
		contentPane.add(txtNome);
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setText("Data de nascimento");
		txtDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataNascimento.setEditable(false);
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBackground(new Color(0, 128, 128));
		txtDataNascimento.setBounds(83, 92, 103, 20);
		contentPane.add(txtDataNascimento);
		
		txtSenha = new JTextField();
		txtSenha.setText("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setEditable(false);
		txtSenha.setColumns(10);
		txtSenha.setBackground(new Color(0, 128, 128));
		txtSenha.setBounds(83, 153, 103, 20);
		contentPane.add(txtSenha);
		
		txtConfirmarSenha = new JTextField();
		txtConfirmarSenha.setText("Confirmar a senha");
		txtConfirmarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfirmarSenha.setEditable(false);
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBackground(new Color(0, 128, 128));
		txtConfirmarSenha.setBounds(83, 184, 103, 20);
		contentPane.add(txtConfirmarSenha);
		
		txtEmail = new JTextField();
		txtEmail.setText("E-mail");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(0, 128, 128));
		txtEmail.setBounds(83, 68, 103, 20);
		contentPane.add(txtEmail);
		
		JButton btnCadastrar = new JButton("Cadastrar");
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
		btnCadastrar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnCadastrar.setBounds(163, 227, 89, 23);
		contentPane.add(btnCadastrar);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(219, 153, 103, 20);
		contentPane.add(campoSenha);
		
		campoConfirmarSenha = new JPasswordField();
		campoConfirmarSenha.setBounds(219, 181, 108, 20);
		contentPane.add(campoConfirmarSenha);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(225, 68, 108, 20);
		contentPane.add(campoEmail);
		
		campoNome = new JTextField();
		campoNome.setColumns(10);
		campoNome.setBounds(225, 45, 108, 20);
		contentPane.add(campoNome);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(225, 122, 103, 20);
		contentPane.add(campoCPF);
		
		txtCPF = new JTextField();
		txtCPF.setText("CPF");
		txtCPF.setHorizontalAlignment(SwingConstants.CENTER);
		txtCPF.setEditable(false);
		txtCPF.setColumns(10);
		txtCPF.setBackground(new Color(0, 128, 128));
		txtCPF.setBounds(83, 122, 103, 20);
		contentPane.add(txtCPF);
		
		campoDataDeNascimento = new JTextField();
		campoDataDeNascimento.setColumns(10);
		campoDataDeNascimento.setBounds(225, 92, 108, 20);
		contentPane.add(campoDataDeNascimento);
	}
}
