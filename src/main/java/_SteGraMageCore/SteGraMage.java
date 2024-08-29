package _SteGraMageCore;

import java.util.HashSet;
import java.util.Set;

public class SteGraMage {
	
	private Codec<String> _channelCodec;
	private Codec<String> _messageCodec;
	private String _messageUnhided;
	private Steganographer _st;
	private Set<Observer> _observers;
			
	public SteGraMage() {
		_st = new Steganographer();
		_observers = new HashSet<Observer>();
	}
	
	public void hide(String message, String channel) {
		_channelCodec.decode(
				_st.hide(_messageCodec.encode(message),
						_channelCodec.encode(channel))
		);
		notifyObservers();
	}
		
	public void unhide(String channel) {
		_messageUnhided = _messageCodec.decode(
				_st.unhide(_channelCodec.encode(channel))
		);
		notifyObservers();
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
		for(Observer obs : _observers) {
			obs.update(this);
		}
			
	}
	
	public void setChannelCodec(Codec<String> c) {
		_messageCodec = c;
	}
	
	public void setMessageCodec(Codec<String> c) {
		_messageCodec = c;
	}

}
