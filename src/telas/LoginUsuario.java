package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JTextField txtLogin;
	private JTextField campoLogin;
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
				//verificacao do campo login
				if(!campoLogin.getText().equals("")) {
					
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
			}
		});
		btnLogar.setBounds(64, 145, 59, 23);
		btnLogar.setForeground(new Color(0, 0, 128));
		btnLogar.setBackground(UIManager.getColor("CheckBox.focus"));
		contentPane.add(btnLogar);
		
		txtLogin = new JTextField();
		txtLogin.setBackground(new Color(64, 128, 128));
		txtLogin.setBounds(54, 64, 86, 20);
		txtLogin.setText("Login");
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setEditable(false);
		txtLogin.setColumns(10);
		contentPane.add(txtLogin);
		
		campoLogin = new JTextField();
		campoLogin.setBounds(150, 64, 86, 20);
		campoLogin.setColumns(10);
		contentPane.add(campoLogin);
		
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
