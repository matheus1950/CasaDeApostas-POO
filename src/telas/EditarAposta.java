package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarAposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoDescricao;
	private JTextField txtOdd;
	private JTextField txtDescrio;
	private JTextField campoOdd;
	private JTextArea campoEventoEdit;
	private JButton btnLogout;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarAposta frame = new EditarAposta(-1.0, "testeDescricao", -1, new ApostasAdm(-1), -1); //Rever se posso passar esses argumentos como padrão
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param id 
	 */
	public EditarAposta(double odd, String descricao, int id, ApostasAdm frame, int idEvento) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 11, 737, 377);
		contentPane.add(panel);
		
		JTextArea txtEditarAposta = new JTextArea();
		txtEditarAposta.setText("Edição de Aposta");
		txtEditarAposta.setForeground(new Color(128, 255, 255));
		txtEditarAposta.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtEditarAposta.setEditable(false);
		txtEditarAposta.setBackground(new Color(0, 64, 0));
		txtEditarAposta.setBounds(211, 0, 321, 42);
		panel.add(txtEditarAposta);
		
		campoDescricao = new JTextField();
		campoDescricao.setBounds(30, 267, 164, 20);
		panel.add(campoDescricao);
		campoDescricao.setColumns(10);
		
		txtOdd = new JTextField();
		txtOdd.setEditable(false);
		txtOdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtOdd.setText("Odd");
		txtOdd.setToolTipText("");
		txtOdd.setBackground(new Color(64, 128, 128));
		txtOdd.setBounds(478, 236, 164, 20);
		panel.add(txtOdd);
		txtOdd.setColumns(10);
		
		txtDescrio = new JTextField();
		txtDescrio.setEditable(false);
		txtDescrio.setBackground(new Color(64, 128, 128));
		txtDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescrio.setText("Descrição");
		txtDescrio.setBounds(30, 236, 164, 20);
		panel.add(txtDescrio);
		txtDescrio.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 218, 727, 12);
		panel.add(separator);
		
		double oddAntiga = odd;
		String descricaoAntiga = descricao;
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar(btnSalvar, id, oddAntiga, descricaoAntiga);
				frame.atualizarTabela(idEvento);
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 128));
		btnSalvar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnSalvar.setBounds(211, 327, 89, 23);
		panel.add(btnSalvar);
		
		campoOdd = new JTextField();
		campoOdd.setBounds(478, 267, 164, 20);
		panel.add(campoOdd);
		campoOdd.setColumns(10);
		
		JTextArea txtrApostaEditada = new JTextArea();
		txtrApostaEditada.setText("Aposta a ser editada:");
		txtrApostaEditada.setForeground(new Color(0, 255, 255));
		txtrApostaEditada.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtrApostaEditada.setEditable(false);
		txtrApostaEditada.setBackground(new Color(0, 64, 0));
		txtrApostaEditada.setBounds(10, 96, 321, 42);
		panel.add(txtrApostaEditada);
	
		
		campoOdd.setText(String.valueOf(oddAntiga));
		campoDescricao.setText(descricaoAntiga);
		
		campoEventoEdit = new JTextArea();
		campoEventoEdit.setEditable(false);
		campoEventoEdit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		campoEventoEdit.setRows(2);
		campoEventoEdit.setText("Example");
		campoEventoEdit.setForeground(new Color(255, 0, 0));
		campoEventoEdit.setBackground(new Color(0, 64, 0));
		campoEventoEdit.setBounds(20, 138, 707, 69);
		panel.add(campoEventoEdit);
		
		//Não consigo fazer a tela mostrar a descrição inteira de alguns eventos
		campoEventoEdit.setText("Descrição: " + descricao + "\nOdd: " + odd);
		
		EditarAposta essaTela = this;
		
		btnLogout = new JButton("Logout");
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
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		new TelaPrincipalAdm(idEvento).setVisible(true);
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
			}		
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(364, 327, 81, 23);
		panel.add(btnVoltar);
		
	}
	
	public void salvar(JButton botao, int id, double oddAntiga, String descricaoAntiga) {
		DaoFactory dao = new DaoFactory();
		//Se forem ambos campos vazios ou ambos iguais aos campos já registrados, não chama o método de editar
		if((campoOdd.getText().equals("") && campoDescricao.getText().equals("")) ||
				(campoOdd.getText().equals(oddAntiga) && campoDescricao.getText().equals(descricaoAntiga))) {
			JOptionPane.showMessageDialog(botao, "Não houve tentativa de alteração!");
		}
		else {
			if(!campoOdd.getText().equals("")) {
				dao.criarApostaDaoJDBC().editarOdd(id, Double.parseDouble(campoOdd.getText()));
			}
			if(!campoDescricao.getText().equals("")) {
				dao.criarApostaDaoJDBC().editarDescricao(id, campoDescricao.getText());
			}
			JOptionPane.showMessageDialog(botao, "Aposta atualizada com sucesso!");
		}
	}
	
}
