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
import entidades.Evento;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class TelaPrincipalAdm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
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
                    TelaPrincipalAdm frame = new TelaPrincipalAdm(-1); //coloquei um id padrão, não sei se tem problema
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
	public TelaPrincipalAdm(int idUsuario) {  	
    	setTitle("telaAdm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 790, 528);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 64, 0));
        panel.setBounds(21, 11, 743, 454);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JTextArea txtTelaPrincipalAdm = new JTextArea();
        txtTelaPrincipalAdm.setText("Tela Principal Adm");
        txtTelaPrincipalAdm.setForeground(new Color(128, 255, 255));
        txtTelaPrincipalAdm.setFont(new Font("Tahoma", Font.BOLD, 31));
        txtTelaPrincipalAdm.setEditable(false);
        txtTelaPrincipalAdm.setBackground(new Color(0, 64, 0));
        txtTelaPrincipalAdm.setBounds(220, 0, 294, 42);
        panel.add(txtTelaPrincipalAdm);
        
        scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 58, 599, 330);
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
        
        //table.setEnabled(false);   - uma opção diferente para desativar a edição das células(mas não são selecionáveis aqui)
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtém o ID do evento a partir da linha selecionada, por exemplo:
                    int id = (int) table.getValueAt(selectedRow, 0);
                    // A partir do nome ou de outra coluna, você pode encontrar o ID do evento
                    System.out.println("Id do evento: " + id);
                }
            }
        });
        
        JButton btnExcluir = new JButton("Excluir Evento");
        btnExcluir.setForeground(new Color(0, 0, 128));
        btnExcluir.setBackground(UIManager.getColor("CheckBox.focus"));
        btnExcluir.setBounds(619, 115, 114, 23);
        btnExcluir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                String nome= (String) table.getValueAt(selectedRow, 1);
                excluirEvento(id, nome);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um evento para excluir.");
            }
        });
        
        panel.add(btnExcluir);
        
        JButton btnVisualizarEvento = new JButton("Visualizar Evento");
        btnVisualizarEvento.setForeground(new Color(0, 0, 128));
        btnVisualizarEvento.setBackground(UIManager.getColor("CheckBox.focus"));
        btnVisualizarEvento.setBounds(619, 178, 114, 23);
        btnVisualizarEvento.addActionListener(e -> {
        		int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    //abrindo a tela de apostas
                    DaoFactory dao = new DaoFactory();
                    ApostasAdm telaApostaAdm = new ApostasAdm();
                    telaApostaAdm.preencherTabela(dao.criarApostaDaoJDBC().ListarApostasPorEventoId(id));
                    telaApostaAdm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um evento para excluir.");
                }	
        });
        
        panel.add(btnVisualizarEvento);
        
        JButton txtEditarEvento = new JButton("Editar Evento");
        txtEditarEvento.addActionListener(e -> {
        		int selectedRow = table.getSelectedRow();
        		if (selectedRow != -1) {
        			//passando nome, descricao e id para a tela de editar evento
	        		EditarEvento editar = new EditarEvento((String)table.getValueAt(selectedRow, 1), (String)table.getValueAt(selectedRow, 2), (int)table.getValueAt(selectedRow, 0), this);
	        		//this aqui é para passar a própria frame no argumento ^
	        		editar.setVisible(true);
        		}
        		else {
                    JOptionPane.showMessageDialog(this, "Selecione um evento para editar.");
                }	
        });
        txtEditarEvento.setForeground(new Color(0, 0, 128));
        txtEditarEvento.setBackground(UIManager.getColor("CheckBox.focus"));
        txtEditarEvento.setBounds(619, 241, 114, 23);
        panel.add(txtEditarEvento);
        
        TelaPrincipalAdm essaTela = this; //capturando essa tela por aqui, porque, no botão, o "this" captura o próprio botão
        
        JButton btnInserirEvento = new JButton("Inserir Evento");
        btnInserirEvento.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroDeEvento cadastro = new CadastroDeEvento(idUsuario, essaTela);
                cadastro.setVisible(true);
        	}
        });
        btnInserirEvento.setForeground(new Color(0, 0, 128));
        btnInserirEvento.setBackground(UIManager.getColor("CheckBox.focus"));
        btnInserirEvento.setBounds(619, 298, 114, 23);
        panel.add(btnInserirEvento);
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
    
    public void atualizarTabela() {
    	DaoFactory dao = new DaoFactory();
        tableModel.setRowCount(0); // Limpa todos os dados da tabela
        
        ArrayList<Evento> todosEventos = dao.criarEventoDaoJDBC().listarTodosEventos();
        preencherTabela(todosEventos);
    }
    
    
    public void excluirEvento(int id, String nome) {
        DaoFactory dao = new DaoFactory();
        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o evento de id " + id + " e nome " + nome + " ?");
        if (confirmacao == JOptionPane.YES_OPTION) {
            if(dao.criarEventoDaoJDBC().deleteById(id)) { //se realmente excluir do banco!
            	JOptionPane.showMessageDialog(this, "Evento excluído com sucesso!");
            	removerLinhaTabela(id);
            	atualizarTabela();
            }
        }
        else {
        	JOptionPane.showMessageDialog(this, "Erro ao excluir o evento.");
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
