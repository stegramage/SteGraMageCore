package _SteGraMageCore;

public class SteGraMage {

	private Converter _cc;
	private Interpreter _mi;
	
	public void hide(String message, String channel) {
		_cc.openChannel(channel);
		byte[] aux = hide(_mi.interpretMessage(message), _cc.channelToBytes());
		_cc.bytesToChannel(aux);
		_cc.saveChannel(channel);
	}
	
	byte[] hide(byte[] message, byte[] channel) {
		if(message.length > channel.length)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < message.length; i++) {
			 channel[i] = (byte) (channel[i] >>> 1);
			 channel[i] = (byte) (channel[i] << 1);
			 channel[i] = (byte) (channel[i] ^ message[i]);
		}
		
		return channel;
	}
	
	public String unhide(String channel) {
		_cc.openChannel(channel);
		byte[] aux = unhide(_cc.channelToBytes());
		return _mi.interpretBytes(aux);
	}
	
	byte[] unhide(byte[] channel) {
		byte[] b_mensaje = new byte[channel.length];
		
		for(int i = 0; i < channel.length; i++) {
			b_mensaje[i] = extractBit(channel[i]); 	
		}
		
		return b_mensaje;
	}
	
	private byte extractBit(byte chanel) {
		return (byte) (chanel & (byte) 0x01);
	}

	public void setConverter(Converter cc) {
		_cc = cc;
	}

	public void setInterpreter(Interpreter mi) {
		_mi = mi;
	}
}
