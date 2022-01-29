package NQA_VTA21IT5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;

public class Them extends JFrame implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tencs;
	private JTextField tenbh;
	private JTextField thoil;
	
	GUIAdmin gdds;
	
	private String[] th = {"Pop","Ballad","Country","Jazz","Rock","Rap","R and B","EDM"};
	private JComboBox theBox = new JComboBox(th);
	
	public String tenbaihat;
	public String tencasi;
	
	public int thoiluong;
	public String theloai;
	private JTextField loibaihat;
	private String loibh;
	 
	public Them(GUIAdmin id) {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Th\u00EAm Nh\u1EA1c ");
		setSize( 367, 193);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Them.class.getResource("/NQA_VTA21IT5/Them.png")));
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblNewLabel_2 = new JLabel("Tên bài hát");
			contentPanel.add(lblNewLabel_2);
		}
		{
			tenbh = new JTextField();
			contentPanel.add(tenbh);
			tenbh.setColumns(12);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Tên ca sĩ");
			contentPanel.add(lblNewLabel_1);
		}
		{
			tencs = new JTextField();
			contentPanel.add(tencs);
			tencs.setColumns(12);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Thời lượng");
			contentPanel.add(lblNewLabel_3);
		}
		{
			thoil = new JTextField();
			contentPanel.add(thoil);
			thoil.setColumns(12);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Thể loại");
			contentPanel.add(lblNewLabel_4);
		}
		{
			
			contentPanel.add(theBox);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Lời bài hát mở đầu");
			contentPanel.add(lblNewLabel_5);
		}
		{
			loibaihat = new JTextField();
			contentPanel.add(loibaihat);
			loibaihat.setColumns(12);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(245, 222, 179));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("ADD");
				okButton.setBackground(new Color(100, 149, 237));
				okButton.addActionListener(this);
				okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.setBackground(new Color(100, 149, 237));
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setVisible(true);
		
		gdds = id;
	}

	
	public void themnhac() {
		if (tenbh.getText().equals("") || thoil.getText().equals("") || 
				tencs.getText().equals("") || loibaihat.getText().equals("")) {
			JOptionPane.showMessageDialog(rootPane, "Dữ liệu bị trống vui lòng nhập lại!");
		}else {
			try {
				tenbaihat = tenbh.getText();
				tencasi = tencs.getText();
				thoiluong = Integer.parseInt(thoil.getText());
				theloai = (String)theBox.getSelectedItem();
				loibh = loibaihat.getText();
				Nhac n = new Nhac(0, tenbaihat, tencasi, thoiluong, theloai, tenbaihat);
				
				String sqlString = "insert into danhsach(TENBAIHAT,TENCASI,THOILUONG,THELOAI,LOIBAIHAT) "
						+ "values (\"" + n.getTenbaihat() + "\",\"" + n.getTencasi() + "\",\"" + n.getThoiluong() + "\",\"" + n.getTheloai() + "\",\"" + n.getLoibaihat() + "\")";
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydanhsachnhac","root","andubadao123");
				Statement stmt = conn.createStatement();
				

				stmt.executeUpdate(sqlString);
				gdds.load();
				gdds.dModel.fireTableDataChanged();
				
				conn.close();
				stmt.close();
				dispose();
				JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane,"Chưa thêm được vì sai định dạng!");
				
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String add = e.getActionCommand();
		if (add == "ADD") {
			themnhac();
		}else if(add == "CANCEL") {
			setVisible(false);
		}
	}

}
