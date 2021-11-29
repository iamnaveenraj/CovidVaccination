package covidvaccine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UI
{
	public static void main(String[] args) throws Exception
	{
		MyFrame f = new MyFrame();
	}
}

class MyFrame extends JFrame implements ActionListener 
{
	private Container c;
	private JLabel title;
	private JLabel dob;
	private JComboBox date;
	private JComboBox month;
	private JComboBox year;
	private JLabel vtype;
	private JComboBox cb;
	private JLabel name;
	private JTextField tname;
	private JLabel add;
	private JTextField tadd;
	private JLabel mno;
	private JTextField tmno;
	private JLabel email;
	private JTextField temail;
	private JButton sub;
	private JLabel result;
	

	private String dates[]
		= { "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30",
			"31" };
	private String months[]
		= { "Jan", "feb", "Mar", "Apr",
			"May", "Jun", "July", "Aug",
			"Sup", "Oct", "Nov", "Dec" };
	private String years[]
		= {"2015", "2016", "2017", "2018",
			"2019","2020","2021","2022" };
	
	private String vaccine[] = {"Covishield","Covaxin","Sputnik"};

	public MyFrame()
	{
		setTitle("Covid Vaccination Slot Booking");
		setBounds(300, 90, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Slot Booking");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 40);
		title.setLocation(300, 30);
		c.add(title);
		
		dob = new JLabel("Booking Date");
		dob.setFont(new Font("Arial", Font.PLAIN, 20));
		dob.setSize(200, 30);
		dob.setLocation(100, 100);
		c.add(dob);

		date = new JComboBox(dates);
		date.setFont(new Font("Arial", Font.PLAIN, 15));
		date.setSize(70, 30);
		date.setLocation(270, 100);
		c.add(date);

		month = new JComboBox(months);
		month.setFont(new Font("Arial", Font.PLAIN, 15));
		month.setSize(70, 30);
		month.setLocation(340, 100);
		c.add(month);

		year = new JComboBox(years);
		year.setFont(new Font("Arial", Font.PLAIN, 15));
		year.setSize(70, 30);
		year.setLocation(410, 100);
		c.add(year);
		
		vtype = new JLabel("Vaccination Type");
		vtype.setFont(new Font("Arial", Font.PLAIN, 20));
		vtype.setSize(200, 30);
		vtype.setLocation(100, 150);
		c.add(vtype);
		
		cb = new JComboBox(vaccine);
        cb.setFont(new Font("Arial", Font.PLAIN, 20));
        cb.setSize(150, 30);
		cb.setLocation(270, 150);
        c.add(cb);
        

		name = new JLabel("Name");
		name.setFont(new Font("Arial", Font.PLAIN, 20));
		name.setSize(100, 30);
		name.setLocation(100, 200);
		c.add(name);

		tname = new JTextField();
		tname.setFont(new Font("Arial", Font.PLAIN, 15));
		tname.setSize(200, 30);
		tname.setLocation(270, 200);
		c.add(tname);
		
		add = new JLabel("Address");
		add.setFont(new Font("Arial", Font.PLAIN, 20));
		add.setSize(100, 30);
		add.setLocation(100, 250);
		c.add(add);

		tadd = new JTextField();
		tadd.setFont(new Font("Arial", Font.PLAIN, 15));
		tadd.setSize(200, 30);
		tadd.setLocation(270, 250);
		c.add(tadd);

		mno = new JLabel("Mobile No");
		mno.setFont(new Font("Arial", Font.PLAIN, 20));
		mno.setSize(100, 30);
		mno.setLocation(100, 300);
		c.add(mno);

		tmno = new JTextField();
		tmno.setFont(new Font("Arial", Font.PLAIN, 15));
		tmno.setSize(200, 30);
		tmno.setLocation(270, 300);
		c.add(tmno);
		
		email = new JLabel("Email Id");
		email.setFont(new Font("Arial", Font.PLAIN, 20));
		email.setSize(100, 30);
		email.setLocation(100, 350);
		c.add(email);
		
		temail = new JTextField();
		temail.setFont(new Font("Arial", Font.PLAIN, 20));
		temail.setSize(200, 30);
		temail.setLocation(270, 350);
		c.add(temail);
		
		sub = new JButton("Submit");
		sub.setFont(new Font("Arial", Font.PLAIN, 15));
		sub.setSize(100, 30);
		sub.setLocation(250, 450);
		sub.addActionListener(this);
		c.add(sub);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == sub) 
		{
			String dob,name,address,mobno,email,vtype,da = null;
			
			name = tname.getText();
			address = tadd.getText();
			email = temail.getText();
			mobno = tmno.getText();
			dob = (String)date.getSelectedItem()+ "-" + (String)month.getSelectedItem()+ "-" + (String)year.getSelectedItem();
			vtype = (String) cb.getSelectedItem();
			
			try 
			{
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covidvaccination","root","");
				 
				 Statement stmt=con.createStatement();
				 String query = "Select date from vaccine where date ='"+dob+"'";
				 ResultSet rs = stmt.executeQuery(query);
				 
				 if(!rs.next()) 
				 {
					 PreparedStatement stmt2=con.prepareStatement("insert into vaccine value(?,?,?,?)");
					 stmt2.setString(1,dob);
					 stmt2.setString(2,"20");
					 stmt2.setString(3,"20");
					 stmt2.setString(4,"10");
					 stmt2.executeUpdate();
				 }
				 
				 ResultSet rsV = stmt.executeQuery("Select * from vaccine where date ='"+dob+"'");
				 rsV.next();
				 
				 	 int temp = 0;
				 	 if(vtype == "Covishield") 
				 	 {
				 		 temp = 2;
				 	 }
				 	 else if(vtype == "Covaxin") 
				 	 {
				 		 temp = 3;
				 	 }
				 	 else if(vtype == "Sputnik") 
				 	 {
				 		 temp = 4;
				 	 }else 
				 	 {
				 		 System.exit(0);
				 	 }
				 	 
				 	 
					 String no = rsV.getString(temp);
					 if(Integer.parseInt(no) >= 1) 
					 {
						 PreparedStatement st=con.prepareStatement("INSERT INTO user VALUES (?,?,?,?)");
						 st.setString(1,name);  
						 st.setString(2,address);
						 st.setString(3,mobno);
						 st.setString(4,email);
						 st.executeUpdate();
						 
						 PreparedStatement st2=con.prepareStatement("INSERT INTO appointment VALUES (?,?,?)");
						 st2.setString(1, email);
						 st2.setString(2, vtype);
						 st2.setString(3, dob);
						 st2.executeUpdate();
						
						 Statement st1 = con.createStatement();
						 String query2 = "Update vaccine set " + vtype + " = '"+(Integer.parseInt(no)-1)+"' where date = '"+dob+"'";
						 st1.executeUpdate(query2);
						 JOptionPane.showMessageDialog(title, "Your "+vtype+ " is Booked on "+dob); 
					 }
					 else 
					 {
						 JOptionPane.showMessageDialog(title, "Slots are Not Available");
						 System.exit(0);
					 }
				 }
			catch(Exception e1) 
			{
				 e1.printStackTrace();
			}
		}
	}
			
}
