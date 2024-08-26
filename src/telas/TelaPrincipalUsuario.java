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
import entidades.Usuario;

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
    private JButton btnLogout;
    private JButton btnBilhete;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipalUsuario frame = new TelaPrincipalUsuario(-1); //-1 padrão
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
	public TelaPrincipalUsuario(int idUsuario) {
    	TelaPrincipalUsuario essaTela = this;
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 514);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 64, 0));
        panel.setBounds(10, 11, 723, 453);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JTextArea txtTelaPrincipal = new JTextArea();
        txtTelaPrincipal.setText("Tela Principal (User)");
        txtTelaPrincipal.setForeground(new Color(128, 255, 255));
        txtTelaPrincipal.setFont(new Font("Tahoma", Font.BOLD, 31));
        txtTelaPrincipal.setEditable(false);
        txtTelaPrincipal.setBackground(new Color(0, 64, 0));
        txtTelaPrincipal.setBounds(185, 0, 321, 42);
        panel.add(txtTelaPrincipal);
        
        scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setBounds(10, 109, 579, 339);
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
                ApostasUsuario telaApostaUser = new ApostasUsuario(idUsuario);
                telaApostaUser.preencherTabela(dao.criarApostaDaoJDBC().ListarApostasPorEventoId(id));
                telaApostaUser.setVisible(true);
                essaTela.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um evento para visualizar.");
            }	
        });
        
        
        btnVisualizarEvento.setBackground(UIManager.getColor("CheckBox.focus"));
        btnVisualizarEvento.setForeground(new Color(0, 0, 128));
        btnVisualizarEvento.setBounds(599, 112, 114, 23);
        panel.add(btnVisualizarEvento);
        
        
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
        btnLogout.setBackground(new Color(0, 0, 0));
        btnLogout.setBounds(599, 18, 114, 23);
        panel.add(btnLogout);
        
        JButton btnMinhaConta = new JButton("Minha conta");
        btnMinhaConta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		essaTela.setVisible(false);
        		MinhaConta Conta = new MinhaConta(idUsuario);
        		Conta.setVisible(true);		
        	}
        });
        btnMinhaConta.setBackground(UIManager.getColor("CheckBox.focus"));
        btnMinhaConta.setForeground(new Color(0, 0, 128));
        btnMinhaConta.setBounds(10, 18, 114, 23);
        panel.add(btnMinhaConta);
        
        btnBilhete = new JButton("Bilhete");
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
        btnBilhete.setForeground(new Color(0, 0, 128));
        btnBilhete.setBackground(UIManager.getColor("CheckBox.focus"));
        btnBilhete.setBounds(599, 169, 114, 23);
        panel.add(btnBilhete);
        
        JButton btnHistoricoDeAposta = new JButton("Meu histórico");
        btnHistoricoDeAposta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			DaoFactory dao = new DaoFactory();							
				HistoricoDeApostas historico = new HistoricoDeApostas(idUsuario);					
				essaTela.setVisible(false);					
				historico.setVisible(true);					
				historico.preencherTabela(dao.criarBilheteDaoJDBC().todosBilhetesPorUsuarioId(idUsuario));//passando array de bilhetes							
        	}
        });
        btnHistoricoDeAposta.setForeground(new Color(0, 0, 128));
        btnHistoricoDeAposta.setBackground(UIManager.getColor("CheckBox.focus"));
        btnHistoricoDeAposta.setBounds(10, 61, 114, 23);
        panel.add(btnHistoricoDeAposta);
        
        JTextArea campoSaldo = new JTextArea();
        campoSaldo.setForeground(new Color(0, 255, 255));
        campoSaldo.setBackground(new Color(0, 64, 0));
        campoSaldo.setBounds(195, 41, 124, 22);
        panel.add(campoSaldo);
        campoSaldo.setText("Saldo = R$" + saldoUsuario(idUsuario));
        
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		essaTela.setVisible(false);
        		new TelaDeDeposito(idUsuario).setVisible(true);;
        	}
        });
        btnDepositar.setForeground(new Color(0, 0, 128));
        btnDepositar.setBackground(UIManager.getColor("CheckBox.focus"));
        btnDepositar.setBounds(349, 42, 79, 23);
        panel.add(btnDepositar);;
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
    
    public void atualizarTabela() {
    	DaoFactory dao = new DaoFactory();
        tableModel.setRowCount(0); // Limpa todos os dados da tabela
        
        ArrayList<Evento> todosEventos = dao.criarEventoDaoJDBC().listarTodosEventos();
        preencherTabela(todosEventos);
    }
    
    public double saldoUsuario(int idUsuario) {
    	DaoFactory dao = new DaoFactory();
    	return dao.criarUsuarioDaoJDBC().findUsuarioById(idUsuario).getCarteira();
    }
}
