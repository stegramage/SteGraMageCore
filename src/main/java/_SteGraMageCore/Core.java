package _SteGraMageCore;

import java.util.HashSet;
import java.util.Set;

public class Core<M, C> {
	
	private Encoder<M> _messageEncoder;
	private Decoder<M> _messageDecoder;
	private Encoder<C> _channelEncoder;
	private Decoder<C> _channelDecoder;
	private String _command;
	private M _message;
	private C _channel;
	private M _messageUnhided;
	private Steganographer _st;
	private Set<Observer> _observers;
			
	Core(String command, M message, C channel) {
		_command = command;
		_message = message;
		_channel = channel;
		_st = new Steganographer();
		_observers = new HashSet<Observer>();
	}
	
	public void hide() {
		_channelDecoder.decode(
				_st.hide(_messageEncoder.encode(_message),
						_channelEncoder.encode(_channel))
		);
		notifyObservers();
	}
		
	public void unhide() {
		_messageUnhided = _messageDecoder.decode(
				_st.unhide(_channelEncoder.encode(_channel))
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

	public void run() {
		if (_command.equals("hide"))
			hide();
		if (_command.equals("unhide"))
			unhide();
	}
	
//	public void setChannelCodec(Codec<C> c) {
//		_channelCodec = c;
//	}
	
//	public void setMessageCodec(Codec<M> c) {
//		_messageCodec = c;
//	}

}
