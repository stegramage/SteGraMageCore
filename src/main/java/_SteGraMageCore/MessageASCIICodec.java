package _SteGraMageCore;

import java.util.ArrayList;
import java.util.List;

public class MessageASCIICodec implements Codec<String> {
	
	@Override
	public List<Integer> encode(String message) {
		if (message == null)
			throw new IllegalArgumentException("El mensaje no puede ser nulo");
		if(message.equals(""))
			return new ArrayList<Integer>();
		
		char[] aux = message.toCharArray();
		int[] temp = new int[aux.length + 1];
		for(int i = 0; i < aux.length; i++)	
			temp[i] = aux[i];
		
		temp[aux.length] = 0x04; // 0x04 = EOT End Of Transmission
		
		List<Integer> ret = new ArrayList<Integer>(temp.length * 8);
				
		for(int i = 0; i < temp.length; i++) {
			ret.add((temp[i] & 0x01) >>> 0);
			ret.add((temp[i] & 0x02) >>> 1);
			ret.add((temp[i] & 0x04) >>> 2);
			ret.add((temp[i] & 0x08) >>> 3);
			ret.add((temp[i] & 0x10) >>> 4);
			ret.add((temp[i] & 0x20) >>> 5);
			ret.add((temp[i] & 0x40) >>> 6);
			ret.add((temp[i] & 0x80) >>> 7);
		}
				
		return ret;	
	}
	
	@Override
	public String decode(List<Integer> channel) {
		if (channel.size() == 0)
			return "";
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		char[] chars;
		
		for(int i = 0; i < channel.size(); i+=8) {
			if (i + 8 > channel.size())
				throw new UnsupportedOperationException("No se encontro un mensaje en el canal");
			
			int[] aux = new int[8];
			aux[0] = channel.get(i + 0);
			aux[1] = channel.get(i + 1);
			aux[2] = channel.get(i + 2);
			aux[3] = channel.get(i + 3);
			aux[4] = channel.get(i + 4);
			aux[5] = channel.get(i + 5);
			aux[6] = channel.get(i + 6);
			aux[7] = channel.get(i + 7);
					
			int character = joinBits(aux);
			
			temp.add(character);
			
			if((character == 0x04))  // 0x04 = EOT End Of Transmission
				break;
		}
		
		if (temp.get(temp.size()-1) != 0x04)
			throw new UnsupportedOperationException("No se encontro un mensaje en el canal");
		
		chars = new char[temp.size() - 1];
		for(int i = 0; i < chars.length; i++)
			chars[i] = (char) ((int) temp.get(i));
			
		return new String(chars);
	}
	
	private int joinBits(int[] bits) {
		int ret = ((bits[7] << 7) + (bits[6] << 6) + (bits[5] << 5) + (bits[4] << 4) + (bits[3] << 3) + 
				(bits[2] << 2) + (bits[1] << 1) + (bits[0] << 0));
		return ret;
	}

}
