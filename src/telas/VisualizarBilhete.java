package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.impl.DaoFactory;
import entidades.Aposta;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizarBilhete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarBilhete frame = new VisualizarBilhete(-1); //-1 padrão
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
	public VisualizarBilhete(int idUsuario) {
		VisualizarBilhete essaTela = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 802);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 1038, 751);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(24, 11, 1002, 728);
		contentPane_1.add(panel);
		
		JTextArea txtrApostasNoBilhete = new JTextArea();
		txtrApostasNoBilhete.setText("Apostas do Bilhete");
		txtrApostasNoBilhete.setForeground(new Color(128, 255, 255));
		txtrApostasNoBilhete.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrApostasNoBilhete.setEditable(false);
		txtrApostasNoBilhete.setBackground(new Color(0, 64, 0));
		txtrApostasNoBilhete.setBounds(261, 4, 304, 42);
		panel.add(txtrApostasNoBilhete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(10, 58, 854, 658);
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
		btnLogout.setBackground(UIManager.getColor("CheckBox.focus"));
		btnLogout.setBounds(876, 20, 114, 23);
		panel.add(btnLogout);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DaoFactory dao = new DaoFactory();
				essaTela.setVisible(false);
				HistoricoDeApostas historico = new HistoricoDeApostas(idUsuario);
				historico.setVisible(true);
				historico.preencherTabela(dao.criarBilheteDaoJDBC().todosBilhetesPorUsuarioId(idUsuario));
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(909, 653, 81, 23);
		panel.add(btnVoltar);
		
		JButton btnLerDescricao = new JButton("Ler Descrição");
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
		btnLerDescricao.setBounds(876, 115, 114, 23);
		panel.add(btnLerDescricao);
	}
	
	public void preencherTabela(ArrayList<Aposta> apostas) {
		for (Aposta aposta : apostas) {
			Object[] row = { aposta.getId(), aposta.getDescricao(), aposta.getOdd(),
					aposta.getDataDeCriacao().toString(), aposta.getStatus() };
			tableModel.addRow(row);
		}
	}
}
