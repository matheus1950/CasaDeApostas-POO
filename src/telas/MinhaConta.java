package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;
import entidades.Usuario;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MinhaConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField textField_1;
	private JTextField txtEmail;
	private JTextField campoEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinhaConta frame = new MinhaConta(-1); //-1 padrão
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
	public MinhaConta(int idUsuario) {
		MinhaConta essaTela = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 757, 412);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 11, 737, 377);
		contentPane_1.add(panel);
		
		JTextArea txtrMinhaConta = new JTextArea();
		txtrMinhaConta.setText("Minha conta");
		txtrMinhaConta.setForeground(new Color(128, 255, 255));
		txtrMinhaConta.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrMinhaConta.setEditable(false);
		txtrMinhaConta.setBackground(new Color(0, 64, 0));
		txtrMinhaConta.setBounds(211, 0, 321, 42);
		panel.add(txtrMinhaConta);
		
		campoNome = new JTextField();
		campoNome.setColumns(10);
		campoNome.setBounds(20, 292, 164, 20);
		panel.add(campoNome);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setText("Nome");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(64, 128, 128));
		textField_1.setBounds(20, 261, 164, 20);
		panel.add(textField_1);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(64, 128, 128));
		txtEmail.setBounds(398, 261, 164, 20);
		panel.add(txtEmail);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 218, 727, 12);
		panel.add(separator);
		
		//Coletando informações de usuário pelo id
		DaoFactory dao = new DaoFactory();
		
		Usuario usuario = dao.criarUsuarioDaoJDBC().findUsuarioById(idUsuario);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				salvar(btnSalvar, idUsuario, usuario.getNome(), usuario.getEmail());
				//frame.atualizarTabela(idEvento);
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 128));
		btnSalvar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnSalvar.setBounds(211, 343, 89, 23);
		panel.add(btnSalvar);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(398, 292, 164, 20);
		panel.add(campoEmail);
		
		JTextArea txtrUsuario = new JTextArea();
		txtrUsuario.setText("Dados da minha conta:");
		txtrUsuario.setForeground(Color.CYAN);
		txtrUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtrUsuario.setEditable(false);
		txtrUsuario.setBackground(new Color(0, 64, 0));
		txtrUsuario.setBounds(10, 96, 321, 42);
		panel.add(txtrUsuario);
		
		JTextArea campoUsuarioEdit = new JTextArea();
		campoUsuarioEdit.setText("Nome: " + usuario.getNome() + "\nEmail: " + usuario.getEmail());
		campoUsuarioEdit.setRows(2);
		campoUsuarioEdit.setForeground(Color.RED);
		campoUsuarioEdit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		campoUsuarioEdit.setEditable(false);
		campoUsuarioEdit.setBackground(new Color(0, 64, 0));
		campoUsuarioEdit.setBounds(20, 138, 707, 69);
		panel.add(campoUsuarioEdit);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnLogout, "Deseja realmente fazer logout?");
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		new Login().setVisible(true);
        		}
        		else {
        			JOptionPane.showMessageDialog(btnLogout, "Logout cancelado!");
        		}
			}
		});
		btnLogout.setForeground(Color.RED);
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(613, 18, 114, 23);
		panel.add(btnLogout);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
        			if(dao.criarUsuarioDaoJDBC().findPermissaoById(idUsuario) == true) {
		        		essaTela.setVisible(false);
		        		TelaPrincipalAdm adm = new TelaPrincipalAdm(idUsuario);
		        		adm.setVisible(true);
		        		adm.atualizarTabela();
        			}
        			else {
        				essaTela.setVisible(false);
		        		TelaPrincipalUsuario user = new TelaPrincipalUsuario(idUsuario);
		        		user.setVisible(true);
		        		user.atualizarTabela();
        			}
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(374, 343, 81, 23);
		panel.add(btnVoltar);
		
		JButton btnNewButton = new JButton("Senha");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarSenha senha = new AlterarSenha(idUsuario, usuario.getSenha());
        		//this aqui é para passar a própria frame no argumento ^
        		senha.setVisible(true);
        		essaTela.setVisible(false);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBackground(UIManager.getColor("CheckBox.focus"));
		btnNewButton.setBounds(624, 291, 89, 23);
		panel.add(btnNewButton);
		
		JTextArea txtrModificar = new JTextArea();
		txtrModificar.setText("Modificar");
		txtrModificar.setForeground(new Color(128, 255, 255));
		txtrModificar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrModificar.setEditable(false);
		txtrModificar.setBackground(new Color(0, 64, 0));
		txtrModificar.setBounds(223, 218, 164, 42);
		panel.add(txtrModificar);
		
		JTextArea txtrModificarSenha = new JTextArea();
		txtrModificarSenha.setText("alterar senha:");
		txtrModificarSenha.setForeground(new Color(128, 255, 255));
		txtrModificarSenha.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrModificarSenha.setEditable(false);
		txtrModificarSenha.setBackground(new Color(0, 64, 0));
		txtrModificarSenha.setBounds(602, 256, 125, 42);
		panel.add(txtrModificarSenha);
	}
	
	public void salvar(JButton botao, int id, String nomeAntigo, String emailAntigo) {
		DaoFactory dao = new DaoFactory();
		//Se forem ambos campos vazios ou ambos iguais aos campos já registrados, não chama o método de editar
		if((campoNome.getText().equals("") && campoEmail.getText().equals("")) ||
				(campoNome.getText().equals(nomeAntigo) && campoEmail.getText().equals(emailAntigo))) {
			JOptionPane.showMessageDialog(botao, "Não houve tentativa de alteração!");
		}
		else {
			if(!campoNome.getText().equals("")) {
				dao.criarUsuarioDaoJDBC().editarNome(id, campoNome.getText());
			}
			if(!campoEmail.getText().equals("")) {
				dao.criarUsuarioDaoJDBC().editarEmail(id, campoEmail.getText());
			}
		}
	}
}
