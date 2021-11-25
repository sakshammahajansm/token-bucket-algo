import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputScreen extends JFrame{
	    private JTextArea textArea; 
	    
	    private ArrayList<String> list = new ArrayList<String>();

	    OutputScreen(ArrayList<String> s){
	    	this.list = s;
		    setDefaultCloseOperation(javax.swing.  
		    		WindowConstants.DISPOSE_ON_CLOSE);  //To close the window on clicking 'X'
		    	        setTitle("Output");    	        
		    	        
	        textArea = new JTextArea(600,150); 
	        textArea.setEditable(false);
	        
	        //To append the data into the list which is to be shown on the output screen
	        for(int i=0; i<list.size();i++) {
	        	textArea.append(list.get(i) + "\n");
	        }
	        
	        //For creating the scrollable output screen
	        JScrollPane temp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        add(temp);
	    }
	    
}
