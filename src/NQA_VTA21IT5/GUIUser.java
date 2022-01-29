package NQA_VTA21IT5;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class GUIUser extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	
	private JPanel pan1 = new JPanel();
	private JPanel pan2 = new JPanel();
	private JPanel pan3 = new JPanel();
	private JPanel pan4 = new JPanel();

	private JButton timkiem = new JButton("TÌM KIẾM");
	private JButton thoat = new JButton("THOÁT");
	private JButton about = new JButton("ABOUT");
	private JButton tongso = new JButton("TỔNG SỐ"); 
	
	private JLabel karaoke = new JLabel("KARAOKE LIST");
	
	private String[] title = {"Mã bài hát","Tên bài hát","Tên ca sĩ","Thời lượng(s)","Thể loại","Lời bài hát mở đầu"};
	
	private JTable danhsach;
	private DefaultTableModel dModel;
	private JScrollPane sPane;
	
	private Vector vTitle = new Vector();
	private Vector vData = new Vector();
	
	private int removeIndex;

	private final JLabel label = new JLabel("New label");
	private final JPanel panel = new JPanel();
	private final JTextField textField = new JTextField();
	String[] chosse = {"Tên bài hát","Tên ca sĩ"};
	private final JComboBox comboBox = new JComboBox(chosse);
	String sql = "";
	private final JButton hienthi = new JButton("HIỂN THỊ");

	public GUIUser() {
		textField.setColumns(12);
		setTitle("Karaoke List");
		setSize(1100,600);
		
		URL urlIconNotepad = GUIAdmin.class.getResource("logo.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
		setIconImage(img);
		
		getContentPane().setLayout(new BorderLayout());
		pan1.setBackground(new Color(245, 222, 179));
		//Thêm tiêu đề
		getContentPane().add(pan1,BorderLayout.NORTH);
		pan1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		karaoke.setBackground(UIManager.getColor("Button.disabledShadow"));
		karaoke.setFont(new Font("Sitka Heading", Font.BOLD, 40));
		karaoke.setForeground(Color.DARK_GRAY);
		pan1.add(karaoke);
		
		pan2.setBackground(Color.LIGHT_GRAY);
		//Thêm danh sách
		getContentPane().add(pan2,BorderLayout.CENTER);
		load();
		dModel = new DefaultTableModel(vData,vTitle);
		danhsach = new JTable(dModel);
		danhsach.setFont(new Font("Tahoma", Font.ITALIC, 13));
		danhsach.setForeground(Color.BLACK);
		danhsach.setBackground(Color.LIGHT_GRAY);
		
		sPane = new JScrollPane(danhsach);
		danhsach.getColumnModel().getColumn(0).setPreferredWidth(60);
		danhsach.getColumnModel().getColumn(1).setPreferredWidth(60);
		danhsach.getColumnModel().getColumn(2).setPreferredWidth(60);
		danhsach.getColumnModel().getColumn(3).setPreferredWidth(80);
		danhsach.getColumnModel().getColumn(4).setPreferredWidth(90);
		pan2.setLayout(new BorderLayout(0, 0));
		danhsach.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		pan2.add(sPane);
		
		sPane.setColumnHeaderView(label);
		
		sPane.setColumnHeaderView(panel);
		hienthi.setBackground(new Color(100, 149, 237));
		hienthi.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		pan2.add(hienthi, BorderLayout.NORTH);
		
		//Chỉnh sửa các nút
		timkiem.setForeground(Color.DARK_GRAY);
		timkiem.setFont(new Font("Segoe UI Light", Font.BOLD | Font.ITALIC, 12));
		timkiem.setBackground(new Color(210, 180, 140));
		tongso.setForeground(Color.DARK_GRAY);
		tongso.setFont(new Font("Segoe UI Light", Font.BOLD | Font.ITALIC, 12));
		tongso.setBackground(new Color(100, 149, 237));
		about.setForeground(Color.DARK_GRAY);
		about.setFont(new Font("Segoe UI Light", Font.BOLD | Font.ITALIC, 12));
		about.setBackground(new Color(100, 149, 237));
		thoat.setForeground(Color.DARK_GRAY);
		thoat.setFont(new Font("Segoe UI Light", Font.BOLD | Font.ITALIC, 12));
		thoat.setBackground(new Color(100, 149, 237));
		
		//Thêm các nút
		getContentPane().add(pan3,BorderLayout.SOUTH);
		pan3.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		pan3.setBackground(new Color(245, 222, 179));
		comboBox.setFont(new Font("Tahoma", Font.ITALIC, 10));
		comboBox.setBackground(new Color(210, 180, 140));
		
		pan3.add(comboBox);
		
		pan3.add(textField);
		pan3.add(timkiem);
		pan3.add(about);
		pan3.add(tongso);
		pan3.add(thoat);
		
		
		//Thêm hành động cho các nút
		timkiem.addActionListener(this);
		about.addActionListener(this);
		thoat.addActionListener(this);
		tongso.addActionListener(this);
		hienthi.addActionListener(this);
		
		
		
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void  load() {
		try {
			vTitle.clear();
			vData.clear();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydanhsachnhac","root","andubadao123");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from danhsach");
			ResultSetMetaData rsmt = rs.getMetaData();
			
			int num_column = rsmt.getColumnCount();
			
			for (int i = 0; i < num_column; i++) {
				vTitle.add(title[i]);
			}
			
			while (rs.next()) {
				Vector row = new Vector<>(num_column);
				for (int i = 1; i <= num_column; i++) {
					row.add(rs.getString(i));
					
				}
				vData.add(row);
			}
			
			conn.close();
			rs.close();
			st.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public int tongso() {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydanhsachnhac","root","andubadao123");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from danhsach");
			
			while(rs.next()) {
				count++;
			}
			conn.close();
			st.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public String luachon() {
		if (comboBox.getSelectedItem() == "Tên bài hát") {
			sql = "select * from danhsach where TENBAIHAT = \"" + textField.getText() + "\"";
		}else if (comboBox.getSelectedItem() == "Tên ca sĩ") {
			sql = "select * from danhsach where TENCASI = \"" + textField.getText() + "\"";
		}
		return sql;
	}
	
	public void timkiem() {
		if (textField.getText().equals("")) {
			JOptionPane.showMessageDialog(rootPane, "Xin hãy nhập tên bài hát hoặc tên ca sĩ");
		}else {
			try {
				vData.clear();

				Class.forName("com.mysql.jdbc.Driver");
				Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydanhsachnhac","root","andubadao123");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(luachon());
				ResultSetMetaData rsmt = rs.getMetaData();
				int colum = rsmt.getColumnCount();

				
				while (rs.next()) {
					Vector row = new Vector<>(colum);
					for (int i = 1; i <= colum; i++) {
						row.add(rs.getString(i));
						
					}
					vData.add(row);
				}
				
				dModel.fireTableDataChanged();
				
				conn.close();
				st.close();
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Không tìm thấy dữ liệu ");
			}
		}
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String check = e.getActionCommand();
		if (check == "ABOUT") {
			JFrame dia = new JFrame();
			JOptionPane.showMessageDialog(dia, "Nguyễn Quốc An\n Vũ Thái An\n Lớp 21IT5","Thông tin",JOptionPane.INFORMATION_MESSAGE);
		}else if(check == "TÌM KIẾM") {
			timkiem();
		}else if(check == "THOÁT") {
			System.exit(0);
		}else if (check == "TỔNG SỐ") {
			JOptionPane.showMessageDialog(rootPane, "Số bài hát hiện có: " + tongso(),"Tổng",JOptionPane.INFORMATION_MESSAGE);
		}else if (check == "HIỂN THỊ") {
			load();
			dModel.fireTableDataChanged();
		}
		
		
	}

	
}