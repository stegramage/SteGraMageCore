package _SteGraMageCore;

import java.util.HashSet;
import java.util.Set;

public class SteGraMage<M, C> {
	
	private Codec<M> _messageCodec;
	private Codec<C> _channelCodec;
	private M _messageUnhided;
	private Steganographer _st;
	private Set<Observer> _observers;
			
	public SteGraMage() {
		_st = new Steganographer();
		_observers = new HashSet<Observer>();
	}
	
	public void hide(M message, C channel) {
		_channelCodec.decode(
				_st.hide(_messageCodec.encode(message),
						_channelCodec.encode(channel))
		);
		notifyObservers();
	}
		
	public void unhide(C channel) {
		_messageUnhided = _messageCodec.decode(
				_st.unhide(_channelCodec.encode(channel))
		);
		notifyObservers();
	}
	
	public M getMessageUnhided() {
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
	
	public void setChannelCodec(Codec<C> c) {
		_channelCodec = c;
	}
	
	public void setMessageCodec(Codec<M> c) {
		_messageCodec = c;
	}

}
