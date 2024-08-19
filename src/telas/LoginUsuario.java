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

public class LoginUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField campoEmail;
	private JTextField txtSenha;
	private JPasswordField campoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUsuario frame = new LoginUsuario();
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
	public LoginUsuario() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 236);
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
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DaoFactory dao = new DaoFactory();
				
				//verificacao do campo login
				
				if(!campoEmail.getText().equals("")) {
					
				}
				else {
					JOptionPane.showMessageDialog(btnLogar, "Login não digitado!");
					return;
				}
				
				//verificacao do campo senha
				if(!campoSenha.getText().equals("")) {
					
				}
				else {
					JOptionPane.showMessageDialog(btnLogar, "Senha não digitada!");
					return;
				}
				
				//verificação da combinação email - senha
				if(campoSenha.getText().equals(dao.criarUsuarioDaoJDBC().findPasswordByEmail(campoEmail.getText()))) {
					JOptionPane.showMessageDialog(btnLogar, "Sucesso! Falta criar tela de usuário!");
				}
				else {
					JOptionPane.showMessageDialog(btnLogar, "Combinação incorreta de email e senha!");
				}
				
			}
		});
		btnLogar.setBounds(64, 145, 59, 23);
		btnLogar.setForeground(new Color(0, 0, 128));
		btnLogar.setBackground(UIManager.getColor("CheckBox.focus"));
		contentPane.add(btnLogar);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(64, 128, 128));
		txtEmail.setBounds(54, 64, 86, 20);
		txtEmail.setText("Email");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);
		
		campoEmail = new JTextField();
		campoEmail.setBounds(150, 64, 86, 20);
		campoEmail.setColumns(10);
		contentPane.add(campoEmail);
		
		txtSenha = new JTextField();
		txtSenha.setBackground(new Color(64, 128, 128));
		txtSenha.setBounds(54, 95, 86, 20);
		txtSenha.setText("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setEditable(false);
		txtSenha.setColumns(10);
		contentPane.add(txtSenha);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(150, 95, 86, 20);
		contentPane.add(campoSenha);
	}
}
