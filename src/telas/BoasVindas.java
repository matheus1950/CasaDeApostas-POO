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
import javax.swing.SwingConstants;

public class BoasVindas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtPossuiCadastro;

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
		BoasVindas essaTela = this;
		setTitle("boas vindas!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(90, 72, 129, 20);
		textField.setText("Não possui cadastro?");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 128, 128));
		contentPane.add(textField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroDeUsuario().setVisible(true);
				essaTela.setVisible(false);
			}
		});
		btnCadastrar.setBounds(90, 114, 129, 23);
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBackground(UIManager.getColor("CheckBox.focus"));
		contentPane.add(btnCadastrar);
		
		txtPossuiCadastro = new JTextField();
		txtPossuiCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		txtPossuiCadastro.setBounds(90, 192, 129, 20);
		txtPossuiCadastro.setText("Possui cadastro?");
		txtPossuiCadastro.setEditable(false);
		txtPossuiCadastro.setColumns(10);
		txtPossuiCadastro.setBackground(new Color(0, 128, 128));
		contentPane.add(txtPossuiCadastro);
		
		JTextArea txtBoasVindas = new JTextArea();
		txtBoasVindas.setBounds(39, 0, 260, 42);
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
				new Login().setVisible(true);
				essaTela.setVisible(false);
			}
		});
		btnLogar.setBounds(90, 239, 129, 23);
		btnLogar.setForeground(new Color(0, 0, 128));
		contentPane.add(btnLogar);
	}

}
