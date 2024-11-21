package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;
	
	public TV(int channel, int volume, boolean power) {
		this.channel=channel;
		this.volume=volume;
		this.power=power;
	}
	
	public TV() {
		this(1,0,false);
	}
	
	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}
	
	public void power(boolean on) {
		this.power=on;
	}
	
	public void channel(int channel) {
		if(channel>255) {
			this.channel=1;
		} else if(channel<1) {
			this.channel=255;
		} else {
			this.channel=channel;
		}
	}
	
	public void channel(boolean up) {
		if(up) {
			this.channel+=1;
		} else {
			this.channel-=1;
		}		
		this.channel(channel);
	}
	
	public void volume(int volume) {
		if(volume>100) {
			this.volume=0;
		} else if(volume<0) {
			this.volume=100;
		} else {
			this.volume=volume;
		}
	}
	
	public void volume(boolean up) {
		if(up) {
			this.volume+=1;
		} else {
			this.volume-=1;
		}
		this.volume(volume);
	}

	public void status() {
		System.out.println("TV[channel=" + channel + ", volume=" + volume + ", power=" + (power?"on":"off") + "]");
	}
	
	// channel: 1~255 (256이상은 1로)
	// volume: 0~100 (동일)
}
