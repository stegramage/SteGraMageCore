package _SteGraMageCore;

public class CoreFactory {

    public <M, C> Core<M,C> create(String command, String[] args) {
        if (args.length < 1 || args.length > 2)
            throw new IllegalArgumentException("Wrong number of arguments");

        if (args.length == 1)
            return new Core(command, args[0],null);
        return new Core(command, args[0], args[1]);

    }
}
