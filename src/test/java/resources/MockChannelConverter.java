package resources;

import _SteGraMageCore.Converter;

public class MockChannelConverter implements Converter {
	
	private char[] _channel_in;
	private char[] _channel_out;
	
	public MockChannelConverter(int size) {
		_channel_in = new char[size];
		fillChannel();
	}

	private void fillChannel() {
		for(int i = 0; i < _channel_in.length; i++) {
			_channel_in[i] = 'q';
		}
	}

	@Override
	public char[] channelToIntegers() {
		return _channel_in.clone();
	}

	@Override
	public void integersToChannel(char[] channel) {
		_channel_out = channel;
	}

	@Override
	public void openChannel(String path) {
		if(path.equals("unhide"))
			_channel_in = _channel_out;
	}

	@Override
	public void saveChannel(String path) {
	}

	public char[] getChannelIn() {
		return _channel_in;
	}

	public char[] getChannelOut() {
		return _channel_out;
	}
	
	

}
