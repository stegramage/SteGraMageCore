package _SteGraMageCore;

import java.util.List;

public abstract class CodecDecorator<T> implements Codec<T> {

	private Codec<T> _wrappee;
	
	public CodecDecorator (Codec<T> codec) {
		_wrappee = codec;
	}
	
	@Override
	public List<Integer> encode(T message) {
		return _wrappee.encode(message);
	}

	@Override
	public T decode(List<Integer> channel) {
		return _wrappee.decode(channel);
	}
	
	public Codec<T> getCodec() {
		return _wrappee;
	}

}
