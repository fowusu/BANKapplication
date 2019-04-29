package bankGUi;


import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class BankGUI {
	
	OpenAccount opNewAcc = new OpenAccount();
	DepositMoney dpoNewM = new DepositMoney();
	WithdrawMoney withDrNewM = new WithdrawMoney();
	checkBalance chkNewBlnce = new checkBalance();
	JFrame Win = new JFrame(); 
	String depositStr;
	String withdrawtStr;
	BankGUI(){

		
		JMenuBar MenuBar = new JMenuBar();
		
		JMenu Menu1 = new JMenu("BANK");
		
		
		JMenuItem M1 = new JMenu("OPEN ACCOUNT");
		JMenuItem M2 = new JMenu("DEPOSIT MONEY");
		JMenuItem M3 = new JMenu("WITHRDAW MONEY");
		JMenuItem M4 = new JMenu("CHECK BALANCE");
		JMenuItem S1 = new JMenuItem("open new account");
		JMenuItem S2 = new JMenuItem("deposit");
		JMenuItem S3 = new JMenuItem("withdraw");
		JMenuItem S4 = new JMenuItem("check balance");
		
		Menu1.add(M1); 
		Menu1.add(M2);
		Menu1.add(M3); 
		Menu1.add(M4);
		
		M1.add(S1);
		M2.add(S2);
		M3.add(S3);
		M4.add(S4);
		
		MenuBar.add(Menu1);
		
		Win.setSize(200,200);
		Win.setJMenuBar(MenuBar);
		Win.setVisible(true);
		
		S1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				Win.setVisible(false);
				opNewAcc.opAcc.setVisible(true);
				
			}
		});
		
		S2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Win.setVisible(false);
				dpoNewM.dpoM.setVisible(true);
				
			}
		});
		S3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Win.setVisible(false);
				withDrNewM.withDrM.setVisible(true);
				
			}
		});
		S4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Win.setVisible(false);
				chkNewBlnce.chkBlnce.setVisible(true);
				
			}
		});
	}
	
	public class OpenAccount implements ActionListener{
		
		JFrame opAcc = new JFrame();
		JTextField T1, T2, T3, T4, T5,T6,T7;
		JLabel L1,L2,L3,L4,L5,L6,L7,L8,L9,accNoLabel;
		JButton B1, B2; 
		ResultSet Rs;
		String Gender; 
		String accType;
		JRadioButton R1=new JRadioButton("A) Male");    
		JRadioButton R2=new JRadioButton("B) Female"); 
		JRadioButton R3=new JRadioButton("A) CURRENT");    
		JRadioButton R4=new JRadioButton("B) SAVINGS");
		
		OpenAccount(){
			ButtonGroup group = new ButtonGroup();
			ButtonGroup group2 = new ButtonGroup();
			group.add(R1);
			group.add(R2);
			
			group2.add(R3);
			group2.add(R4);
			
			T1 = new JTextField(10);
			T2 = new JTextField(10);
			T3 = new JTextField(10);
			T4 = new JTextField(10);
			T5 = new JTextField(10);
			T6 = new JTextField(10);
			T7 = new JTextField(10);
			
			
			
			B1 = new JButton("SAVE");
			B2 = new JButton("RETURN");
			L1 = new JLabel("Account No:");
			L2 = new JLabel("NAME:");
			L3 = new JLabel("Adress:");
			L4 = new JLabel("ACCOUNT TYPE:");
			L5 = new JLabel("GENDER:");
			
			L8 = new JLabel("           ");
			L9 = new JLabel("           ");
			accNoLabel = new JLabel("");
			
			
			opAcc.setLayout(new GridLayout(9, 2));
			
			opAcc.add(L1); 
			opAcc.add(accNoLabel);
			opAcc.add(L2);
			opAcc.add(T2);
			opAcc.add(L3);
			opAcc.add(T3);
			opAcc.add(L4);
			opAcc.add(L8);
			opAcc.add(R3);
			opAcc.add(R4);
			opAcc.add(L5);
			opAcc.add(L9);
			opAcc.add(R1);// MALE 
			opAcc.add(R2);// FEMALE
			
			opAcc.add(B1);
			opAcc.add(B2);
			
			
			B1.addActionListener(this);
			B2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					opNewAcc.opAcc.setVisible(false);
					Win.setVisible(true);
					
				}
				
			});
			
			R1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					  Gender = "M";
			            System.out.println(Gender);
				}
				
			});
			R2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					  Gender = "F";
			            System.out.println(Gender);
				}
				
			});
			R3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					  accType = "C";
			            System.out.println(accType);
				}
				
			});
			R4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					  accType = "S";
			            System.out.println(accType);
				}
		
			});
			
		opAcc.setSize(600,200);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 
	      
		 
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");// Driver
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bankdbs","root","");//Ip adress/name of db/username,password
			
			Statement St = con.createStatement();
			
			if (accType.equals("C")) {
			
			Rs = St.executeQuery("SELECT MAX(SUBSTRING(ACCNO,3,3))+1 as T From bank WHERE SUBSTRING(ACCNO,1,1) = 'C'");
			
			}
			if (accType.equals("S")) {
				
				Rs = St.executeQuery("SELECT MAX(SUBSTRING(ACCNO,3,3))+1 as T From bank WHERE SUBSTRING(ACCNO,1,1) = 'S'");
				
				}
			
			Rs.next(); 
			
			int newAccNo=Rs.getInt(1); 
			//St.executeUpdate("Insert into School values(" + newRegNo +")"); 
			
			St.executeUpdate("insert into bank values("+"'"+accType+Gender+newAccNo+"'"+","+"'"+T2.getText()+"'"+","+"'"+T3.getText()+"'"+")"); 
			
			accNoLabel.setText(accType+Gender+Integer.toString(newAccNo));
			

		}catch(Exception E) {
			System.out.print(E.toString());
		}
		
	}
	
	
	}
	
	
	public class DepositMoney implements ActionListener{
		
		JFrame dpoM = new JFrame();
		JLabel L1; 
		JTextField T1; 
		JButton B1,B2; 
		
		ResultSet Rs;
		
		DepositMoney(){
			
			
			
			T1 = new JTextField(10);
			
			L1 = new JLabel("ACCOUNT NO:");
		
			B1 = new JButton("LOGIN");
			B2 = new JButton("RETURN");
			
			dpoM.setLayout(new GridLayout(1, 1));
			
			dpoM.add(L1); 
			dpoM.add(T1);
			dpoM.add(B1); 
			dpoM.add(B2); 
			
			dpoM.setSize(500,200);
			
			B1.addActionListener(this);
			B2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dpoNewM.dpoM.setVisible(false);
					Win.setVisible(true);
					
				}
				
			});
			
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String currentAccNo = T1.getText(); 
            
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");// Driver
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bankdbs","root","");//Ip adress/name of db/username,password
				
				Statement St = con.createStatement();
				
				
				Rs = St.executeQuery("SELECT * FROM bank WHERE ACCNO='" + currentAccNo + "'");
				
			
				if(Rs.next()) {
					
					depositStr = JOptionPane.showInputDialog("DEPOSIT AMOUNT");
			
		
					St.executeUpdate("insert into deposit values("+"'"+currentAccNo+"'"+","+"'"+Integer.parseInt(depositStr)+"'"+","+"sysdate())"); 

				}
				

			}catch(Exception E) {
				System.out.print(E.toString());
				
			}
				
			
		}
	}
	
	public class WithdrawMoney implements ActionListener{
		JFrame withDrM = new JFrame();
		JLabel L1; 
		JTextField T1; 
		JButton B1,B2; 
		
		ResultSet Rs;
		
		
		WithdrawMoney(){
           T1 = new JTextField(10);
			
			L1 = new JLabel("ACCOUNT NO:");
		
			B1 = new JButton("LOGIN");
			B2 = new JButton("RETURN");
			
			withDrM.setLayout(new GridLayout(1, 1));
			
			withDrM.add(L1); 
			withDrM.add(T1);
			withDrM.add(B1); 
			withDrM.add(B2); 
			
			withDrM.setSize(500,200);
			
			B1.addActionListener(this);
			B2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					withDrNewM.withDrM.setVisible(false);
					Win.setVisible(true);
					
				}
				
			});
			
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
	String currentAccNo = T1.getText(); 
	int amount = 0; 

			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");// Driver
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bankdbs","root","");//Ip adress/name of db/username,password
				Statement dp = con.createStatement();
				Statement St = con.createStatement();
				
				Rs = St.executeQuery("SELECT * FROM bank WHERE ACCNO='" + currentAccNo + "'");
				  
				
				
			
				if(Rs.next()) {
					
				
						
						ResultSet dpStr = dp.executeQuery("SELECT SUM(amount)FROM deposit WHERE ACCNO = '"+ currentAccNo +"'");
						  dpStr.next();
						  int dep = dpStr.getInt(1);
						  
						  ResultSet wdStr = dp.executeQuery("SELECT SUM(amount)FROM withdraw WHERE ACCNO = '"+ currentAccNo +"'");
						  wdStr.next();
						  int with = wdStr.getInt(1);
						  
						  amount = dep - with;
						   if(amount-with > 0) {
							   withdrawtStr = JOptionPane.showInputDialog("DEPOSIT AMOUNT");
						   }
						   else {
							   System.out.println("Not enough cash");
						   }
					St.executeUpdate("insert into withdraw values("+"'"+currentAccNo+"'"+","+"'"+Integer.parseInt(withdrawtStr)+"'"+","+"sysdate())"); 
					}
				
				

			}catch(Exception E) {
				System.out.print(E.toString());
				
			}
		}
	}
	
	public class checkBalance implements ActionListener{
		JLabel L1,L2,L3; 
		JTextField T1; 
		JButton B1,B2; 
		
		ResultSet Rs;
		
		JFrame chkBlnce = new JFrame();
		checkBalance(){
			    T1 = new JTextField(10);
				
				L1 = new JLabel("ACCOUNT NO:");
				L2 = new JLabel("YOUR BALANCE:");
				L3 = new JLabel("0");
			
				B1 = new JButton("LOGIN");
				B2 = new JButton("RETURN");
				
				chkBlnce.setLayout(new GridLayout(4, 4));
				
				chkBlnce.add(L1); 
				chkBlnce.add(T1);
				chkBlnce.add(L2);
				chkBlnce.add(L3); 
				chkBlnce.add(B1); 
				chkBlnce.add(B2); 
				
				chkBlnce.setSize(500,200);
			
				B1.addActionListener(this);
			
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String currentAccNo = T1.getText(); 
			int amount = 0; 

					
					try {
						
						Class.forName("com.mysql.cj.jdbc.Driver");// Driver
						
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bankdbs","root","");//Ip adress/name of db/username,password
						Statement dp = con.createStatement();
						Statement St = con.createStatement();
						
						Rs = St.executeQuery("SELECT * FROM bank WHERE ACCNO='" + currentAccNo + "'");
						  
						
						
					
						if(Rs.next()) {
							

								
								ResultSet dpStr = dp.executeQuery("SELECT SUM(amount)FROM deposit WHERE ACCNO = '"+ currentAccNo +"'");
								  dpStr.next();
								  int dep = dpStr.getInt(1);
								  
								  ResultSet wdStr = dp.executeQuery("SELECT SUM(amount)FROM withdraw WHERE ACCNO = '"+ currentAccNo +"'");
								  wdStr.next();
								  int with = wdStr.getInt(1);
								  
								  amount = dep - with;
								   
								  L3.setText(Integer.toString(amount));
								  
							St.executeUpdate("insert into withdraw values("+"'"+currentAccNo+"'"+","+"'"+Integer.parseInt(withdrawtStr)+"'"+","+"sysdate())"); 
							}
						
						

					}catch(Exception E) {
						System.out.print(E.toString());
						
					}
			
		}
		
	}
	
public static void main(String abc[]) {
		
		
		new BankGUI(); 
		
		
		
	}
}
