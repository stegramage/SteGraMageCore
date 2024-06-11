//package resources;
//
//import _SteGraMageCore.Converter;
//
//public class MockChannelConverter implements Converter {
//	
//	private int[] _channel_in;
//	private int[] _channel_out;
//	
//	public MockChannelConverter(int size) {
//		_channel_in = new int[size];
//		fillChannel();
//	}
//
//	private void fillChannel() {
//		for(int i = 0; i < _channel_in.length; i++) {
//			_channel_in[i] = 0xff;
//		}
//	}
//
//	@Override
//	public int[] channelToIntegers() {
//		return _channel_in.clone();
//	}
//
//	@Override
//	public void integersToChannel(int[] channel) {
//		_channel_out = channel;
//	}
//
//	@Override
//	public void openChannel(String path) {
//		if(path.equals("unhide"))
//			_channel_in = _channel_out;
//	}
//
//	@Override
//	public void saveChannel(String path) {
//	}
//
//	public int[] getChannelIn() {
//		return _channel_in;
//	}
//
//	public int[] getChannelOut() {
//		return _channel_out;
//	}
//	
//	
//
//}
