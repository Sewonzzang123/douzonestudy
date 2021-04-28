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
		if(on) {
			power = true;
		}else {
			power = false;
		}
	}
	
	public void channel(boolean up) {
		if(up) {
			if(channel==255) {
				channel = 1;
			}else {
				channel ++;
			}			
		}else {
			if(channel==1) {
				channel = 255;
			}else {
				channel --;
			}
		}
	}
	
	public void channel(int channel) {
		if(1<=channel && channel<=255 ) {
			this.channel = channel;
		}else {
			return;
		}
	}
	
	public void volume(boolean up) {
		if(up) {
			if(volume==100) {
				volume = 100;
			}else {
				volume ++;
			}
		}else {
			if(volume==1) {
				volume = 1;
			}else {
				volume --;
			}
		}
	}
	
	public void volume(int volume) {
		if(1>volume) {
			this.volume = 1;
		}else if(volume >100){
			this.volume =100;
		}else {
			this.volume = volume;
		}
	}
	
	
	
	public int getChannel() {
		return volume;
	}
	public int getVolueme() {
		return channel;
	}
	
	public void status() {
		if(power) {
			System.out.println("TV[power-on, channel ="+channel+", volume = "+volume+"]");
		}else {
			System.out.println("TV[power-off, channel ="+channel+", volume = "+volume+"]");
		}
		
	}
	
}
