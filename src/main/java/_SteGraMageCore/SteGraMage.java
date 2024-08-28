package _SteGraMageCore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SteGraMage {

	private Converter _channelConverter;
	private Codec<String> _messageCodec;
	private String _messageUnhided;
	private Set<Observer> _observers;
			
	public SteGraMage() {
		_observers = new HashSet<Observer>();
	}
	
	public void hide(String message, String channel) {
		_channelConverter.openChannel(channel);
		char[] aux = hide(_messageCodec.encode(message), _channelConverter.channelToIntegers());
		_channelConverter.integersToChannel(aux);
		_channelConverter.saveChannel(channel);
		notifyObservers();
	}
	
	char[] hide(List<Integer> message, char[] channel) {
		if(message.size() > channel.length * 8)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < message.size(); i++) {
			if (message.get(i) == 1)
				channel[i] = Character.toUpperCase(channel[i]);
			else
				channel[i] = Character.toLowerCase(channel[i]);
		}
		
		return channel;
	}
	
	public void unhide(String channel) {
		_channelConverter.openChannel(channel);
		List<Integer> aux = unhide(_channelConverter.channelToIntegers());
		_messageUnhided = _messageCodec.decode(aux);
		notifyObservers();
	}
	
	List<Integer> unhide(char[] channel) {
		if (channel == null)
			throw new IllegalArgumentException("El canal no puede ser nulo");
		List<Integer> b_mensaje = new ArrayList<Integer>(channel.length);
		
		for(int i = 0; i < channel.length; i++) {
			b_mensaje.set(i, extractBit(channel[i])); 	
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
	
	public void setConverter(Converter c) {
		_channelConverter = c;
	}
	
	public void setCodec(Codec<String> c) {
		_messageCodec = c;
	}

}
