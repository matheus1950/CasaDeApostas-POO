package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;
import entidades.Evento;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;

public class CadastroDeEvento extends JFrame {
	//Incompleta
	//Não vem de lugar algum ainda, ela deve vir da tela de administração de eventos

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeDoEvento;
	private JTextField campoNome;
	private JTextField campoDescricao;
	private JTextField textDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeEvento frame = new CadastroDeEvento(-1, new TelaPrincipalAdm(-1)); //não sei se tem problema iniciar em 0
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
	public CadastroDeEvento(int idUsuario, TelaPrincipalAdm frame) {
		setTitle("Cadastrar Evento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 22, 384, 216);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Evento evento = new Evento();
				DaoFactory dao = new DaoFactory();
				
				if(!campoNome.getText().equals("")) {
					evento.setNome(campoNome.getText());
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Nome não digitado!");
					return;
				}
				
				if(!campoDescricao.getText().equals("")) {
					evento.setDescricao(campoDescricao.getText());
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Descrição não digitada!");
					return;
				}
				
				evento.setIdDeUsuario(idUsuario);
				dao.criarEventoDaoJDBC().insert(evento);
				frame.atualizarTabela();
			}
		});
		btnCadastrar.setBounds(120, 182, 119, 23);
		panel.add(btnCadastrar);
		
		txtNomeDoEvento = new JTextField();
		txtNomeDoEvento.setBackground(new Color(64, 128, 128));
		txtNomeDoEvento.setEditable(false);
		txtNomeDoEvento.setText("Nome");
		txtNomeDoEvento.setBounds(24, 72, 50, 20);
		panel.add(txtNomeDoEvento);
		txtNomeDoEvento.setColumns(10);
		
		campoNome = new JTextField();
		campoNome.setBounds(134, 72, 86, 20);
		panel.add(campoNome);
		campoNome.setColumns(10);
		
		campoDescricao = new JTextField();
		campoDescricao.setBounds(134, 103, 86, 20);
		panel.add(campoDescricao);
		campoDescricao.setColumns(10);
		
		textDescricao = new JTextField();
		textDescricao.setBackground(new Color(64, 128, 128));
		textDescricao.setEditable(false);
		textDescricao.setText("Descricao");
		textDescricao.setBounds(24, 103, 86, 20);
		panel.add(textDescricao);
		textDescricao.setColumns(10);
		
		JTextArea txtrCadastroDeEvento = new JTextArea();
		txtrCadastroDeEvento.setText("Cadastrar evento");
		txtrCadastroDeEvento.setForeground(new Color(128, 255, 255));
		txtrCadastroDeEvento.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrCadastroDeEvento.setEditable(false);
		txtrCadastroDeEvento.setBackground(new Color(0, 64, 0));
		txtrCadastroDeEvento.setBounds(46, 11, 285, 42);
		panel.add(txtrCadastroDeEvento);
	}
}
