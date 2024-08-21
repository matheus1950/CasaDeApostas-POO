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

public class ApostasAdm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JButton btnExcluirAposta;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApostasAdm frame = new ApostasAdm();
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
	public ApostasAdm() {
    	setTitle("telaAdm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 779, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 64, 0));
        panel.setBounds(24, 11, 712, 359);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JTextArea txtApostasAdm = new JTextArea();
        txtApostasAdm.setText("Apostas(Adm)");
        txtApostasAdm.setForeground(new Color(128, 255, 255));
        txtApostasAdm.setFont(new Font("Tahoma", Font.BOLD, 31));
        txtApostasAdm.setEditable(false);
        txtApostasAdm.setBackground(new Color(0, 64, 0));
        txtApostasAdm.setBounds(230, 0, 294, 42);
        panel.add(txtApostasAdm);
        
        scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 58, 567, 290);
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
        
        btnExcluirAposta = new JButton("Excluir Aposta");
        btnExcluirAposta.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                String descricao = (String) table.getValueAt(selectedRow, 1);
                excluirAposta(id, descricao);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma aposta para excluir.");
            }
        });
        btnExcluirAposta.setForeground(new Color(0, 0, 128));
        btnExcluirAposta.setBackground(UIManager.getColor("CheckBox.focus"));
        btnExcluirAposta.setBounds(587, 61, 115, 23);
        panel.add(btnExcluirAposta);
        
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
    
    public void excluirAposta(int id, String descricao) {
        DaoFactory dao = new DaoFactory();
        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o aposta de id " + id + " e descricao " + descricao + " ?");
        if (confirmacao == JOptionPane.YES_OPTION) {
            if(dao.criarApostaDaoJDBC().deleteById(id)) { 
            	JOptionPane.showMessageDialog(this, "aposta excluído com sucesso!");
            	removerLinhaTabela(id);
            }
        }
        else {
        	JOptionPane.showMessageDialog(this, "Erro ao excluir o aposta.");
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
    
}
