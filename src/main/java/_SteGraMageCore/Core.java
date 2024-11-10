package _SteGraMageCore;

import java.util.HashSet;
import java.util.Set;

public class Core<M, C> {

	private Encoder<M> _messageEncoder;
	private Decoder<M> _messageDecoder;
	private Encoder<C> _channelEncoder;
	private Decoder<C> _channelDecoder;
	private M _message;
	private C _channel;
	private M _messageUnhided;
	private Steganographer _st;
	private Set<Observer> _observers;

	public Core(C channel) {
		_message = null;
		_channel = channel;
		_st = new Steganographer();
		_observers = new HashSet<Observer>();
	}

	public Core(M message, C channel) {
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
		for (Observer obs : _observers) {
			obs.update(this);
		}

	}

	public void execute() {
		if (_message != null)
			hide();
		else
			unhide();
	}

	public void setMessageDecoder(Decoder<M> messageDecoder) {
		_messageDecoder = messageDecoder;
	}

	public void setMessageEncoder(Encoder<M> messageEncoder) {
		_messageEncoder = messageEncoder;
	}

	public void setChannelDecoder(Decoder<C> channelDecoder) {
		_channelDecoder = channelDecoder;
	}

	public void setChannelEncoder(Encoder<C> channelEncoder) {
		_channelEncoder = channelEncoder;
	}

}
