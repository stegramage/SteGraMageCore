package _US1.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.ASCIIMessageCodec;
import _SteGraMageCore.ChannelConverter;
import _SteGraMageCore.Configurator;
import _SteGraMageCore.PluginsLoader;
import _SteGraMageCore.SteGraMage;
import resources.MockChannelConverter;

class CA7 {

	@Test
	void extractMessageFromChannelWithoutMessageTest() {
		MockChannelConverter mockChannel = new MockChannelConverter(40);
		PluginsLoader loader = new PluginsLoader("plugins/");
		SteGraMage stegramage = new SteGraMage();
		List<String> codecs = new ArrayList<String>();
		List<String> converters = new ArrayList<String>();
		codecs.add(ASCIIMessageCodec.class.getName());
		converters.add(ChannelConverter.class.getName());
		Configurator.configure(stegramage, loader.getPlugins(), codecs, converters);
		stegramage.setConverter(mockChannel);
		stegramage.hide("", "/path/to/nothing");
		
		assertThrows(UnsupportedOperationException.class, () -> stegramage.unhide("unhide"));
	}

}
