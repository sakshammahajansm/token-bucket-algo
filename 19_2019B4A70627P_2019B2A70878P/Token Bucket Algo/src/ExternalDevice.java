import java.lang.Math;

public class ExternalDevice {
	private int deviceNum;
	private int noOfPackets;
	private int sizeOfPacket = 1;
	
	ExternalDevice(int i) {
		this.noOfPackets = (int)(Math.random()*11); //To randomize the number of packets in each source 
//		this.noOfPackets = 10;  //Uncomment this if number of packets have to be kept constant 
		this.deviceNum = i;
	}
	
	public int getDeviceNum() {
		return this.deviceNum;
	}
	
	public int getNoOfPackets() {
		return this.noOfPackets;
	}
	
	public void setNoOfPackets(int i) {
		this.noOfPackets = i;
	}
}

