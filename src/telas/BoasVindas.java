package telas;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class BoasVindas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtBemvindo;
	private JTextField txtJTemUma;
	private JTextField txtNoTemCadastro;

	/**
	 * Create the panel.
	 */
	public BoasVindas() {
		setLayout(null);
		
		txtBemvindo = new JTextField();
		txtBemvindo.setBounds(133, 11, 174, 44);
		txtBemvindo.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtBemvindo.setText("Bem-vindo");
		add(txtBemvindo);
		txtBemvindo.setColumns(10);
		
		JButton btnNewButton = new JButton("Logar");
		btnNewButton.setBounds(10, 88, 89, 23);
		add(btnNewButton);
		
		txtJTemUma = new JTextField();
		txtJTemUma.setText("Já tem uma conta?");
		txtJTemUma.setBounds(10, 67, 101, 20);
		add(txtJTemUma);
		txtJTemUma.setColumns(10);
		
		txtNoTemCadastro = new JTextField();
		txtNoTemCadastro.setText("Não tem cadastro?");
		txtNoTemCadastro.setBounds(10, 162, 101, 20);
		add(txtNoTemCadastro);
		txtNoTemCadastro.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setBounds(10, 183, 89, 23);
		add(btnNewButton_1);

	}
}
