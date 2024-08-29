package _SteGraMageCore;

import java.util.ArrayList;
import java.util.List;

public class Steganographer {
	private final int CHANNEL_MESSAGE_RATIO = 8;
	private final int HIDE_MASK = 1;
	private final int UNHIDE_MASK = 0x00000001;

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
}
