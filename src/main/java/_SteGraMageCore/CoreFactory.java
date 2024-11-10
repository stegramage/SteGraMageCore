package _SteGraMageCore;

public class CoreFactory {

    public <M, C> Core create(String[] args) {
        if (args.length == 1)
            return new Core(args[0]);
        return new Core(args[0], args[1]);

    }
}
