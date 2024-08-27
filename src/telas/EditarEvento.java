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

public class EditarEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoDescricao;
	private JTextField txtNome;
	private JTextField txtDescrio;
	private JTextField campoNome;
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
					EditarEvento frame = new EditarEvento("testeNome", "testeDescricao", -1, new TelaPrincipalAdm(-1)); //Rever se posso passar esses argumentos como padrão
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
	public EditarEvento(String nome, String descricao, int id, TelaPrincipalAdm frame) {
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
		
		JTextArea txtEditarEvento = new JTextArea();
		txtEditarEvento.setText("Edição de Evento");
		txtEditarEvento.setForeground(new Color(128, 255, 255));
		txtEditarEvento.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtEditarEvento.setEditable(false);
		txtEditarEvento.setBackground(new Color(0, 64, 0));
		txtEditarEvento.setBounds(211, 0, 321, 42);
		panel.add(txtEditarEvento);
		
		campoDescricao = new JTextField();
		campoDescricao.setBounds(30, 308, 164, 20);
		panel.add(campoDescricao);
		campoDescricao.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setText("Nome");
		txtNome.setToolTipText("");
		txtNome.setBackground(new Color(64, 128, 128));
		txtNome.setBounds(478, 267, 164, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		txtDescrio = new JTextField();
		txtDescrio.setEditable(false);
		txtDescrio.setBackground(new Color(64, 128, 128));
		txtDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescrio.setText("Descrição");
		txtDescrio.setBounds(30, 267, 164, 20);
		panel.add(txtDescrio);
		txtDescrio.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 218, 727, 12);
		panel.add(separator);
		
		String nomeAntigo = nome;
		String descricaoAntiga = descricao;
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar(btnSalvar, id, nomeAntigo, descricaoAntiga);
				frame.atualizarTabela();
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 128));
		btnSalvar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnSalvar.setBounds(211, 343, 89, 23);
		panel.add(btnSalvar);
		
		campoNome = new JTextField();
		campoNome.setBounds(478, 308, 164, 20);
		panel.add(campoNome);
		campoNome.setColumns(10);
		
		JTextArea txtrEventoASer = new JTextArea();
		txtrEventoASer.setText("Evento a ser editado:");
		txtrEventoASer.setForeground(new Color(0, 255, 255));
		txtrEventoASer.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtrEventoASer.setEditable(false);
		txtrEventoASer.setBackground(new Color(0, 64, 0));
		txtrEventoASer.setBounds(10, 96, 321, 42);
		panel.add(txtrEventoASer);
	
		
		campoNome.setText(nome);
		campoDescricao.setText(descricao);
		
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
		campoEventoEdit.setText("Nome: " + nome + "\nDescrição: " + descricao);
		
		EditarEvento essaTela = this;
		
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
        		essaTela.setVisible(false);
        		TelaPrincipalAdm adm = new TelaPrincipalAdm(id);
        		adm.setVisible(true);
        		adm.atualizarTabela();      		
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(366, 343, 81, 23);
		panel.add(btnVoltar);
		
		JTextArea txtrModificar = new JTextArea();
		txtrModificar.setText("Modificar");
		txtrModificar.setForeground(new Color(128, 255, 255));
		txtrModificar.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrModificar.setEditable(false);
		txtrModificar.setBackground(new Color(0, 64, 0));
		txtrModificar.setBounds(252, 227, 164, 42);
		panel.add(txtrModificar);
		
	}
	
	public void salvar(JButton botao, int id, String nomeAntigo, String descricaoAntiga) {
		DaoFactory dao = new DaoFactory();
		//Se forem ambos campos vazios ou ambos iguais aos campos já registrados, não chama o método de editar
		if((campoNome.getText().equals("") && campoDescricao.getText().equals("")) ||
				(campoNome.getText().equals(nomeAntigo) && campoDescricao.getText().equals(descricaoAntiga))) {
			JOptionPane.showMessageDialog(botao, "Não houve tentativa de alteração!");
		}
		else {
			if(!campoNome.getText().equals("")) {
				dao.criarEventoDaoJDBC().editarNome(id, campoNome.getText());
			}
			if(!campoDescricao.getText().equals("")) {
				dao.criarEventoDaoJDBC().editarDescricao(id, campoDescricao.getText());
			}
		}
	}
}
