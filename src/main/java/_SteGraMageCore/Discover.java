package _SteGraMageCore;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Discover {
		
    public Set<Class<?>> findClasses(String path) throws ClassNotFoundException, IllegalAccessException, 
    			InstantiationException, NoSuchMethodException, InvocationTargetException, FileNotFoundException {
        
    	Set<Class<?>> result = new HashSet<>();
    	
    	File file = new File(path);
    	
    	if (!file.exists()) throw new FileNotFoundException();

        for (File f : file.listFiles()) {
            if (!f.getName().endsWith(".class")) continue;

            String className = f.getName().replace(".class", "");
            Class<?> cls = Class.forName(className); // No need to prepend package name
                        
            if (!_SteGraMageCore.Codec.class.isAssignableFrom(cls))
                continue;
            
            result.add(cls);
            
        }
        return result;
    }
    
}