package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.BilheteDaoJDBC;
import dao.impl.DaoFactory;
import dao.impl.EventoDaoJDBC;
import dao.impl.PessoaDaoJDBC;
import entidades.Aposta;
import entidades.Bilhete;
import entidades.Evento;
import entidades.Usuario;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class EncerrarEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoVencedor;
	private JTextField txtApostaVencedora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncerrarEvento frame = new EncerrarEvento(-1, -1); //padrão
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param object 
	 * @param idUsuario 
	 */
	public EncerrarEvento(int idUsuario, int idEvento) {
		DaoFactory dao = new DaoFactory();
		EncerrarEvento essaTela = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 884, 481);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 24, 864, 446);
		contentPane_1.add(panel);
		
		JTextArea txtrEncerrarEvento = new JTextArea();
		txtrEncerrarEvento.setText("Encerrar Evento");
		txtrEncerrarEvento.setForeground(new Color(128, 255, 255));
		txtrEncerrarEvento.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrEncerrarEvento.setEditable(false);
		txtrEncerrarEvento.setBackground(new Color(0, 64, 0));
		txtrEncerrarEvento.setBounds(211, 0, 267, 42);
		panel.add(txtrEncerrarEvento);
		
		campoVencedor = new JTextField();
		campoVencedor.setColumns(10);
		campoVencedor.setBounds(246, 335, 164, 20);
		panel.add(campoVencedor);
		
		txtApostaVencedora = new JTextField();
		txtApostaVencedora.setText("id da aposta vencedora");
		txtApostaVencedora.setHorizontalAlignment(SwingConstants.CENTER);
		txtApostaVencedora.setEditable(false);
		txtApostaVencedora.setColumns(10);
		txtApostaVencedora.setBackground(new Color(64, 128, 128));
		txtApostaVencedora.setBounds(246, 294, 164, 20);
		panel.add(txtApostaVencedora);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 218, 854, 12);
		panel.add(separator);
		
		JButton btnEncerrar = new JButton("Encerrar");
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarStatusDoEvento(idEvento, dao);
				alterarResultadoDoEvento(idEvento,Integer.parseInt(campoVencedor.getText()), dao);
				try {				
					encerrarBilhetesComTodosEventosEncerrados(dao);
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				JOptionPane.showInternalMessageDialog(null, "Evento encerrado com sucesso!");
			}
		});
		btnEncerrar.setForeground(new Color(0, 0, 128));
		btnEncerrar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnEncerrar.setBounds(211, 391, 89, 23);
		panel.add(btnEncerrar);
		
		JTextArea txtrEventoASer = new JTextArea();
		txtrEventoASer.setText("Evento a ser encerrado");
		txtrEventoASer.setForeground(Color.CYAN);
		txtrEventoASer.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtrEventoASer.setEditable(false);
		txtrEventoASer.setBackground(new Color(0, 64, 0));
		txtrEventoASer.setBounds(0, 65, 321, 42);
		panel.add(txtrEventoASer);
		
		JTextArea campoEventoEdit = new JTextArea();
		campoEventoEdit.setRows(2);
		campoEventoEdit.setForeground(Color.RED);
		campoEventoEdit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		campoEventoEdit.setEditable(false);
		campoEventoEdit.setBackground(new Color(0, 64, 0));
		campoEventoEdit.setBounds(10, 103, 844, 104);
		panel.add(campoEventoEdit);
		campoEventoEdit.setText("Descrição: " + findEventoById(idEvento).getDescricao() + "\nPrimeira escolha(id-" + 
		findEventoById(idEvento).getAssociadas().get(0).getId() + ") : "  
		+  findEventoById(idEvento).getAssociadas().get(0).getDescricao() +			
				"\nSegunda escolha(id-" + findEventoById(idEvento).getAssociadas().get(1).getId() + ") : "  
				+  findEventoById(idEvento).getAssociadas().get(1).getDescricao());
		
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
		btnLogout.setBounds(613, 18, 114, 23);
		panel.add(btnLogout);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				essaTela.setVisible(false);
				TelaPrincipalAdm adm = new TelaPrincipalAdm(idUsuario);
				adm.setVisible(true);
				adm.atualizarTabela();
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(397, 391, 81, 23);
		panel.add(btnVoltar);
		
		JTextArea txtrGanhador = new JTextArea();
		txtrGanhador.setText("Defina o vencedor:");
		txtrGanhador.setForeground(new Color(128, 255, 255));
		txtrGanhador.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrGanhador.setEditable(false);
		txtrGanhador.setBackground(new Color(0, 64, 0));
		txtrGanhador.setBounds(184, 228, 294, 42);
		panel.add(txtrGanhador);
	}
	
	public Evento findEventoById(int idEvento) {
		DaoFactory dao = new DaoFactory();
		
		return dao.criarEventoDaoJDBC().findEventoById(idEvento);
	}
	
	public void alterarStatusDoEvento(int idEvento, DaoFactory dao) {
		dao.criarEventoDaoJDBC().editarStatus(idEvento, "encerrado");
	}
	
	public void alterarResultadoDoEvento(int idEvento, int idApostaVencedora, DaoFactory dao) {
		String vencedor = dao.criarApostaDaoJDBC().findApostaById(idApostaVencedora).getDescricao();
		dao.criarEventoDaoJDBC().editarResultado(idEvento, vencedor);
	}
	
	public void encerrarBilhetesComTodosEventosEncerrados(DaoFactory dao) throws SQLException {	   
	    PessoaDaoJDBC pessoaDao = dao.criarPessoaDaoJDBC();
	    BilheteDaoJDBC bilheteDao = dao.criarBilheteDaoJDBC();
	    EventoDaoJDBC eventoDao = dao.criarEventoDaoJDBC();

	    
	    for (Usuario usuario : pessoaDao.todosUsuarios()) {
	        
	        ArrayList<Bilhete> bilhetes = bilheteDao.todosBilhetesPorUsuarioId(usuario.getId());

	        if (bilhetes != null) {
	            for (Bilhete bilhete : bilhetes) {
	                if (bilhete.isEfetuado() && bilhete.getStatus().equals("pendente")) { 
	                    boolean todosEventosEncerrados = true;
	                    boolean ganhou = true;

	                    
	                    ArrayList<Aposta> apostas = bilheteDao.obterApostasPorBilheteId(bilhete.getId());
	                    if (apostas != null) {
	                        for (Aposta aposta : apostas) {
	                                                    	
	                            Evento evento = eventoDao.findEventoById(aposta.getIdDeEvento());
	                            
	                            if (evento == null || evento.getStatus() == null) {	                                
	                                System.out.println("Evento ou status é nulo para o evento com ID: " + evento.getId());
	                                todosEventosEncerrados = false;
	                                break;
	                            }
	                            
	                            if (!evento.getStatus().equalsIgnoreCase("encerrado")) {
	                                todosEventosEncerrados = false;
	                                break;
	                            }
	                            
	                            if (!aposta.getDescricao().equalsIgnoreCase(evento.getResultado())) {
	                                ganhou = false;
	                            }
	                        }
	                    }
	               
	                    if (todosEventosEncerrados) {
	                        if (ganhou) {
	                            bilheteDao.editarStatusBilhete(bilhete.getId(), "venceu");
	                            pessoaDao.editarCarteira(usuario.getId(), bilhete.getRetorno());
	                        } else {
	                            bilheteDao.editarStatusBilhete(bilhete.getId(), "perdeu");
	                        }
	                    }

	                } else { 
	                    ArrayList<Aposta> apostas = bilheteDao.obterApostasPorBilheteId(bilhete.getId());
	                    if (apostas != null) {
	                        for (Aposta aposta : apostas) {
	                            Evento evento = eventoDao.findEventoById(aposta.getIdDeEvento());

	                            if (evento != null && evento.getStatus() != null && evento.getStatus().equalsIgnoreCase("encerrado")) {	                                
	                                bilheteDao.removerDoBilhete(bilhete.getId(), aposta.getId());
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	}




	
}
