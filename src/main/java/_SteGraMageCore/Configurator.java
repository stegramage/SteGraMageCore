package _SteGraMageCore;

import java.util.List;
import java.util.Set;

public class Configurator {
	
	public static <M,C> void configure(Core<M,C> st, Set<Class<?>> plugins, List<String> messageCodecList, List<String> channelCodecList) {
		
		DecoratorBuilder<Codec<M>> messageCodecBuilder = new DecoratorBuilder<Codec<M>>(plugins);
		DecoratorBuilder<Codec<C>> channelCodecBuilder = new DecoratorBuilder<Codec<C>>(plugins);
		
		
		Codec<M> messageCodec = messageCodecBuilder.buildComponent(messageCodecList);
		Codec<C> channelCodec = channelCodecBuilder.buildComponent(channelCodecList);
		
		st.setChannelCodec(channelCodec);
		st.setMessageCodec(messageCodec);
	}

}
