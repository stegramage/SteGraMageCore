package _US1.java;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.PluginsLoader;
import _SteGraMageCore.SteGraMage;
import _SteGraMageCore.MessageASCIICodec;
import _SteGraMageCore.TXTChannelCodec;
import _SteGraMageCore.Configurator;
import resources.MockChannelCodec;

class CA1 {

	@Test
	void notEnoughSpaceTest() {
		PluginsLoader loader = new PluginsLoader("plugins/");
		SteGraMage<String, String> stegramage = new SteGraMage<String, String>();
		List<String> codecs = new ArrayList<String>();
		List<String> converters = new ArrayList<String>();
		codecs.add(MessageASCIICodec.class.getName());
		converters.add(TXTChannelCodec.class.getName());
		Configurator.configure(stegramage, loader.getPlugins(), codecs, converters);
		stegramage.setChannelCodec(new MockChannelCodec(2));
				
		assertThrows(IllegalArgumentException.class, () -> stegramage.hide("hola", "/path/to/nothig"));
	}

}
