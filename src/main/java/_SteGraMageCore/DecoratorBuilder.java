package _SteGraMageCore;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class DecoratorBuilder<T>{
	
	private Set<Class<?>> _plugins;
	
	public DecoratorBuilder(Set<Class<?>> plugins) {
		_plugins = plugins;
	}
	
	@SuppressWarnings("unchecked")
	public T build(List<String> names) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
				InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		T result = null;
		Class<?> iface = Class.forName(names.get(0)).getInterfaces()[0];
		
		for ( String name : names) {
			Class<?> cls = Class.forName(name);
			if (result == null) 
				result = (T) cls.getDeclaredConstructor().newInstance();
			else
				if (_plugins.contains(cls)) {
					result = (T) cls.getDeclaredConstructor(new Class[] {iface}).newInstance(result);
				}
		}
	
		return result;
	}
}


