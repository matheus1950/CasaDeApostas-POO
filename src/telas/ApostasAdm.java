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
import javax.swing.ScrollPaneConstants;

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
                    ApostasAdm frame = new ApostasAdm(-1, -1); //-1 padrão, não se deve manter
                    frame.setVisible(true);                                  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param idUsuario 
     */
    @SuppressWarnings("serial")
	public ApostasAdm(int idEvento, int idUsuario) {
    	ApostasAdm essaTela = this; //pegando essa tela para passar para a tela de cadastro de aposta
    	
    	setTitle("telaAdm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1103, 746);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 64, 0));
        panel.setBounds(10, 11, 1065, 684);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JTextArea txtApostasAdm = new JTextArea();
        txtApostasAdm.setText("Apostas");
        txtApostasAdm.setForeground(new Color(128, 255, 255));
        txtApostasAdm.setFont(new Font("Tahoma", Font.BOLD, 31));
        txtApostasAdm.setEditable(false);
        txtApostasAdm.setBackground(new Color(0, 64, 0));
        txtApostasAdm.setBounds(326, 4, 194, 42);
        panel.add(txtApostasAdm);
        
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 58, 878, 614);
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
        btnExcluirAposta.setBounds(900, 104, 153, 23);
        panel.add(btnExcluirAposta);
        
        JButton btnEditarAposta = new JButton("Editar Aposta");
        btnEditarAposta.addActionListener(e -> {
        		int selectedRow = table.getSelectedRow();
        		if (selectedRow != -1) {
        			//passando nome, descricao e id para a tela de editar evento
	        		EditarAposta editar = new EditarAposta((double)table.getValueAt(selectedRow, 2), (String)table.getValueAt(selectedRow, 1), (int)table.getValueAt(selectedRow, 0), this, idEvento, idUsuario);
	        		//this aqui é para passar a própria frame no argumento ^
	        		editar.setVisible(true);
	        		essaTela.setVisible(false);
        		}
        		else {
                    JOptionPane.showMessageDialog(this, "Selecione uma aposta para editar.");
                }	
        });
        btnEditarAposta.setForeground(new Color(0, 0, 128));
        btnEditarAposta.setBackground(UIManager.getColor("CheckBox.focus"));
        btnEditarAposta.setBounds(900, 165, 153, 23);
        panel.add(btnEditarAposta);
       
        
        JButton btnCadastrarAposta = new JButton("Cadastrar Aposta");
        btnCadastrarAposta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroDeAposta cadastro = new CadastroDeAposta(idEvento, idUsuario, essaTela);
                cadastro.setVisible(true);
                essaTela.setVisible(false);
        	}
        });
        btnCadastrarAposta.setForeground(new Color(0, 0, 128));
        btnCadastrarAposta.setBackground(UIManager.getColor("CheckBox.focus"));
        btnCadastrarAposta.setBounds(900, 229, 153, 23);
        panel.add(btnCadastrarAposta);
        
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
        btnLogout.setBounds(939, 15, 114, 23);
        panel.add(btnLogout);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		TelaPrincipalAdm adm =  new TelaPrincipalAdm(idUsuario);
	        		adm.setVisible(true);
	        		adm.atualizarTabela();	        		
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
        	} 	
        });
        btnVoltar.setForeground(new Color(0, 0, 128));
        btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
        btnVoltar.setBounds(939, 602, 81, 23);
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
        btnLerDescricao.setBounds(902, 306, 151, 23);
        panel.add(btnLerDescricao);
        
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

    //atualizar somente as que são relacionadas ao id de evento
	public void atualizarTabela(int idEvento) {
		DaoFactory dao = new DaoFactory();
        tableModel.setRowCount(0); // Limpa todos os dados da tabela
        
        ArrayList<Aposta> todasApostas = dao.criarApostaDaoJDBC().ListarApostasPorEventoId(idEvento);
        preencherTabela(todasApostas);
	}
}
