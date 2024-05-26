package _SteGraMageCore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Discover {
    public Set<Codec> findClasses(String path) throws ClassNotFoundException, IllegalAccessException, 
    			InstantiationException, NoSuchMethodException, InvocationTargetException, FileNotFoundException {
        
    	Set<Codec> result = new HashSet<>();
    	
    	File file = new File(path);
    	
    	if (!file.exists()) throw new FileNotFoundException();

        for (File f : file.listFiles()) {
            if (!f.getName().endsWith(".jar")) continue;

            Set<Class<?>> classes = getClassesFromJarFile(f);
            
            for (Class<?> cls : classes) {
	            if (_SteGraMageCore.Codec.class.isAssignableFrom(cls) || !cls.isInterface() || !java.lang.reflect.Modifier.isAbstract(cls.getModifiers())) {
	                // Check if the class has a default constructor
	                if (cls.getDeclaredConstructor() != null) {
	                    // Create an instance of the class
	                    Object instance = cls.getDeclaredConstructor().newInstance();
	                    result.add((Codec)instance);
	                }
	            }
            }
        }
        return result;
    }
    
    private Set<Class<?>> getClassesFromJarFile(File jarFile) throws ClassNotFoundException {
        Set<String> classNames = getClassNamesFromJarFile(jarFile);
        Set<Class<?>> classes = new HashSet<>(classNames.size());
        try (URLClassLoader cl = URLClassLoader.newInstance(
               new URL[] { new URL("jar:file:" + jarFile + "!/") })) {
            for (String name : classNames) {
                Class<?> clazz = cl.loadClass(name); // Load the class by its name
                classes.add(clazz);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        return classes;
    }
    
    private Set<String> getClassNamesFromJarFile(File givenFile) {
        Set<String> classNames = new HashSet<>();
        try (JarFile jarFile = new JarFile(givenFile)) {
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName()
                      .replace("/", ".")
                      .replace(".class", "");
                    classNames.add(className);
                }
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return classNames;
    }
}