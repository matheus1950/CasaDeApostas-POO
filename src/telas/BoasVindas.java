package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class BoasVindas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoasVindas frame = new BoasVindas();
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
	public BoasVindas() {
		setTitle("boas vindas!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 247);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 53, 129, 20);
		textField.setText("Não possui cadastro?");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 128, 128));
		contentPane.add(textField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroDeUsuario().setVisible(true);
			}
		});
		btnCadastrar.setBounds(10, 75, 81, 23);
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBackground(UIManager.getColor("CheckBox.focus"));
		contentPane.add(btnCadastrar);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 133, 86, 20);
		textField_1.setText("Possui conta?");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(0, 128, 128));
		contentPane.add(textField_1);
		
		JTextArea txtBoasVindas = new JTextArea();
		txtBoasVindas.setBounds(10, 0, 260, 42);
		txtBoasVindas.setText("Seja bem-vindo!");
		txtBoasVindas.setForeground(new Color(128, 255, 255));
		txtBoasVindas.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtBoasVindas.setEditable(false);
		txtBoasVindas.setBackground(new Color(0, 64, 0));
		contentPane.add(txtBoasVindas);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginUsuario().setVisible(true);
			}
		});
		btnLogar.setBounds(10, 157, 59, 23);
		btnLogar.setForeground(new Color(0, 0, 128));
		contentPane.add(btnLogar);
	}

}
