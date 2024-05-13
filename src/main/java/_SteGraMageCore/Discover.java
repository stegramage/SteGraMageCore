package _SteGraMageCore;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Discover {
    public Set<Object> findClasses(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Set<Object> result = new HashSet<>();

        for (File f : new File(path).listFiles()) {
            if (!f.getName().endsWith(".class")) continue;

            String className = f.getName().replace(".class", "");
            Class<?> cls = Class.forName(className); // No need to prepend package name

            if (!_SteGraMageCore.Interpreter.class.isAssignableFrom(cls) || cls.isInterface() || java.lang.reflect.Modifier.isAbstract(cls.getModifiers())) {
                System.out.println(cls.getName() + " does not implement or extend _SteGraMageCore.Interpreter interface");
            } else {
                // Check if the class has a default constructor
                if (cls.getDeclaredConstructor() != null) {
                    // Create an instance of the class
                    Object instance = cls.getDeclaredConstructor().newInstance();
                    result.add(instance);
                } else {
                    System.out.println(cls.getName() + " does not have a default constructor");
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Discover discover = new Discover();
        Set<Object> result = discover.findClasses("plugins");

        System.out.println("\nMain");
        System.out.println(result);

        for (Object obj : result) {
            System.out.println(obj.getClass());
        }
    }
}

