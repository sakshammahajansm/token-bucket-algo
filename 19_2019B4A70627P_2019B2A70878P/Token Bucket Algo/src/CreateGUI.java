//import required classes and packages  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception;  
import java.util.ArrayList;
  

//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on the click of button
class CreateGUI extends JFrame implements ActionListener  
{  
    //initialize button, panel, label, and text field  
    JButton b1;  
    JPanel newPanel;  
    JLabel firstLabel, secondLabel;  
    final JTextField  textField1, textField2;
    
    int noOfExternalDevices, noOfInitTokens, packets = 0;
	ArrayList<ExternalDevice> sources = new ArrayList<ExternalDevice>();
	ArrayList<IntermediateDevice> iDeviceThreads = new ArrayList<IntermediateDevice>();
    String dValue;
    String tValue;
    
    CreateGUI()  
    {     
    	//create label 
    	firstLabel = new JLabel();  
        firstLabel.setText("Please enter the number of External Devices (sources):");      //set label value for textField1  
          
        //create text field 
        textField1 = new JTextField(15);    //set length of the text  
  
        //create label 
        secondLabel = new JLabel();  
        secondLabel.setText("Please enter the number of initial tokens in the intermediate device:");      //set label value for textField2  
      
        textField2 = new JTextField(15); 
          
        //create submit button  
        b1 = new JButton("SUBMIT"); //set label to button
          
        //create panel to put form elements  
        newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        newPanel.add(firstLabel);    //set firstlabel to panel  
        newPanel.add(textField1);   //set text field to panel  
        newPanel.add(secondLabel);    //set password label to panel  
        newPanel.add(textField2);   //set text field to panel  
        newPanel.add(b1);           //set button to panel  
        setDefaultCloseOperation(javax.swing.  
	    		WindowConstants.DISPOSE_ON_CLOSE);  
        
        //set border to panel   
        add(newPanel, BorderLayout.CENTER);
        
        //perform action on button click   
        b1.addActionListener(this);     //add action listener to button  
        setTitle("PROJECT 19: TOKEN BUCKET ALGORITHM");         //set title of the form 
    }  
    
    //define abstract method actionPerformed() which will be called on button click   
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {  
    	System.out.println("Processing.....");
    	dValue = textField1.getText();        //get user entered value from the textField1  
        tValue = textField2.getText();        //get user entered value from the textField2  
        super.dispose();
        
        noOfExternalDevices = Integer.parseInt(dValue);
        noOfInitTokens = Integer.parseInt(tValue);
        
    	IntermediateDevice iDevice = new IntermediateDevice(noOfInitTokens);
		
		for(int i = 1; i <= noOfExternalDevices; i++){
			ExternalDevice tempSource = new ExternalDevice(i);

			sources.add(tempSource);
			
			if(packets < IntermediateDevice.memory){
				IntermediateDevice tempDevice = new IntermediateDevice(tempSource);
				iDeviceThreads.add(tempDevice);
				packets += tempSource.getNoOfPackets();
				if(packets > IntermediateDevice.memory){
					int j = packets - IntermediateDevice.memory;
					int modify = tempSource.getNoOfPackets() - j;
					tempSource.setNoOfPackets(modify);
					IntermediateDevice.noOfLost += j;
				}
			} else {
				for(int k = 0; k < tempSource.getNoOfPackets(); k++){
					IntermediateDevice.noOfLost++;
				}
			}
		}
		
		String output = "Transmitting order is as follows:";
	    IntermediateDevice.list.add(output);
	  
		for(int i = 0; i < iDeviceThreads.size(); i++){
			iDeviceThreads.get(i).start();
		}
		
		for(int i = 0; i < iDeviceThreads.size(); i++){
			try{
				iDeviceThreads.get(i).join();
			}catch(Exception e){
				
			}
		}
		
		Runnable r = new Runnable(){  
		    public void run(){	
		      String output = "Number of packets with tokens successfully transmitted through an intermediate device: " + IntermediateDevice.noOfSuccess;
		      IntermediateDevice.list.add(output);
		      output = "Number of packets lost i.e. returned back to source from intermediate device: " + IntermediateDevice.noOfLost;
		      IntermediateDevice.list.add(output);
		    }  
		  };
		  
		 Thread t = new Thread(r);
		 t.start(); 
		 
		 OutputScreen screen = new OutputScreen(IntermediateDevice.list);  
         screen.setSize(200,300);
         screen.pack();
         screen.setLocationRelativeTo(null); 
         screen.setVisible(true);  //make form visible to the user 
    }
}  