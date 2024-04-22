package _SteGraMageCore;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ASCIIMessageInterpreter implements Interpreter {
	
	@Override
	public byte[] interpretMessage(String message) {
		byte[] aux = new byte[0];
		
		try {
			aux = new String(message).getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(aux.length != 0) {
			byte[] temp = new byte[aux.length + 1];
			for(int i = 0; i < aux.length; i++)	
				temp[i] = aux[i];
			
			temp[aux.length] = (byte) 0x04;
			
			byte[] ret = new byte[temp.length * 8];
					
			for(int i = 0; i < temp.length; i++) {
				ret[(i * 8) + 0] = (byte) ((temp[i] & (byte) 0x01) >>> 0);
				ret[(i * 8) + 1] = (byte) ((temp[i] & (byte) 0x02) >>> 1);
				ret[(i * 8) + 2] = (byte) ((temp[i] & (byte) 0x04) >>> 2);
				ret[(i * 8) + 3] = (byte) ((temp[i] & (byte) 0x08) >>> 3);
				ret[(i * 8) + 4] = (byte) ((temp[i] & (byte) 0x10) >>> 4);
				ret[(i * 8) + 5] = (byte) ((temp[i] & (byte) 0x20) >>> 5);
				ret[(i * 8) + 6] = (byte) ((temp[i] & (byte) 0x40) >>> 6);
				ret[(i * 8) + 7] = (byte) ((temp[i] & (byte) 0x80) >>> 7);
			}
					
			return ret;
			
		} else {
			return aux;
		}
		
	}
	
	@Override
	public String interpretBytes(byte[] channel) {
		ArrayList<Byte> temp = new ArrayList<Byte>();
		
		for(int i = 0; i < channel.length; i+=8) {
			byte[] aux = new byte[8];
			aux[0] = channel[i + 0];
			aux[1] = channel[i + 1];
			aux[2] = channel[i + 2];
			aux[3] = channel[i + 3];
			aux[4] = channel[i + 4];
			aux[5] = channel[i + 5];
			aux[6] = channel[i + 6];
			aux[7] = channel[i + 7];
					
			byte letra = joinBits(aux);
						
			if((letra ^ (byte) 0x04) == 0)
				break;
			else
				temp.add(letra);
		}
		
		byte[] b_message = new byte[temp.size()];
		for(int i = 0; i < b_message.length; i++)
			b_message[i] = temp.get(i);
			
		String ret = new String();
		try {
			ret = new String(b_message, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private byte joinBits(byte[] bits) {
		byte ret = (byte) ((byte) (bits[7] << 7) | (bits[6] << 6) | (bits[5] << 5) | (bits[4] << 4) | (bits[3] << 3) |
				(bits[2] << 2) | (bits[1] << 1) | (bits[0] << 0));
		return ret;
	}

}
