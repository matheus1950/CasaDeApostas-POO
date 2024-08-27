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
import javax.swing.ScrollPaneConstants;

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
	private JButton btnLerDescricao;

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
		ApostasUsuario essaTela = this;
		
		setTitle("telaAdm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 826);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(24, 11, 1018, 764);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextArea txtApostas = new JTextArea();
		txtApostas.setText("Apostas");
		txtApostas.setForeground(new Color(128, 255, 255));
		txtApostas.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtApostas.setEditable(false);
		txtApostas.setBackground(new Color(0, 64, 0));
		txtApostas.setBounds(327, 5, 160, 42);
		panel.add(txtApostas);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBounds(10, 58, 846, 682);
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
		btnLogout.setBackground(UIManager.getColor("CheckBox.focus"));
		btnLogout.setBounds(892, 20, 114, 23);
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
		btnVoltar.setBounds(892, 717, 81, 23);
		panel.add(btnVoltar);

		btnBilhete = new JButton("Bilhete Pendente");
		btnBilhete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DaoFactory dao = new DaoFactory();
				//verificando se o usuário tem bilhete com "efetuado" pendente e, se passar, verificando se o mesmo bilhete tem apostas associadas
				if(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario) != -1) {
					if(dao.criarBilheteDaoJDBC().bilheteNaoTemApostas(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario)) == false) {
						TelaDeBilhete bilhete = new TelaDeBilhete(idUsuario);					
						essaTela.setVisible(false);					
						bilhete.setVisible(true);					
						bilhete.atualizarTabela(idUsuario, btnBilhete);	
					}
					else {
						JOptionPane.showMessageDialog(btnBilhete, "Usuário não tem bilhete pendente! adicione apostas!");
					}
				}
				else {
					JOptionPane.showMessageDialog(btnBilhete, "Usuário não tem bilhete pendente! adicione apostas!");
				}
			}
		});
		btnBilhete.setBackground(UIManager.getColor("CheckBox.focus"));
		btnBilhete.setForeground(new Color(0, 0, 128));
		btnBilhete.setBounds(867, 258, 139, 23);
		panel.add(btnBilhete);

		// table.setEnabled(false); - uma opção diferente para desativar a edição das células(mas não são selecionáveis nesse caso)

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
		btnAdicionarAposta.setBounds(868, 152, 139, 23);
		panel.add(btnAdicionarAposta);
		
		btnLerDescricao = new JButton("Ler Descrição");
		btnLerDescricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
	        		JOptionPane.showInternalMessageDialog(null, "Descrição: " + table.getValueAt(table.getSelectedRow(), 1));
	        	}
        		else {
        			JOptionPane.showMessageDialog(null, "Selecione uma aposta para visualizar a descrição!");
        		}
			}
		});
		btnLerDescricao.setForeground(new Color(0, 0, 128));
		btnLerDescricao.setBackground(UIManager.getColor("Button.focus"));
		btnLerDescricao.setBounds(868, 356, 139, 23);
		panel.add(btnLerDescricao);
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
			try{
				dao.criarBilheteDaoJDBC().inserirApostaNoBilheteById(idBilhete, idAposta);
				JOptionPane.showMessageDialog(botao, "Bilhete criado com sucesso! Aposta adicionada com sucesso!");
			}
			catch (SQLException e) {
			    e.printStackTrace();
			    JOptionPane.showMessageDialog(botao, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
				
		} else { // inserir no bilhete pendente encontrado
					// a próxima linha utiliza um método para achar o bilhete pendente por meio do
					// id que pegamos no método usuarioTemBilhetePendente(devolve o id do bilhete
					// pendente)
			Bilhete bilhete = dao.criarBilheteDaoJDBC().findBilheteById(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario));
			bilhete.addAposta(dao.criarApostaDaoJDBC().findApostaById(idAposta)); // adicionar a aposta pelo Id que
																					// pegamos neste método
			
			//dao.criarBilheteDaoJDBC().inserirApostaNoBilheteById(bilhete.getId(), idAposta);
			//JOptionPane.showMessageDialog(botao, "Aposta adicionada ao bilhete!");	
			
			try {		
			    dao.criarBilheteDaoJDBC().inserirApostaNoBilheteById(bilhete.getId(), idAposta);
			    JOptionPane.showMessageDialog(botao, "Aposta adicionada ao bilhete com sucesso!");
			}catch (SQLException e) {
			    e.printStackTrace();
			    JOptionPane.showMessageDialog(botao, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
