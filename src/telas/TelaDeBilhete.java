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
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class TelaDeBilhete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField campoOdd;
	private DefaultTableModel tableModel;
	private JTextField campoValor;
	private JTextField campoRetorno;

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
		TelaDeBilhete essaTela = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 655, 409);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 11, 630, 369);
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
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar para a tela principal?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		TelaPrincipalUsuario user =  new TelaPrincipalUsuario(idUsuario);
	        		user.setVisible(true);
	        		user.atualizarTabela();	        		
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
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
		campoOdd.setBounds(162, 330, 86, 20);
		panel.add(campoOdd);
		campoOdd.setColumns(10);
		
		JTextArea txtrOdd = new JTextArea();
		txtrOdd.setText("ODD ");
		txtrOdd.setForeground(new Color(0, 255, 255));
		txtrOdd.setBackground(new Color(0, 64, 0));
		txtrOdd.setBounds(172, 297, 42, 22);
		panel.add(txtrOdd);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(410, 58, 210, 2);
		panel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(411, 58, 1, 300);
		panel.add(separator_2);
		
		campoValor = new JTextField();
		campoValor.setBounds(422, 100, 119, 20);
		panel.add(campoValor);
		campoValor.setColumns(10);
		
		JTextArea txtrValorApostado = new JTextArea();
		txtrValorApostado.setText("Valor Apostado");
		txtrValorApostado.setForeground(Color.CYAN);
		txtrValorApostado.setBackground(new Color(0, 64, 0));
		txtrValorApostado.setBounds(420, 67, 121, 22);
		panel.add(txtrValorApostado);
		
		JTextArea txtrPossvelRetorno = new JTextArea();
		txtrPossvelRetorno.setText("Possível retorno");
		txtrPossvelRetorno.setForeground(Color.CYAN);
		txtrPossvelRetorno.setBackground(new Color(0, 64, 0));
		txtrPossvelRetorno.setBounds(420, 152, 151, 22);
		panel.add(txtrPossvelRetorno);
		
		campoRetorno = new JTextField();
		campoRetorno.setColumns(10);
		campoRetorno.setBounds(422, 180, 119, 20);
		panel.add(campoRetorno);
		
		
		JButton btnApostar = new JButton("Apostar");
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!campoValor.getText().equals("")) {
					if(!campoValor.getText().contains(",")) {
						Double retorno = Double.parseDouble(campoOdd.getText()) * Double.parseDouble(campoValor.getText());				
						campoRetorno.setText("" + retorno);
						int option = JOptionPane.showConfirmDialog(btnApostar, "Deseja realmente fazer a aposta?");
						
						if(option == JOptionPane.YES_OPTION) {				
							apostar(idUsuario, Double.parseDouble(campoValor.getText()));
							JOptionPane.showMessageDialog(btnApostar, "Aposta realizada!");
							//Depois da aposta não existe mais bilhete pendente, então voltamos para a tela principal
							essaTela.setVisible(false);
			        		TelaPrincipalUsuario user =  new TelaPrincipalUsuario(idUsuario);
			        		user.setVisible(true);
			        		user.atualizarTabela();	
						}
						else {
							JOptionPane.showMessageDialog(btnApostar, "Aposta cancelada!");
						}
					}
					else {
						JOptionPane.showMessageDialog(btnApostar, "Não insira vírgulas(,) nos números decimais, utilize pontos(.)!. Exemplo correto: 10.10");
					}
				}
				else {
					JOptionPane.showMessageDialog(btnApostar, "Nenhum valor inserido!");
					campoRetorno.setText("0");
				}
			}
		});
		btnApostar.setForeground(new Color(0, 0, 128));
		btnApostar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnApostar.setBounds(422, 273, 81, 23);
		panel.add(btnApostar);
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
    	DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)); //máscara + colocando os símbolos para o formato dos EUA
    	campoOdd.setText("" + df.format(bilhete.getOddTotal()));      
	}
	
	public void apostar(int idUsuario, double valor) {
		DaoFactory dao = new DaoFactory();		
		
		Bilhete bilhete = dao.criarBilheteDaoJDBC().findBilheteById(dao.criarBilheteDaoJDBC().usuarioTemBilhetePendente(idUsuario));
		bilhete.setEfetuado(true);
		bilhete.setValor(Double.parseDouble(campoValor.getText()));
		bilhete.setOddTotal(Double.parseDouble(campoOdd.getText()));
		bilhete.setRetorno();
		bilhete.setDataDeCriacao();
		dao.criarBilheteDaoJDBC().apostar(bilhete);
	}
}
