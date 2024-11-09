package _SteGraMageCore;

public class SteGraMage<M, C> {
    private Core<M, C> _core;

    public SteGraMage(String command,String... args) {
        _core = new CoreFactory().create(command, args);
    }

    public void run() {
        _core.run();
    }

}
