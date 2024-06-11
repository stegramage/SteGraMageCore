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
		char[] aux = hide(_mc.encodeMessage(message), _cc.channelToIntegers());
		_cc.integersToChannel(aux);
		_cc.saveChannel(channel);
		notifyObservers();
	}
	
	char[] hide(int[] message, char[] channel) {
		if(message.length > channel.length * 8)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < message.length; i++) {
			if (message[i] == 1)
				channel[i] = Character.toUpperCase(channel[i]);
			else
				channel[i] = Character.toLowerCase(channel[i]);
		}
		
		return channel;
	}
	
	public void unhide(String channel) {
		_cc.openChannel(channel);
		int[] aux = unhide(_cc.channelToIntegers());
		_messageUnhided = _mc.decodeChannel(aux);
		notifyObservers();
	}
	
	int[] unhide(char[] channel) {
		int[] b_mensaje = new int[channel.length];
		
		for(int i = 0; i < channel.length; i++) {
			b_mensaje[i] = extractBit(channel[i]); 	
		}
		
		return b_mensaje;
	}
	
	private int extractBit(char channel) {
		return Character.isUpperCase(channel) ? 1 : 0;
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
