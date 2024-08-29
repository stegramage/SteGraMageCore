package _SteGraMageCore;

import java.util.ArrayList;
import java.util.List;

public class TXTChannelCodec implements Codec<String> {

	private String _channel;
	
		
	@Override
	public List<Integer> encode(String data) {
		_channel = data;
		
		return convertToIntegers(_channel);
	}

	private List<Integer> convertToIntegers(String data) {
		List<Integer> ret = new ArrayList<Integer>(data.length());
		for(int i = 0; i < data.length(); i++)
			ret.add((int) data.charAt(i));
		
		return ret;
	}

	@Override
	public String decode(List<Integer> data) {
		_channel = convertToString(data);
		
		return _channel;	
	}

	private String convertToString(List<Integer> data) {
		char[] aux = new char[data.size()];
		for(int i = 0; i < aux.length; i++)
			aux[i] = (char) ((int) data.get(i));
		
		return String.copyValueOf(aux);
	}

}
