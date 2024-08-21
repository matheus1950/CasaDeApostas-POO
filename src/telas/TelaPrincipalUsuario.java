package telas;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
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
import entidades.Evento;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalUsuario extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JButton btnVisualizarEvento;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipalUsuario frame = new TelaPrincipalUsuario();
                    frame.setVisible(true);
                    
                    //preencher a tabela com todos eventos
                    DaoFactory dao = new DaoFactory();
                    ArrayList<Evento> todosEventos = dao.criarEventoDaoJDBC().listarTodosEventos();
                    frame.preencherTabela(todosEventos);
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
	public TelaPrincipalUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 797, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 64, 0));
        panel.setBounds(24, 11, 723, 359);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JTextArea txtTelaPrincipal = new JTextArea();
        txtTelaPrincipal.setText("Tela Principal (User)");
        txtTelaPrincipal.setForeground(new Color(128, 255, 255));
        txtTelaPrincipal.setFont(new Font("Tahoma", Font.BOLD, 31));
        txtTelaPrincipal.setEditable(false);
        txtTelaPrincipal.setBackground(new Color(0, 64, 0));
        txtTelaPrincipal.setBounds(208, 0, 321, 42);
        panel.add(txtTelaPrincipal);
        
        scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 58, 579, 290);
        panel.add(scrollPane);
        
        //criar uma variável para receber um objeto DefaultTableModel e só depois colocalo como argumento em new JTable!
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Id", "Nome", "Descrição", "Data de criação"
            }
        ){@Override //sobrescrevendo o método de DefaultTable para as células não serem editáveis 
    	    public boolean isCellEditable(int row, int column) {
		        return false;
        	}
    	};
        

        	
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        btnVisualizarEvento = new JButton("Visualizar Evento");
        btnVisualizarEvento.addActionListener(e -> {
    		int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                //abrindo a tela de apostas
                DaoFactory dao = new DaoFactory();
                ApostasUsuario telaApostaUser = new ApostasUsuario();
                telaApostaUser.preencherTabela(dao.criarApostaDaoJDBC().ListarApostasPorEventoId(id));
                telaApostaUser.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um evento para excluir.");
            }	
        });
        
        
        btnVisualizarEvento.setBackground(UIManager.getColor("CheckBox.focus"));
        btnVisualizarEvento.setForeground(new Color(0, 0, 128));
        btnVisualizarEvento.setBounds(599, 112, 114, 23);
        panel.add(btnVisualizarEvento);
        //table.setEnabled(false);   - uma opção diferente para desativar a edição das células(mas não são selecionáveis aqui)
    }
    
    public void preencherTabela(ArrayList<Evento> eventos) { 
        for (Evento evento : eventos) {
            Object[] row = {
            	evento.getId(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getDataDeCriacao().toString()
            };
            tableModel.addRow(row);
        }
    }
}
