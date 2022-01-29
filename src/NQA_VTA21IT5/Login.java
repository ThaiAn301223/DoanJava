package NQA_VTA21IT5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame implements ActionListener{

	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel label;
	private JLabel label_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private final String tk = "An123";
	private final String mk = "123";
	
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/NQA_VTA21IT5/Dn-removebg-preview.png")));
		setTitle("Login ADMIN");
		
		setSize(269, 195);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		label = new JLabel("");
		
		textField = new JTextField();
		textField.setColumns(12);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu           ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(12);
		
		label_1 = new JLabel("");
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		contentPane.add(lblNewLabel);
		contentPane.add(label);
		contentPane.add(textField);
		contentPane.add(lblNewLabel_1);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("OK");
		btnNewButton.setBackground(new Color(100, 149, 237));
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBackground(new Color(100, 149, 237));
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		contentPane.add(label_1);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String login = e.getActionCommand();
		if (login == "OK") {
			if (tk.equals(textField.getText()) && mk.equals(String.valueOf(passwordField.getPassword()))) {
				setVisible(false);
				GUIAdmin gda = new GUIAdmin();
			}else {
				JOptionPane.showMessageDialog(rootPane, "Truy cập thất bại","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if (login == "Cancel") {
			setVisible(false);
		}
		
	}

}
