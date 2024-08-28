package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;
import entidades.Aposta;
import entidades.Evento;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class CadastroDeAposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textOdd;
	private JTextField campoOdd;
	private JTextField campoDescricao;
	private JTextField textDescricao;
	private JButton btnLogout;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeAposta frame = new CadastroDeAposta(-1, -1, new ApostasAdm(-1, -1)); //-1 padrão, talvez mudar
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
	public CadastroDeAposta(int idEvento, int idUsuario, ApostasAdm frame) {
		DaoFactory dao = new DaoFactory();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, 0, 511, 297);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 11, 491, 264);
		contentPane_1.add(panel);
		
		JTextArea campoNumAposta = new JTextArea();
		campoNumAposta.setForeground(new Color(255, 0, 0));
		campoNumAposta.setBackground(new Color(0, 64, 0));
		campoNumAposta.setBounds(38, 157, 402, 62);
		panel.add(campoNumAposta);
		if(contarApostas(idEvento, dao) == 0) {
			campoNumAposta.setText("O evento não tem apostas!");
		}
		else {
			campoNumAposta.setText("O evento já possui " + contarApostas(idEvento, dao) + " aposta(s)!\n(máximo 2!)\n(a 2ª odd é automática!)");
		}
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarAposta(btnCadastrar, idEvento, frame, campoNumAposta);
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnCadastrar.setBounds(38, 230, 119, 23);
		panel.add(btnCadastrar);
		
		textOdd = new JTextField();
		textOdd.setHorizontalAlignment(SwingConstants.CENTER);
		textOdd.setText("Odd");
		textOdd.setEditable(false);
		textOdd.setColumns(10);
		textOdd.setBackground(new Color(64, 128, 128));
		textOdd.setBounds(38, 109, 69, 20);
		panel.add(textOdd);
		
		campoOdd = new JTextField();
		campoOdd.setColumns(10);
		campoOdd.setBounds(134, 109, 119, 20);
		panel.add(campoOdd);
		
		if(contarApostas(idEvento, dao) == 2) {
			campoOdd.setVisible(false);
			textOdd.setVisible(false);
		}
		else if(contarApostas(idEvento, dao) == 1){
			campoOdd.setVisible(false);
			textOdd.setVisible(false);
		}
		else {
			campoOdd.setVisible(true);
			textOdd.setVisible(true);
		}
		
		campoDescricao = new JTextField();
		campoDescricao.setColumns(10);
		campoDescricao.setBounds(134, 72, 119, 20);
		panel.add(campoDescricao);
		
		textDescricao = new JTextField();
		textDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		textDescricao.setText("Descricao");
		textDescricao.setEditable(false);
		textDescricao.setColumns(10);
		textDescricao.setBackground(new Color(64, 128, 128));
		textDescricao.setBounds(38, 72, 69, 20);
		panel.add(textDescricao);
		
		JTextArea txtrCadastroDeAposta = new JTextArea();
		txtrCadastroDeAposta.setText("Cadastrar aposta");
		txtrCadastroDeAposta.setForeground(new Color(128, 255, 255));
		txtrCadastroDeAposta.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrCadastroDeAposta.setEditable(false);
		txtrCadastroDeAposta.setBackground(new Color(0, 64, 0));
		txtrCadastroDeAposta.setBounds(46, 11, 285, 42);
		panel.add(txtrCadastroDeAposta);
		
		CadastroDeAposta essaTela = this;
		
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
		btnLogout.setBackground(UIManager.getColor("CheckBox.focus"));
		btnLogout.setBounds(367, 29, 114, 23);
		panel.add(btnLogout);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		essaTela.setVisible(false);
        		ApostasAdm adm = new ApostasAdm(idEvento, idUsuario);
        		adm.setVisible(true);
        		adm.atualizarTabela(idEvento);
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(367, 230, 81, 23);
		panel.add(btnVoltar);
	}
	
	public void cadastrarAposta(JButton btnCadastrar, int idEvento, ApostasAdm frame, JTextArea campoNumAposta) {
		Aposta aposta = new Aposta();
		DaoFactory dao = new DaoFactory();
		Double odd;
		
		if(!campoDescricao.getText().equals("")) {
			aposta.setDescricao(campoDescricao.getText());
		}
		else {
			JOptionPane.showMessageDialog(btnCadastrar, "Descrição não digitada!");
			return;
		}
		
		if(contarApostas(idEvento, dao) < 2) {
			if(contarApostas(idEvento, dao) == 0) {
				if(!campoOdd.getText().equals("")) {
					odd = Double.parseDouble(campoOdd.getText());
					aposta.setOdd(odd);
					aposta.setIdDeEvento(idEvento);
					dao.criarApostaDaoJDBC().insert(aposta);
					frame.atualizarTabela(idEvento);
					atualizarTela(idEvento, dao, campoNumAposta);
				}
				else {
					JOptionPane.showMessageDialog(btnCadastrar, "Odd não digitada!");
					return;
				}
			}
			else {								
				odd = calculoDaOddOposta(oddDaPrimeiraApostaDoEvento(idEvento, dao)); //sobreescreve a odd escrita pelo Adm	
				odd = Math.round(odd * 100.0) / 100.0;
				
				aposta.setOdd(odd); 
				aposta.setIdDeEvento(idEvento);
				dao.criarApostaDaoJDBC().insert(aposta);
				frame.atualizarTabela(idEvento);
				atualizarTela(idEvento, dao, campoNumAposta);
			}
		}
		else {
			JOptionPane.showMessageDialog(btnCadastrar, "Não pode haver mais de 2 opções de aposta por evento!");
			return;
		}
	}
	
	public int contarApostas(int idEvento, DaoFactory dao) {	
		ArrayList<Aposta> apostas = dao.criarApostaDaoJDBC().ListarApostasPorEventoId(idEvento);
		
		return apostas.size();
	}
	
	public Double oddDaPrimeiraApostaDoEvento(int idEvento, DaoFactory dao) {	
		ArrayList<Aposta> apostas = dao.criarApostaDaoJDBC().ListarApostasPorEventoId(idEvento);
		
		return apostas.get(0).getOdd();
	}
	
	public Double calculoDaOddOposta(Double odd1) {
		Double odd2 = (odd1/(odd1 - 1));
		System.out.println("odd2> + " + odd2);
		return odd2;
	}
	
	private void atualizarTela(int idEvento, DaoFactory dao, JTextArea campoNumAposta) {
        int numeroDeApostas = contarApostas(idEvento, dao);

        if(numeroDeApostas >= 1) {
            campoOdd.setVisible(false);
            textOdd.setVisible(false);
        } else {
            campoOdd.setVisible(true);
            textOdd.setVisible(true);
        }

        if(numeroDeApostas == 0) {
            campoNumAposta.setText("O evento não tem apostas!");
        } else {
            campoNumAposta.setText("O evento já possui " + numeroDeApostas + " aposta(s)!\n(máximo 2!)\n(a 2ª odd é automática!)");
        }
    }
}
