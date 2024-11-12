package _SteGraMageCore;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SteGraMage {
    Core<?, ?> _core;

    public SteGraMage(List<String> encoders, List<String> decoders, String... args) {
        if (args.length < 1 || args.length > 2)
            throw new IllegalArgumentException("Número de argumentos equivocados");

        Core<?, ?> _core = new CoreFactory().create(encoders, decoders, args);
    }

    public void run() {
        _core.execute();
    }

}
