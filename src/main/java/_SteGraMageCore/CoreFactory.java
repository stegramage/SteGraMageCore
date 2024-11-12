package _SteGraMageCore;


import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoreFactory  {
    private static Set<Class<?>> DECODERS;
    private static Set<Class<?>> ENCODERS;
    private static String PLUGIN_PATH = "plugins/";

    public <M,C> Core<M,C> create(List<String> encoders, List<String> decoders, String[] args) {
        pluginsCheck();

        if (args.length == 1) {
            return getUnhideCore(encoders, decoders, args);
        }

        return getHideCore(encoders, decoders, args);
    }

    private <M,C> Core<M,C> getHideCore(List<String> encoders, List<String> decoders, String[] args) {
        Encoder<C> channelEncoder;
        Encoder<M> messageEncoder;
        Decoder<C> channelDecoder;
        channelDecoder = new DecoratorBuilder<Decoder<C>>(DECODERS).buildComponent(decoders);
        channelEncoder = new DecoratorBuilder<Encoder<C>>(ENCODERS).buildComponent(encoders);
        messageEncoder = new DecoratorBuilder<Encoder<M>>(ENCODERS).buildComponent(encoders);

        Core<M,C> core = new Core(args[0], args[1]);

        core.setMessageEncoder(messageEncoder);
        core.setChannelEncoder(channelEncoder);
        core.setChannelDecoder(channelDecoder);

        return core;
    }

    private <M,C> Core<M,C> getUnhideCore(List<String> encoders, List<String> decoders, String[] args) {
        Decoder<M> messageDecoder;
        Encoder<C> channelEncoder;
        channelEncoder = new DecoratorBuilder<Encoder<C>>(ENCODERS).buildComponent(encoders);
        messageDecoder = new DecoratorBuilder<Decoder<M>>(DECODERS).buildComponent(decoders);

        Core<M,C> core = new Core(args[0]);

        core.setMessageDecoder(messageDecoder);
        core.setChannelEncoder(channelEncoder);

        return core;
    }

    private void pluginsCheck() {
        if (DECODERS.isEmpty())
            DECODERS = loadPlugins(Decoder.class);
        if (ENCODERS.isEmpty())
            ENCODERS = loadPlugins(Encoder.class);
    }

    private Set<Class<?>> loadPlugins(Class<?> clazz) {
        Discover discover = new Discover();
        Set<Class<?>> plugins = new HashSet<Class<?>>();

        try {
            plugins = discover.findClasses(PLUGIN_PATH, clazz);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return plugins;
    }
}
