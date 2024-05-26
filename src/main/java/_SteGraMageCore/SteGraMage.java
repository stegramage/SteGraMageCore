package _SteGraMageCore;

import java.util.HashSet;
import java.util.Set;

public class SteGraMage {

	private Converter _cc;
	private Codec _mc;
	private String _messageUnhided;
	private Set<Observer> _observers;
	
	public SteGraMage() {
		_observers = new HashSet<Observer>();
	}
	
	public void hide(String message, String channel) {
		_cc.openChannel(channel);
		int[] aux = hide(_mc.encodeMessage(message), _cc.channelToIntegers());
		_cc.integersToChannel(aux);
		_cc.saveChannel(channel);
		notifyObservers();
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
	
	public void unhide(String channel) {
		_cc.openChannel(channel);
		int[] aux = unhide(_cc.channelToIntegers());
		_messageUnhided = _mc.decodeChannel(aux);
		notifyObservers();
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

	public String getMessageUnhided() {
		return _messageUnhided;
	}
	
	public void clearMessageUnhided() {
		_messageUnhided = "";
	}
	
	public void register(Observer obs) {
		_observers.add(obs);
	}
	
	private void notifyObservers() {
		for(Observer obs : _observers)
			obs.update(this);
	}
		
	public void setConverter(Converter cc) {
		_cc = cc;
	}

	public void setCodec(Codec mc) {
		_mc = mc;
	}
	
	public void configure(Converter c, Codec i) {
		_cc = c;
		_mc = i;
	}
}
