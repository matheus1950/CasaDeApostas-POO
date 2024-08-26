package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class TelaDeDeposito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea campoCarteira;
	private JTextField campoValorAdicionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeDeposito frame = new TelaDeDeposito(-1); //padrão
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param idUsuario 
	 */
	public TelaDeDeposito(int idUsuario) {
		TelaDeDeposito essaTela = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 294, 530);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 11, 272, 520);
		contentPane_1.add(panel);
		
		JTextArea txtrDepositar = new JTextArea();
		txtrDepositar.setText("Depositar");
		txtrDepositar.setForeground(new Color(128, 255, 255));
		txtrDepositar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrDepositar.setEditable(false);
		txtrDepositar.setBackground(new Color(0, 64, 0));
		txtrDepositar.setBounds(24, 58, 168, 42);
		panel.add(txtrDepositar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); // acho que aqui
				// posso tirar esse
				// tipo de
				// confirmação
				if (option == JOptionPane.YES_OPTION) {
					essaTela.setVisible(false);
					TelaPrincipalUsuario usuario = new TelaPrincipalUsuario(idUsuario);
					usuario.setVisible(true);
					usuario.atualizarTabela();
				} else {
					JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
				}
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(24, 474, 81, 23);
		panel.add(btnVoltar);
		
		campoCarteira = new JTextArea();
		campoCarteira.setForeground(new Color(255, 0, 0));
		campoCarteira.setBackground(new Color(0, 64, 0));
		campoCarteira.setEditable(false);
		campoCarteira.setColumns(10);
		campoCarteira.setBounds(24, 180, 86, 20);
		panel.add(campoCarteira);
		campoCarteira.setText("R$" + saldoUsuario(idUsuario));
		
		JTextArea txtrCarteiraAtual = new JTextArea();
		txtrCarteiraAtual.setText("Carteira Atual:");
		txtrCarteiraAtual.setForeground(Color.CYAN);
		txtrCarteiraAtual.setBackground(new Color(0, 64, 0));
		txtrCarteiraAtual.setBounds(24, 147, 133, 22);
		panel.add(txtrCarteiraAtual);
		
		campoValorAdicionado = new JTextField();
		campoValorAdicionado.setColumns(10);
		campoValorAdicionado.setBounds(24, 274, 119, 20);
		panel.add(campoValorAdicionado);
		
		JTextArea txtrValorADepositar = new JTextArea();
		txtrValorADepositar.setText("Valor a depositar:");
		txtrValorADepositar.setForeground(Color.CYAN);
		txtrValorADepositar.setBackground(new Color(0, 64, 0));
		txtrValorADepositar.setBounds(24, 234, 151, 22);
		panel.add(txtrValorADepositar);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositar(idUsuario);
			}
		});
		btnDepositar.setForeground(new Color(0, 0, 128));
		btnDepositar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnDepositar.setBounds(152, 474, 81, 23);
		panel.add(btnDepositar);
	}
	
	public double saldoUsuario(int idUsuario) {
    	DaoFactory dao = new DaoFactory();
    	return dao.criarUsuarioDaoJDBC().findUsuarioById(idUsuario).getCarteira();
    }
	
	public void depositar(int idUsuario) {
    	DaoFactory dao = new DaoFactory();
    	dao.criarUsuarioDaoJDBC().editarCarteira(idUsuario, Double.parseDouble(campoValorAdicionado.getText()));
    	campoCarteira.setText("R$" + saldoUsuario(idUsuario));
    }
}
