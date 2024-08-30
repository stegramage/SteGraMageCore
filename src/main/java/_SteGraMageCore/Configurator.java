package _SteGraMageCore;

import java.util.List;
import java.util.Set;

public class Configurator {
	
	public static <T> void configure(SteGraMage<T> st, Set<Class<?>> plugins, List<String> messageCodecList, List<String> channelCodecList) {
		
		DecoratorBuilder<Codec<T>> messageCodecBuilder = new DecoratorBuilder<Codec<T>>(plugins);
		DecoratorBuilder<Codec<T>> channelCodecBuilder = new DecoratorBuilder<Codec<T>>(plugins);
		
		
		Codec<T> messageCodec = messageCodecBuilder.buildComponent(messageCodecList);
		Codec<T> channelCodec = channelCodecBuilder.buildComponent(channelCodecList);
		
		st.setChannelCodec(channelCodec);
		st.setMessageCodec(messageCodec);
	}

}
