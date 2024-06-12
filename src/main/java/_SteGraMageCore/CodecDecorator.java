package _SteGraMageCore;

public abstract class CodecDecorator implements Codec {

	private Codec _wrappee;
	
	public CodecDecorator (Codec codec) {
		_wrappee = codec;
	}
	
	@Override
	public int[] encodeMessage(String message) {
		return _wrappee.encodeMessage(message);
	}

	@Override
	public String decodeChannel(int[] channel) {
		return _wrappee.decodeChannel(channel);
	}
	
	public Codec getCodec() {
		return _wrappee;
	}

}
