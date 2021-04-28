package tv;

public class TV {
	private int channel;	//1~255
	private int volume;		//0~100
	private boolean power;
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	
	public void power(boolean on) {
		
	}
	
	public void channel(boolean up) {
		if(up) {
			
		}
	}
	
	public void channel(int channel) {
		
	}
	
	public void volume(boolean up) {
		
	}
	
	public void volume(int volume) {
		
	}
	
	
	
	public int getChannel() {
		return volume;
	}
	public int getVolueme() {
		return channel;
	}
	public void status() {
		System.out.println("TV[power-on, channel = 11, volume = 20]");
	}
	
}
