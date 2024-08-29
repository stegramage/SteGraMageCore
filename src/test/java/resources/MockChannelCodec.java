package resources;

import java.util.ArrayList;
import java.util.List;

import _SteGraMageCore.Codec;

public class MockChannelCodec implements Codec<String> {
	
	private char[] _channel_in;
	private char[] _channel_out;
	
	public MockChannelCodec(int size) {
		_channel_in = new char[size];
		fillChannel();
	}

	private void fillChannel() {
		for(int i = 0; i < _channel_in.length; i++) {
			_channel_in[i] = 'q';
		}
	}

	@Override
	public List<Integer> encode(String data) {
		if(data.equals("unhide"))
			_channel_in = _channel_out;
		return _channel_in == null ? null : channelToIntegers();
	}

	private List<Integer> channelToIntegers() {
		List<Integer> ret = new ArrayList<Integer>(_channel_in.length);
		for (int i = 0; i < _channel_in.length; i++)
			ret.add((int) _channel_in[i]);
		return ret;
	}

	@Override
	public String decode(List<Integer> channel) {
		_channel_out = channelToChars(channel);
		
		return _channel_out.toString();
	}

	private char[] channelToChars(List<Integer> channel) {
		char[] ret = new char[channel.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = (char) ((int) channel.get(i));
		return ret;
	}

	public char[] getChannelIn() {
		return _channel_in;
	}

	public char[] getChannelOut() {
		return _channel_out;
	}
	
}
