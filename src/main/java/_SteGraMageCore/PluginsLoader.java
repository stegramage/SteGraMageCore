package _SteGraMageCore;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class PluginsLoader {
	
	private Set<Class<?>> _plugins;
	
	public PluginsLoader(String path) {
		loadPluginsSet(path);
	}

	private void loadPluginsSet(String path) {
		Discover discover = new Discover();
		Set<Class<?>> messageDecoders = new HashSet<Class<?>>();
		Set<Class<?>> messageEncoders = new HashSet<Class<?>>();
		Set<Class<?>> channelDecoders = new HashSet<Class<?>>();
		Set<Class<?>> channelEncoders = new HashSet<Class<?>>();

		try {
			messageDecoders = discover.findClasses(path, Encoder.class);
			messageEncoders = discover.findClasses(path, Decoder.class);
			channelDecoders = discover.findClasses(path, Encoder.class);
			channelEncoders = discover.findClasses(path, Decoder.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		messageDecoders.addAll(messageDecoders);
		_plugins = messageDecoders;
	}
	
	public Set<Class<?>> getPlugins() {
		return _plugins;
	}

}
