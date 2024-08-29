package _SteGraMageCore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SteGraMage {
	private final int CHANNEL_MESSAGE_RATIO = 8;
	private final int HIDE_MASK = 1;
	private final int UNHIDE_MASK = 0x00000001;

	private Codec<String> _channelConverter;
	private Codec<String> _messageCodec;
	private String _messageUnhided;
	private Set<Observer> _observers;
			
	public SteGraMage() {
		_observers = new HashSet<Observer>();
	}
	
	public void hide(String message, String channel) {
		List<Integer> aux = hide(_messageCodec.encode(message), _channelConverter.encode(channel));
		_channelConverter.decode(aux);
		notifyObservers();
	}
	
	List<Integer> hide(List<Integer> message, List<Integer> channel) {
		if(message.size() > channel.size() * CHANNEL_MESSAGE_RATIO)
			throw new IllegalArgumentException();
		
		int chanAux, msgAux;
		for (int i = 0; i < message.size(); i++) {
			chanAux = channel.get(i);
			msgAux = message.get(i);
			
			chanAux = (chanAux >>> HIDE_MASK);
			chanAux = (chanAux << HIDE_MASK);
			chanAux = (chanAux ^ msgAux);
			
			channel.set(i, chanAux);
		}
		
		return channel;
	}
	
	public void unhide(String channel) {
		List<Integer> aux = unhide(_channelConverter.encode(channel));
		_messageUnhided = _messageCodec.decode(aux);
		notifyObservers();
	}
	
	List<Integer> unhide(List<Integer> channel) {
		if (channel == null)
			throw new IllegalArgumentException("El canal no puede ser nulo");
		List<Integer> b_mensaje = new ArrayList<Integer>(channel.size());
		
		for(int i = 0; i < channel.size(); i++) {
			b_mensaje.set(i, extractBit(channel.get(i))); 	
		}
		
		return b_mensaje;
	}
	
	private int extractBit(int channel) {
		return channel & UNHIDE_MASK;
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
//		
//	public static Set<String> getPlugins() {
//		Set<String> ret = new HashSet<String>();
//		_plugins.forEach(c -> ret.add(c.getName()));;
//		return ret;
//	}
	
	public void setChannelCodec(Codec<String> c) {
		_messageCodec = c;
	}
	
	public void setMessageCodec(Codec<String> c) {
		_messageCodec = c;
	}

}
