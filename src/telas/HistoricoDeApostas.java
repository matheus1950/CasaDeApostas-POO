package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Bilhete;
import entidades.Evento;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;

public class HistoricoDeApostas extends JFrame {

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
					HistoricoDeApostas frame = new HistoricoDeApostas(-1); //-1 padrão
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
	public HistoricoDeApostas(int idUsuario) {
		HistoricoDeApostas essaTela = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 762, 540);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(24, 11, 728, 518);
		contentPane_1.add(panel);
		
		JTextArea txtrHistrico = new JTextArea();
		txtrHistrico.setText("Histórico");
		txtrHistrico.setForeground(new Color(128, 255, 255));
		txtrHistrico.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrHistrico.setEditable(false);
		txtrHistrico.setBackground(new Color(0, 64, 0));
		txtrHistrico.setBounds(132, 0, 160, 42);
		panel.add(txtrHistrico);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(10, 58, 559, 449);
		panel.add(scrollPane);
		
		// criar uma variável para receber um objeto DefaultTableModel e só depois
		// colocalo como argumento em new JTable!
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Data de criação", "Odd", "Status", "Valor", "Resultado", "Retorno" }) {
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
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(604, 18, 114, 23);
		panel.add(btnLogout);
		
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
		btnVoltar.setBounds(637, 484, 81, 23);
		panel.add(btnVoltar);
		
		JButton btnBilhete = new JButton("Bilhete");
		btnBilhete.setForeground(new Color(0, 0, 128));
		btnBilhete.setBackground(UIManager.getColor("CheckBox.focus"));
		btnBilhete.setBounds(579, 191, 139, 23);
		panel.add(btnBilhete);
		
		JButton btnVisualizar = new JButton("Visualizar bilhete");
		btnVisualizar.setForeground(new Color(0, 0, 128));
		btnVisualizar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVisualizar.setBounds(579, 114, 139, 23);
		panel.add(btnVisualizar);
	}
	
	public void preencherTabela(ArrayList<Bilhete> bilhetes) { 
        for (Bilhete bilhete : bilhetes) { 
            Object[] row = {
            	bilhete.getId(),
                bilhete.getDataDeCriacao(),
                bilhete.getOddTotal(),
                bilhete.getStatus(),
                bilhete.getValor(),
                bilhete.getResultado(),
                bilhete.getRetorno()
            };
            tableModel.addRow(row);
        }
    }
}
