package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BoasVindas {

	private JFrame frame;
	private JTextField txtPossuiConta;
	private JTextField txtNoPossuiCadastro;
	private JTextField txtSejaBemvindo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoasVindas window = new BoasVindas();
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
	public BoasVindas() {
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
		
		JButton btnNewButton = new JButton("Logar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 86, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		txtPossuiConta = new JTextField();
		txtPossuiConta.setText("Possui conta?");
		txtPossuiConta.setBounds(10, 70, 86, 20);
		frame.getContentPane().add(txtPossuiConta);
		txtPossuiConta.setColumns(10);
		
		txtNoPossuiCadastro = new JTextField();
		txtNoPossuiCadastro.setText("Não possui cadastro?");
		txtNoPossuiCadastro.setBounds(10, 131, 121, 20);
		frame.getContentPane().add(txtNoPossuiCadastro);
		txtNoPossuiCadastro.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setBounds(10, 152, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		txtSejaBemvindo = new JTextField();
		txtSejaBemvindo.setFont(new Font("Tahoma", Font.BOLD, 32));
		txtSejaBemvindo.setText("Seja Bem-vindo!");
		txtSejaBemvindo.setBounds(91, 11, 284, 52);
		frame.getContentPane().add(txtSejaBemvindo);
		txtSejaBemvindo.setColumns(10);
	}

}
