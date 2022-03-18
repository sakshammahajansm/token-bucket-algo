import java.util.*;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception; 

public class MainClass {

	public static void main(String[] args) {
		try  
        {  
            //create instance of CreateGUI  
            CreateGUI form = new CreateGUI();  
            form.setSize(600,150);
            form.pack();
            form.setLocationRelativeTo(null);  
            form.setVisible(true);  //make form visible to the user 
        }  
        catch(Exception e)  
        {     
            //handle exception
			System.out.println("Kindly restart the program");	
            JOptionPane.showMessageDialog(null, e.getMessage());
        }	
	}
	

}
