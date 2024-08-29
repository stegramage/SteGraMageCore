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
		Set<Class<?>> messageCodecs = new HashSet<Class<?>>(); 
		Set<Class<?>> channelCodecs = new HashSet<Class<?>>();
		try {
			messageCodecs = discover.findClasses(path, Codec.class);
			channelCodecs = discover.findClasses(path, Codec.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		messageCodecs.addAll(channelCodecs);
		_plugins = messageCodecs;
	}
	
	public Set<Class<?>> getPlugins() {
		return _plugins;
	}

}
