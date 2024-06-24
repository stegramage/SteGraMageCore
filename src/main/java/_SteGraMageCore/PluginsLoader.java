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
		Set<Class<?>> codecs = new HashSet<Class<?>>(); 
		Set<Class<?>> converters = new HashSet<Class<?>>();
		try {
			codecs = discover.findClasses(path, Codec.class);
			converters = discover.findClasses(path, Converter.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		codecs.addAll(converters);
		_plugins = codecs;
	}
	
	public Set<Class<?>> getPlugins() {
		return _plugins;
	}

}
