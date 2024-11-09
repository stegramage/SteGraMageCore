package _SteGraMageCore;

import java.util.ArrayList;
import java.util.List;

public class ChannelCodec implements Encoder<String>, Decoder<String> {

	private String _channel;
		
	@Override
	public Data encode(String data) {
		_channel = data;
		
		return covertToData(_channel);
	}

	private Data covertToData(String info) {
		List<Integer> ret = new ArrayList<Integer>(info.length());
		for(int i = 0; i < info.length(); i++)
			ret.add((int) info.charAt(i));
		Data data = new Data();
		data.setInfo(ret);
		
		return data;
	}

	@Override
	public String decode(Data data) {
		_channel = convertToString(data);
		
		return _channel;	
	}

	private String convertToString(Data data) {
		char[] aux = new char[data.size()];
		for(int i = 0; i < aux.length; i++)
			aux[i] = (char) ((int) data.get(i));
		
		return String.copyValueOf(aux);
	}

}
