package _SteGraMageCore;

import java.util.ArrayList;

public class ASCIIMessageCodec implements Codec {
	
	@Override
	public int[] encodeMessage(String message) {
		if(message.equals(""))
			return new int[0];
		
		char[] aux = message.toCharArray();
		int[] temp = new int[aux.length + 1];
		for(int i = 0; i < aux.length; i++)	
			temp[i] = aux[i];
		
		temp[aux.length] = 0xff;
		
		int[] ret = new int[temp.length * 8];
				
		for(int i = 0; i < temp.length; i++) {
			ret[(i * 8) + 0] = ((temp[i] & 0x01) >>> 0);
			ret[(i * 8) + 1] = ((temp[i] & 0x02) >>> 1);
			ret[(i * 8) + 2] = ((temp[i] & 0x04) >>> 2);
			ret[(i * 8) + 3] = ((temp[i] & 0x08) >>> 3);
			ret[(i * 8) + 4] = ((temp[i] & 0x10) >>> 4);
			ret[(i * 8) + 5] = ((temp[i] & 0x20) >>> 5);
			ret[(i * 8) + 6] = ((temp[i] & 0x40) >>> 6);
			ret[(i * 8) + 7] = ((temp[i] & 0x80) >>> 7);
		}
				
		return ret;	
	}
	
	@Override
	public String decodeChannel(int[] channel) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		char[] chars;
		
		for(int i = 0; i < channel.length; i+=8) {
			int[] aux = new int[8];
			aux[0] = channel[i + 0];
			aux[1] = channel[i + 1];
			aux[2] = channel[i + 2];
			aux[3] = channel[i + 3];
			aux[4] = channel[i + 4];
			aux[5] = channel[i + 5];
			aux[6] = channel[i + 6];
			aux[7] = channel[i + 7];
					
			int character = joinBits(aux);
						
			if((character == 0xff)) {
				break;
			}
			else {
				temp.add(character);
			}
		}
		chars = new char[temp.size()];
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
