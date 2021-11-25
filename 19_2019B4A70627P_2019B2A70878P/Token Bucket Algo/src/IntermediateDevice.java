import java.util.ArrayList;

class IntermediateDevice extends Thread {
	
	//different attributes of the Intermediate Device
    static long noOfInitialTokens;
    static long noOfTokens;
    static long rate = 250;
    static long capacity = 500;
    static long noOfSuccess;
    static long noOfLost;
    static ArrayList<String> list = new ArrayList<String>();
    private double timePassed;
    static int memory = 1000;
    private ExternalDevice device;
   
    IntermediateDevice(ExternalDevice device){
        this.device = device;
    }
   
    IntermediateDevice(long tokens){
        noOfInitialTokens = tokens;
        noOfTokens = noOfInitialTokens;
    }
   
    void setTokens(double t){  	
	    if (noOfTokens < capacity) {
	    	long temp = (long) (rate*t);
	        noOfTokens = noOfTokens + temp; //Total tokens = c + r*t; c: initial, r: rate, t: time
	    } else {
	    	noOfTokens = capacity;
	    }
    }
    
    //major part of the synchronization mechanism
    synchronized void transmit(ExternalDevice device){
        for(int i=1; i<=device.getNoOfPackets(); i++){
        	//transmit the packet only if there are tokens in the Intermediate Device
            if(noOfTokens>0){
	           String output= "Packet " + i + " from source " + device.getDeviceNum() + " transmitted";
	           list.add(output);
	           noOfSuccess = noOfSuccess + 1;
	           noOfTokens = noOfTokens - 1;
            }else{
                noOfLost = noOfLost + 1;
            }    
        }
    }
   
    @Override
    public void run(){
    	   long start = System.nanoTime();
           transmit(device);
           long end = System.nanoTime();
           long elapsedTime = end - start;
           double time = (double)elapsedTime / 1000000000; //division by 10^9 to convert from nanoseconds to seconds
           timePassed += time;
           setTokens(Math.floor(timePassed));
    }
}