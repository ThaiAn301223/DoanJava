package NQA_VTA21IT5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.GridLayout;

public class Sua extends JFrame implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tenbh;
	private JTextField tencs;
	private JTextField thoil;
	
	private JButton okButton ;
	private JButton cancelButton;
	
	private String mabaihat;
	
	private JComboBox bostheloai;
	private String[] th = {"Pop","Ballad","Country","Jazz","Rock","Rap","R and B","EDM"};
	
	GUIAdmin guiAdmin;
	private JLabel lblNewLabel_5;
	private JTextField loibaihat;
	

	public Sua(GUIAdmin a,String id,String t,String ca,String tt,String lb) {
		
		guiAdmin = a;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sua.class.getResource("/NQA_VTA21IT5/Edit-removebg-preview.png")));
		setTitle("Sửa nhạc");
		setSize( 364, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblNewLabel_1 = new JLabel("Tên bài hát");
			contentPanel.add(lblNewLabel_1);
		}
		{
			tenbh = new JTextField();
			tenbh.setText(t);
			contentPanel.add(tenbh);
			tenbh.setColumns(12);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Tên ca sĩ");
			contentPanel.add(lblNewLabel_2);
		}
		{
			tencs = new JTextField();
			tencs.setText(ca);
			contentPanel.add(tencs);
			tencs.setColumns(12);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Thời lượng(s)");
			contentPanel.add(lblNewLabel_3);
		}
		{
			thoil = new JTextField();
			thoil.setText(tt);
			contentPanel.add(thoil);
			thoil.setColumns(12);
		}
		
		{
			JLabel lblNewJLabel_4 = new JLabel("Thể loại");
			contentPanel.add(lblNewJLabel_4);
		}
		{
			bostheloai = new JComboBox(th);
			contentPanel.add(bostheloai);
		}
		{
			lblNewLabel_5 = new JLabel("Lời bài hát mở đầu");
			contentPanel.add(lblNewLabel_5);
		}
		{
			loibaihat = new JTextField(lb);
			contentPanel.add(loibaihat);
			loibaihat.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(245, 222, 179));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setBackground(new Color(100, 149, 237));
				okButton.setFont(new Font("Tahoma", Font.BOLD, 10));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(100, 149, 237));
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 10));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
		mabaihat = id;
		guiAdmin = a;
	}

	public void suanhac() {
		if (tenbh.getText().equals("") || tencs.getText().equals("") || thoil.getText().equals("") || loibaihat.getText().equals("")) {
			JOptionPane.showMessageDialog(rootPane, "Chưa điền thông tin cần sửa!");
			
		}else {
			try {
				String tenbaihat = tenbh.getText();
				String tencasi = tencs.getText();
				int thoiluong = Integer.parseInt(thoil.getText());
				String theloai = (String)bostheloai.getSelectedItem();
				String loibh = loibaihat.getText();
				
				String sql = "update danhsach set TENBAIHAT = \"" + tenbaihat + 
						"\",TENCASI = \"" + tencasi + 
						"\",THOILUONG = " + thoiluong + 
						",THELOAI = \"" + theloai + "\",LOIBAIHAT = \"" + loibh + 
						"\" where MABAIHAT = \"" + mabaihat + "\"";
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydanhsachnhac","root","andubadao123");
				Statement ts = co.createStatement();
				ts.executeUpdate(sql);
				
				guiAdmin.load();
				guiAdmin.dModel.fireTableDataChanged();
				
				co.close();
				ts.close();
				this.dispose();
				JOptionPane.showMessageDialog(rootPane, "Sửa thành công!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Sửa không thành công!");
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String edit = e.getActionCommand();
		if (edit == "OK") {
			suanhac();
		}else if (edit == "Cancel") {
			setVisible(false);
		}
		
	}
	
	

}
