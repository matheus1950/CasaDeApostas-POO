package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class TelaPrincipalAdm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalAdm frame = new TelaPrincipalAdm();
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
	public TelaPrincipalAdm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 64, 0));
		panel.setBounds(41, 0, 515, 359);
		contentPane.add(panel);
		
		JTextArea txtTelaPrincipal = new JTextArea();
		txtTelaPrincipal.setText("Tela Principal");
		txtTelaPrincipal.setForeground(new Color(128, 255, 255));
		txtTelaPrincipal.setFont(new Font("Tahoma", Font.BOLD, 31));
		txtTelaPrincipal.setEditable(false);
		txtTelaPrincipal.setBackground(new Color(0, 64, 0));
		txtTelaPrincipal.setBounds(147, 0, 214, 42);
		panel.add(txtTelaPrincipal);
		
		textField = new JTextField();
		textField.setText("INCOMPLETA");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(391, 51, 114, 122);
		panel.add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(10, 58, 351, 290);
		panel.add(scrollPane);
	}
}
