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
import java.awt.event.ActionListener;
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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApostasUsuario frame = new ApostasUsuario();
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
	public ApostasUsuario() {
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
        txtApostas.setBounds(92, 0, 294, 42);
        panel.add(txtApostas);
        
        scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 58, 351, 290);
        panel.add(scrollPane);
        
        //criar uma variável para receber um objeto DefaultTableModel e só depois colocalo como argumento em new JTable!
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Id", "Descricao", "Odd", "Data de criação", "Status"
            }
        ){@Override //sobrescrevendo o método de DefaultTable para as células não serem editáveis 
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
        btnLogout.setBounds(396, 18, 114, 23);
        panel.add(btnLogout);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		new TelaPrincipalUsuario().setVisible(true);
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
        	}
        });
        btnVoltar.setForeground(new Color(0, 0, 128));
        btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
        btnVoltar.setBounds(396, 325, 81, 23);
        panel.add(btnVoltar);
        
        //table.setEnabled(false);   - uma opção diferente para desativar a edição das células(mas não são selecionáveis aqui)
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtém o ID do aposta a partir da linha selecionada, por exemplo:
                    int id = (int) table.getValueAt(selectedRow, 0);
                    // A partir do nome ou de outra coluna, você pode encontrar o ID do aposta
                    System.out.println("Id do aposta: " + id);
                }
            }
        });
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
}
