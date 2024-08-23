package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.DaoFactory;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class AlterarSenha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSenha;
	private JTextField campoSenhaAtual;
	private JTextField campoNovaSenha;
	private JTextField txtNovaSenha;
	private JTextField txtConfirmarSenhaNova;
	private JTextField campoConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarSenha frame = new AlterarSenha(-1, null); //-1 padrão
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
	 * @param senhaAntiga 
	 */
	public AlterarSenha(int idUsuario, String senhaAntiga) {
		AlterarSenha essaTela = this;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(64, 128, 128));
		contentPane_1.setBounds(0, -12, 413, 316);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(10, 22, 393, 268);
		contentPane_1.add(panel);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar(btnSalvar, idUsuario, senhaAntiga);
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 128));
		btnSalvar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnSalvar.setBounds(46, 234, 119, 23);
		panel.add(btnSalvar);
		
		txtSenha = new JTextField();
		txtSenha.setText("Senha Atual");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setEditable(false);
		txtSenha.setColumns(10);
		txtSenha.setBackground(new Color(64, 128, 128));
		txtSenha.setBounds(24, 72, 100, 20);
		panel.add(txtSenha);
		
		campoSenhaAtual = new JTextField();
		campoSenhaAtual.setColumns(10);
		campoSenhaAtual.setBounds(134, 72, 119, 20);
		panel.add(campoSenhaAtual);
		
		campoNovaSenha = new JTextField();
		campoNovaSenha.setColumns(10);
		campoNovaSenha.setBounds(134, 103, 119, 20);
		panel.add(campoNovaSenha);
		
		txtNovaSenha = new JTextField();
		txtNovaSenha.setText("Nova Senha");
		txtNovaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtNovaSenha.setEditable(false);
		txtNovaSenha.setColumns(10);
		txtNovaSenha.setBackground(new Color(64, 128, 128));
		txtNovaSenha.setBounds(24, 103, 100, 20);
		panel.add(txtNovaSenha);
		
		JTextArea txtrAlterarSenha = new JTextArea();
		txtrAlterarSenha.setText("Alterar senha");
		txtrAlterarSenha.setForeground(new Color(128, 255, 255));
		txtrAlterarSenha.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtrAlterarSenha.setEditable(false);
		txtrAlterarSenha.setBackground(new Color(0, 64, 0));
		txtrAlterarSenha.setBounds(46, 11, 226, 42);
		panel.add(txtrAlterarSenha);
		
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
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(283, 29, 97, 23);
		panel.add(btnLogout);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnVoltar, "Deseja realmente voltar?"); //acho que aqui posso tirar esse tipo de confirmação
        		if(option == JOptionPane.YES_OPTION) {
	        		essaTela.setVisible(false);
	        		MinhaConta conta = new MinhaConta(idUsuario);
	        		conta.setVisible(true);
        		}
        		else {
        			JOptionPane.showMessageDialog(btnVoltar, "Cancelado!");
        		}
			}
		});
		btnVoltar.setForeground(new Color(0, 0, 128));
		btnVoltar.setBackground(UIManager.getColor("CheckBox.focus"));
		btnVoltar.setBounds(299, 234, 81, 23);
		panel.add(btnVoltar);
		
		txtConfirmarSenhaNova = new JTextField();
		txtConfirmarSenhaNova.setText("Confirmar Senha");
		txtConfirmarSenhaNova.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfirmarSenhaNova.setEditable(false);
		txtConfirmarSenhaNova.setColumns(10);
		txtConfirmarSenhaNova.setBackground(new Color(64, 128, 128));
		txtConfirmarSenhaNova.setBounds(24, 134, 100, 20);
		panel.add(txtConfirmarSenhaNova);
		
		campoConfirmar = new JTextField();
		campoConfirmar.setColumns(10);
		campoConfirmar.setBounds(134, 134, 119, 20);
		panel.add(campoConfirmar);
	}
	
	public void salvar(JButton botao, int id, String senhaAntiga) {
		DaoFactory dao = new DaoFactory();
		//Se forem ambos campos vazios ou ambos iguais aos campos já registrados, não chama o método de editar
	
		if(campoSenhaAtual.getText().equals("") || campoNovaSenha.getText().equals("") || campoConfirmar.getText().equals("")) {
			JOptionPane.showMessageDialog(botao, "Nenhum campo pode ficar em branco!");
		}
		else if(campoNovaSenha.getText().equals(senhaAntiga)) {
			JOptionPane.showMessageDialog(botao, "Senha igual a anterior, coloque uma senha diferente!");
		}
		else {
			if(campoSenhaAtual.getText().equals(senhaAntiga)) {
				if(campoNovaSenha.getText().equals(campoConfirmar.getText())) {
					dao.criarUsuarioDaoJDBC().editarSenha(id, campoConfirmar.getText());
					JOptionPane.showMessageDialog(botao, "Senha atualizada com sucesso!");
				}
				else {
					JOptionPane.showMessageDialog(botao, "A confirmação de nova senha não confere!");
				}
			}
			else {
				JOptionPane.showMessageDialog(botao, "Senha atual não confere!");
			}
		}
	}

}
