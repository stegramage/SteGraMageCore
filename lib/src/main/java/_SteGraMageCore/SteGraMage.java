package _SteGraMageCore;

public class SteGraMage {

	private Converter _cc;
	private Interpreter _mi;
	
	public void hide(String message, String channel) {
		_cc.openChannel(channel);
		int[] aux = hide(_mi.interpretMessage(message), _cc.channelToIntegers());
		_cc.integersToChannel(aux);
		_cc.saveChannel(channel);
	}
	
	int[] hide(int[] message, int[] channel) {
		if(message.length > channel.length)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < message.length; i++) {
			 channel[i] = (channel[i] >>> 1);
			 channel[i] = (channel[i] << 1);
			 channel[i] = (channel[i] ^ message[i]);
		}
		
		return channel;
	}
	
	public String unhide(String channel) {
		_cc.openChannel(channel);
		int[] aux = unhide(_cc.channelToIntegers());
		return _mi.interpretChannel(aux);
	}
	
	int[] unhide(int[] channel) {
		int[] b_mensaje = new int[channel.length];
		
		for(int i = 0; i < channel.length; i++) {
			b_mensaje[i] = extractBit(channel[i]); 	
		}
		
		return b_mensaje;
	}
	
	private int extractBit(int chanel) {
		return chanel & 0x00000001;
	}

	public void setConverter(Converter cc) {
		_cc = cc;
	}

	public void setInterpreter(Interpreter mi) {
		_mi = mi;
	}
}
