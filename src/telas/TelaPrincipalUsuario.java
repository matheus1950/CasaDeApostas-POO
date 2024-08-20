package telas;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
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

public class TelaPrincipalUsuario extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIncompleta;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

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
        
        JTextArea txtTelaPrincipal = new JTextArea();
        txtTelaPrincipal.setText("Tela Principal");
        txtTelaPrincipal.setForeground(new Color(128, 255, 255));
        txtTelaPrincipal.setFont(new Font("Tahoma", Font.BOLD, 31));
        txtTelaPrincipal.setEditable(false);
        txtTelaPrincipal.setBackground(new Color(0, 64, 0));
        txtTelaPrincipal.setBounds(147, 0, 214, 42);
        panel.add(txtTelaPrincipal);
        
        txtIncompleta = new JTextField();
        txtIncompleta.setEditable(false);
        txtIncompleta.setHorizontalAlignment(SwingConstants.CENTER);
        txtIncompleta.setText("INCOMPLETA");
        txtIncompleta.setBounds(391, 51, 114, 122);
        panel.add(txtIncompleta);
        txtIncompleta.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 58, 351, 290);
        panel.add(scrollPane);
        
        //criar uma variável para receber um objeto DefaultTableModel e só depois colocalo como argumento em new JTable!
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Nome", "Descrição", "Data de criação"
            }
        ){@Override //sobrescrevendo o método de DefaultTable para as células não serem editáveis 
    	    public boolean isCellEditable(int row, int column) {
		        return false;
        	}
    	};
        

        	
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        //table.setEnabled(false);   - uma opção diferente para desativar a edição das células(mas não são selecionáveis aqui)
    }
    
    public void preencherTabela(ArrayList<Evento> eventos) {
        for (Evento evento : eventos) {
            Object[] row = {
                evento.getNome(),
                evento.getDescricao(),
                evento.getDataDeCriacao().toString()
            };
            tableModel.addRow(row);
        }
    }
}
