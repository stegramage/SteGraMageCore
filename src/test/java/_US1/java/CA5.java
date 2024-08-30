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

class CA5 {

	@Test
	void extractMessageTest() {
		String message = "hola";
		MockChannelCodec mockChannel = new MockChannelCodec(40);
		PluginsLoader loader = new PluginsLoader("plugins/");
		SteGraMage<String> stegramage = new SteGraMage<String>();
		List<String> codecs = new ArrayList<String>();
		List<String> converters = new ArrayList<String>();
		codecs.add(MessageASCIICodec.class.getName());
		converters.add(TXTChannelCodec.class.getName());
		Configurator.configure(stegramage, loader.getPlugins(), codecs, converters);
		stegramage.setChannelCodec(mockChannel);
		
		stegramage.hide(message, "/path/to/nothig");
		stegramage.unhide("unhide");
		
		assertEquals(message, stegramage.getMessageUnhided());
	}

}
