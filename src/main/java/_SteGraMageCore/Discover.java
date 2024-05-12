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
            String className = "_SteGraMageCore." + f.getName().replace(".class", "");
            Class cls = Class.forName(className);

            if (!_SteGraMageCore.Interpreter.class.isAssignableFrom(cls) || cls.isInterface()) {
                System.out.println(cls.getName() + " NO IMPLEMENTA " + _SteGraMageCore.Interpreter.class.getName());
//                throw new RuntimeException();
            } else {
//                result.add(cls);
                // Crea una instancia de cada clase que cumple con la interface Interpreter
                result.add(cls.getDeclaredConstructor().newInstance());
            }
        }
        return result;
    }
}

class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Discover discover = new Discover();
        Set<Object> result = discover.findClasses("bin/main/_SteGraMageCore");
        System.out.println("\nMain");
        System.out.println(result);

        for (Object obj : result) {
            System.out.println(obj.getClass());
        }
    }
}