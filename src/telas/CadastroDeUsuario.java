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
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

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
	private JButton btnVoltar;

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
		setTitle("cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 381);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 318, 309);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(0, 64, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtCadastrar = new JTextArea();
		txtCadastrar.setBounds(81, 2, 158, 42);
		txtCadastrar.setText("Cadastrar");
		txtCadastrar.setForeground(new Color(128, 255, 255));
		txtCadastrar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtCadastrar.setEditable(false);
		txtCadastrar.setBackground(new Color(0, 64, 0));
		panel.add(txtCadastrar);
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setBounds(47, 112, 116, 20);
		txtDataNascimento.setText("Data de nascimento");
		txtDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataNascimento.setEditable(false);
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBackground(new Color(0, 128, 128));
		panel.add(txtDataNascimento);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(47, 177, 86, 20);
		txtSenha.setText("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setEditable(false);
		txtSenha.setColumns(10);
		txtSenha.setBackground(new Color(0, 128, 128));
		panel.add(txtSenha);
		
		txtConfirmarSenha = new JTextField();
		txtConfirmarSenha.setBounds(47, 208, 102, 20);
		txtConfirmarSenha.setText("Confirmar a senha");
		txtConfirmarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfirmarSenha.setEditable(false);
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBackground(new Color(0, 128, 128));
		panel.add(txtConfirmarSenha);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(47, 81, 86, 20);
		txtEmail.setText("E-mail");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(0, 128, 128));
		panel.add(txtEmail);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(47, 275, 81, 23);
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
		panel.add(btnCadastrar);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(145, 177, 118, 20);
		panel.add(campoSenha);
		
		campoConfirmarSenha = new JPasswordField();
		campoConfirmarSenha.setBounds(153, 208, 110, 20);
		panel.add(campoConfirmarSenha);
		
		campoEmail = new JTextField();
		campoEmail.setBounds(153, 81, 86, 20);
		campoEmail.setColumns(10);
		panel.add(campoEmail);
		
		campoNome = new JTextField();
		campoNome.setBounds(153, 55, 86, 20);
		campoNome.setColumns(10);
		panel.add(campoNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(47, 55, 94, 20);
		txtNome.setText("Nome de usuário");
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		txtNome.setBackground(new Color(0, 128, 128));
		panel.add(txtNome);
		
		campoCPF = new JTextField();
		campoCPF.setBounds(153, 143, 86, 20);
		campoCPF.setColumns(10);
		panel.add(campoCPF);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(47, 143, 86, 20);
		txtCPF.setText("CPF");
		txtCPF.setHorizontalAlignment(SwingConstants.CENTER);
		txtCPF.setEditable(false);
		txtCPF.setColumns(10);
		txtCPF.setBackground(new Color(0, 128, 128));
		panel.add(txtCPF);
		
		campoDataDeNascimento = new JTextField();
		campoDataDeNascimento.setBounds(173, 112, 86, 20);
		campoDataDeNascimento.setColumns(10);
		panel.add(campoDataDeNascimento);
		
		CadastroDeUsuario essaTela = this;
		
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
		btnVoltar.setBounds(194, 275, 81, 23);
		panel.add(btnVoltar);
	}
}