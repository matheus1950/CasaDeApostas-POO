package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.impl.DaoFactory;
import entidades.Aposta;
import entidades.Bilhete;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TelaDeBilhete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField campoOdd;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeBilhete frame = new TelaDeBilhete(-1); //-1 padrão
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
	public TelaDeBilhete(int idUsuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 664, 527);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(24, 11, 630, 505);
		contentPane_1.add(panel);
		
		JTextArea txtrBilhete = new JTextArea();
		txtrBilhete.setText("Bilhete");
		txtrBilhete.setForeground(new Color(128, 255, 255));
		txtrBilhete.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrBilhete.setEditable(false);
		txtrBilhete.setBackground(new Color(0, 64, 0));
		txtrBilhete.setBounds(162, 5, 121, 42);
		panel.add(txtrBilhete);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.RED);
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(473, 5, 114, 23);
		panel.add(btnLogout);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(Color.BLACK);
		btnVoltar.setBounds(10, 5, 81, 23);
		panel.add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 58, 383, 238);
		panel.add(scrollPane);
		
		//criar uma variável para receber um objeto DefaultTableModel e só depois colocalo como argumento em new JTable!
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Id", "Descricao", "Odd", "Data de criação", "Status"
            }
        ){
		@Override //sobrescrevendo o método de DefaultTable para as células não serem editáveis 
    	    public boolean isCellEditable(int row, int column) {
		        return false;
        	}
    	};
        	
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
		
		campoOdd = new JTextField();
		campoOdd.setEditable(false);
		campoOdd.setBounds(17, 351, 86, 20);
		panel.add(campoOdd);
		campoOdd.setColumns(10);
		
		JTextArea txtrOdd = new JTextArea();
		txtrOdd.setText("ODD ");
		txtrOdd.setForeground(new Color(0, 255, 255));
		txtrOdd.setBackground(new Color(0, 64, 0));
		txtrOdd.setBounds(17, 318, 81, 22);
		panel.add(txtrOdd);
	}
	
	public void preencherTabela(ArrayList<Aposta> apostas) {
        for (Aposta aposta : apostas) {
            Object[] row = {
            	aposta.getId(),
                aposta.getDescricao(),
                aposta.getOdd(),
                aposta.getDataDeCriacao().toString(),
                aposta.getStatus()
            };
            tableModel.addRow(row);
        }     
    }
    
    public void excluirAposta(int id, double odd) {
        DaoFactory dao = new DaoFactory();
        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente se desfazer da aposta de id " + id + " e odd " + odd + " ?");
        if (confirmacao == JOptionPane.YES_OPTION) {
            if(dao.criarApostaDaoJDBC().deleteById(id)) { 
            	JOptionPane.showMessageDialog(this, "aposta excluída com sucesso!");
            	removerLinhaTabela(id);
            }
        }
        else {
        	JOptionPane.showMessageDialog(this, "Erro ao excluir a aposta.");
        }
    }
    
    public void removerLinhaTabela(int id) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if ((int)table.getValueAt(i, 0) == id) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    //atualizar somente as que estão no bilhete pendente relacionado ao id de usuario
	public void atualizarTabela(int idUsuario, JButton botao) {
		DaoFactory dao = new DaoFactory();
        tableModel.setRowCount(0); // Limpa todos os dados da tabela
        
        if(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario) == -1) {
        	JOptionPane.showMessageDialog(botao, "Bilhete vazio, insira alguma aposta!");
        }
        else {
        	//encontrando as apostas pelo id de bilhete
        	ArrayList<Aposta> apostasBilhete = dao.criarApostaDaoJDBC().findApostasByBilheteId(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario));       	
        	preencherTabela(apostasBilhete);
        	preencherCampoOdd(idUsuario, botao);
        }          
	}
	
	public void preencherCampoOdd(int idUsuario, JButton botao) {
		DaoFactory dao = new DaoFactory();   
                
    	//encontrando as apostas pelo id de bilhete
    	ArrayList<Aposta> apostasBilhete = dao.criarApostaDaoJDBC().findApostasByBilheteId(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario));
    	Bilhete bilhete = new Bilhete();
    	for(Aposta aposta : apostasBilhete) {
    		bilhete.addAposta(aposta);
    	}
    	bilhete.setOddTotal(apostasBilhete);//método que multiplica as odds	
    	DecimalFormat df = new DecimalFormat("###.##"); //máscara para reduzir o número de casas decimais para 2
    	campoOdd.setText("" + df.format(bilhete.getOddTotal()));      
	}
}
