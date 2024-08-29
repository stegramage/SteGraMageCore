package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.MessageASCIICodec;
import _SteGraMageCore.TXTChannelCodec;
import _SteGraMageCore.Configurator;
import _SteGraMageCore.PluginsLoader;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelCodec;

class CA4 {

	@Test
	void containsAMessageTest() {
		MockChannelCodec mockChannel = new MockChannelCodec(40);
		PluginsLoader loader = new PluginsLoader("plugins/");
		SteGraMage stegramage = new SteGraMage();
		List<String> codecs = new ArrayList<String>();
		List<String> converters = new ArrayList<String>();
		codecs.add(MessageASCIICodec.class.getName());
		converters.add(TXTChannelCodec.class.getName());
		Configurator.configure(stegramage, loader.getPlugins(), codecs, converters);
		stegramage.setChannelCodec(mockChannel);
		stegramage.hide("hola", "/path/to/nothig");
		
		assertTrue(arrayNotEquals(mockChannel.getChannelIn(), mockChannel.getChannelOut()));
	}
	
	private boolean arrayNotEquals(char[] expected, char[] obtained) {
		
		if (expected.length == obtained.length) {
			for (int i = 0; i < expected.length; i++) {
				if (expected[i] != obtained[i])
					return true;
			}
			return false;
		}
		return true;
	}


}
