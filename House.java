package exam;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JOptionPane;

public class House{
	
	String filename = "C:\\Users\\acer\\Desktop\\mine.txt";
	
	public House() {
		try {
			int ch = Integer.parseInt(JOptionPane.showInputDialog(null, "[1] Create User \n[2] Log in \n[3] Exit \nEnter: " , "TEST FILE HANDLER", JOptionPane.INFORMATION_MESSAGE));
			switch(ch) {
			case 1:
				create();
				break;
			case 2:
				login();
				break;
			case 3:
				System.out.println("TEST DONE");
				System.exit(0);
				break;
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}
	
	public void create() {
		try {
		Path path = Paths.get(filename.toString());
		OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		
		String username = JOptionPane.showInputDialog(null, "Enter User: " , "CREATE ACCOUNT", JOptionPane.INFORMATION_MESSAGE);
		String password = JOptionPane.showInputDialog(null, "Enter Password: " , "CREATE ACCOUNT", JOptionPane.INFORMATION_MESSAGE);
		
		writer.write(username + "," + password);
		writer.newLine();
		JOptionPane.showMessageDialog(null, "Account saved!");
		writer.close();
		output.close();
		
		new House();
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error! " + e.getMessage());
		}
	}	
	public void login() {
		try {
			Path path = Paths.get(filename.toString());
			InputStream input = Files.newInputStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			String username = JOptionPane.showInputDialog(null, "Enter User: " , "LOGIN", JOptionPane.INFORMATION_MESSAGE);
			String password = JOptionPane.showInputDialog(null, "Enter Password: " , "LOGIN", JOptionPane.INFORMATION_MESSAGE);
			
			String temp = null;
			String user, pass;
			boolean tester = false;
			while((temp=reader.readLine()) != null) {
				String [] account = temp.split(",");
				user = account[0];
				pass = account[1];
				if(username.equals(user) && password.equals(pass)) {
					tester=true;
				}
			}
			if(tester==true) {
				JOptionPane.showMessageDialog(null, "Access granted!");
			}else {
				JOptionPane.showMessageDialog(null, "Access Denied! Invalid user or password");
			}
			reader.close();
			String con = JOptionPane.showInputDialog("Press Enter or Ok to continue..");
			new House();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error! " + e.getMessage());
		}
	}
	
	public static void main(String args[]) {
		new House();
	}
}

