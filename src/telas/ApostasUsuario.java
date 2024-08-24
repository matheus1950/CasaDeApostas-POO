package telas;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;
import entidades.Aposta;
import entidades.Bilhete;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class ApostasUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JButton btnLogout;
	private JButton btnVoltar;
	private JButton btnAdicionarAposta;
	private JButton btnBilhete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostasUsuario frame = new ApostasUsuario(-1); // -1 padrão
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
	@SuppressWarnings("serial")
	public ApostasUsuario(int idUsuario) {
		setTitle("telaAdm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(24, 11, 515, 359);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextArea txtApostas = new JTextArea();
		txtApostas.setText("Apostas");
		txtApostas.setForeground(new Color(128, 255, 255));
		txtApostas.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtApostas.setEditable(false);
		txtApostas.setBackground(new Color(0, 64, 0));
		txtApostas.setBounds(162, 5, 160, 42);
		panel.add(txtApostas);

		scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBounds(10, 58, 351, 290);
		panel.add(scrollPane);

		// criar uma variável para receber um objeto DefaultTableModel e só depois
		// colocalo como argumento em new JTable!
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Descricao", "Odd", "Data de criação", "Status" }) {
			@Override // sobrescrevendo o método de DefaultTable para as células não serem editáveis
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		ApostasUsuario essaTela = this;

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnLogout, "Deseja realmente fazer logout?");
				if (option == JOptionPane.YES_OPTION) {
					essaTela.setVisible(false);
					new Login().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(btnLogout, "Logout cancelado!");
				}
			}
		});
		btnLogout.setForeground(Color.RED);
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(396, 18, 114, 23);
		panel.add(btnLogout);

		btnVoltar = new JButton("Voltar");
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
		btnVoltar.setBounds(396, 325, 81, 23);
		panel.add(btnVoltar);

		btnBilhete = new JButton("Bilhete");
		btnBilhete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				essaTela.setVisible(false);
				TelaDeBilhete bilhete = new TelaDeBilhete(idUsuario);
				bilhete.setVisible(true);
				bilhete.atualizarTabela(idUsuario, btnAdicionarAposta);
			}
		});
		btnBilhete.setBackground(UIManager.getColor("CheckBox.focus"));
		btnBilhete.setForeground(new Color(0, 0, 128));
		btnBilhete.setBounds(371, 147, 139, 23);
		panel.add(btnBilhete);

		// table.setEnabled(false); - uma opção diferente para desativar a edição das
		// células(mas não são selecionáveis aqui)

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					// Obtém o ID do aposta a partir da linha selecionada
					int id = (int) table.getValueAt(selectedRow, 0);
					System.out.println("Id do aposta: " + id);
				}
			}
		});

		btnAdicionarAposta = new JButton("Adicionar Aposta");
		btnAdicionarAposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					criarBilhete((int) table.getValueAt(selectedRow, 0), idUsuario, btnAdicionarAposta);
				} else {
					JOptionPane.showMessageDialog(btnAdicionarAposta, "Selecione uma opção para apostar!");
				}
			}
		});
		btnAdicionarAposta.setBackground(UIManager.getColor("CheckBox.focus"));
		btnAdicionarAposta.setForeground(new Color(0, 0, 128));
		btnAdicionarAposta.setBounds(371, 87, 139, 23);
		panel.add(btnAdicionarAposta);
	}

	public void preencherTabela(ArrayList<Aposta> apostas) {
		for (Aposta aposta : apostas) {
			Object[] row = { aposta.getId(), aposta.getDescricao(), aposta.getOdd(),
					aposta.getDataDeCriacao().toString(), aposta.getStatus() };
			tableModel.addRow(row);
		}
	}

	public void criarBilhete(int idAposta, int idUsuario, JButton botao) {
		DaoFactory dao = new DaoFactory();
		// -1 no método usuarioTemBilhetePendente simboliza o retorno nulo(não
		// encontrado!), sendo nulo, criamos o bilhete e inserimos a aposta nele!
		if (dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario) == -1) {
			// criando bilhete e inserindo a aposta nele!
			Bilhete bilhete = new Bilhete();
			bilhete.setIdDeUsuario(idUsuario);
			dao.criarBilheteDaoJDBC().inserirBilhete(bilhete);
			int idBilhete = dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario); // reutilizando o método, já
																							// que agora temos o bilhete
																							// inserido, para pegar o id
																							// dele
			dao.criarBilheteDaoJDBC().inserirApostaNoBilheteById(idBilhete, idAposta);
			JOptionPane.showMessageDialog(botao, "Bilhete criado com sucesso! Aposta adicionada com sucesso!");
		} else { // inserir no bilhete pendente encontrado
					// a próxima linha utiliza um método para achar o bilhete pendente por meio do
					// id que pegamos no método usuarioTemBilhetePendente(devolve o id do bilhete
					// pendente)
			Bilhete bilhete = dao.criarBilheteDaoJDBC()
					.findBilheteById(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario));
			bilhete.addAposta(dao.criarApostaDaoJDBC().findApostaById(idAposta)); // adicionar a aposta pelo Id que
																					// pegamos neste método
			dao.criarBilheteDaoJDBC().inserirApostaNoBilheteById(bilhete.getId(), idAposta);
			JOptionPane.showMessageDialog(botao, "Aposta adicionada ao bilhete!"); // falta uma verificação aqui pra
																					// evitar duplicações de aposta no
																					// bilhete!
		}
	}
}
