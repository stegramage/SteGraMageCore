package _SteGraMageCore;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CodecBuilder {
	
	private boolean _withROT13;
	private boolean _withBase64;
	private Set<Class<?>> _plugins;
	private Codec _codec;
	
	public CodecBuilder(Set<Class<?>> plugins) {
		_codec = new ASCIIMessageCodec();
		_plugins = plugins;
	}
	
	public CodecBuilder withROT13() {
		_withROT13 = true;
		return this;
	}
	
	public CodecBuilder withBase64() {
		_withBase64 = true;
		return this;
	}
	
	public Codec build() {
		if(_withROT13) 
			decorateCodec("_rot13.ROT13");
		
		if(_withBase64) 
			decorateCodec("_base64.Base64");
		
		return _codec;
	}
	
	private void decorateCodec(String name) {
		for (Class<?> cls : _plugins) 
			if (cls.getName().equals(name))
				try {
					_codec = (Codec) cls.getDeclaredConstructor(new Class[] {Codec.class}).newInstance(_codec);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}		
		
	}

}
