package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {
	//vindo da tela login de usuário
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIncompleta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(32, 11, 378, 240);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtTelaPrincipal = new JTextArea();
		txtTelaPrincipal.setText("Tela Principal");
		txtTelaPrincipal.setForeground(new Color(128, 255, 255));
		txtTelaPrincipal.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtTelaPrincipal.setEditable(false);
		txtTelaPrincipal.setBackground(new Color(0, 64, 0));
		txtTelaPrincipal.setBounds(59, 11, 261, 42);
		panel.add(txtTelaPrincipal);
		
		txtIncompleta = new JTextField();
		txtIncompleta.setHorizontalAlignment(SwingConstants.CENTER);
		txtIncompleta.setText("INCOMPLETA");
		txtIncompleta.setBounds(87, 81, 163, 122);
		panel.add(txtIncompleta);
		txtIncompleta.setColumns(10);
	}

}
