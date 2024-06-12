package _SteGraMageCore;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class DecoratorBuilder<T>{
	
	private Set<Class<?>> _plugins;
	private Class<?> _iface;
	
	public DecoratorBuilder(Set<Class<?>> plugins) {
		_plugins = plugins;
	}
	
	public T buildComponent(List<String> names) {		
		T component = null;
		
		try {
			_iface = getComponentInterface(names.get(0));
			
			for ( String name : names) {
				Class<?> cls = Class.forName(name);
				if (component == null)
					component = getConcretComponent(cls);
				else {
					if (_plugins.contains(cls)) {
						component = decorateComponent(component, cls);
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		return component;
	}

	private Class<?> getComponentInterface(String name) throws ClassNotFoundException {
		return Class.forName(name).getInterfaces()[0];
	}

	@SuppressWarnings("unchecked")
	private T getConcretComponent(Class<?> cls)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		return (T) cls.getDeclaredConstructor().newInstance();
	}
	
	@SuppressWarnings("unchecked")
	private T decorateComponent(T component, Class<?> cls)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return (T) cls.getDeclaredConstructor(new Class[] {_iface}).newInstance(component);
	}
	
}


