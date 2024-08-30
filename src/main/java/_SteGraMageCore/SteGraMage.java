package _SteGraMageCore;

import java.util.HashSet;
import java.util.Set;

public class SteGraMage<T> {
	
	private Codec<T> _channelCodec;
	private Codec<T> _messageCodec;
	private T _messageUnhided;
	private Steganographer _st;
	private Set<Observer> _observers;
			
	public SteGraMage() {
		_st = new Steganographer();
		_observers = new HashSet<Observer>();
	}
	
	public void hide(T message, T channel) {
		_channelCodec.decode(
				_st.hide(_messageCodec.encode(message),
						_channelCodec.encode(channel))
		);
		notifyObservers();
	}
		
	public void unhide(T channel) {
		_messageUnhided = _messageCodec.decode(
				_st.unhide(_channelCodec.encode(channel))
		);
		notifyObservers();
	}
	
	public T getMessageUnhided() {
		return _messageUnhided;
	}
	
	public void clearMessageUnhided() {
		_messageUnhided = null;
	}
	
	public void register(Observer obs) {
		_observers.add(obs);
	}
	
	private void notifyObservers() {
		for(Observer obs : _observers) {
			obs.update(this);
		}
			
	}
	
	public void setChannelCodec(Codec<T> c) {
		_channelCodec = c;
	}
	
	public void setMessageCodec(Codec<T> c) {
		_messageCodec = c;
	}

}
