package _SteGraMageCore;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
public class CodecBuilder<T>{
	
	private Set<Class<?>> _plugins;
	
	public CodecBuilder(Set<Class<?>> plugins) {
		_plugins = plugins;
	}
	
	public T build(List<String> names) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		T result = null;
			for ( String name : names) {
				Class<?> cls = Class.forName(name);
				if (result == null) 
					result = (T) cls.getDeclaredConstructor().newInstance();
				else
					if (_plugins.contains(cls))
						result = (T) cls.getDeclaredConstructor(new Class[] {result.getClass()}).newInstance(result);
			}
		return result;

	}
}


