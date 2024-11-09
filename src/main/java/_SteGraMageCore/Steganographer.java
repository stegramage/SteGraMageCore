package _SteGraMageCore;

import java.util.ArrayList;
import java.util.List;

class Steganographer {
	private final int CHANNEL_MESSAGE_RATIO = 8;
	private final int HIDE_MASK = 1;
	private final int UNHIDE_MASK = 0x00000001;

	Data hide(Data message, Data channel) {
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
	
	Data unhide(Data channel) {
		if (channel == null)
			throw new IllegalArgumentException("El canal no puede ser nulo");
		List<Integer> bitMessage = new ArrayList<Integer>(channel.size());
		
		for(int i = 0; i < channel.size(); i++) {
			bitMessage.add(extractBit(channel.get(i)));
		}

		Data data = new Data();
		data.setInfo(bitMessage);

		return data;
	}
	
	private int extractBit(int channel) {
		return channel & UNHIDE_MASK;
	}
}
