package _SteGraMageCore;

public class SteGraMage {
    Core<?, ?> _core;

    public SteGraMage(String... args) {
        if (args.length < 1 || args.length > 2)
            throw new IllegalArgumentException("Número de argumentos equivocados");

        Core<?, ?> _core = new CoreFactory().create(args);
    }

    public void run() {
        _core.execute();
    }

}
