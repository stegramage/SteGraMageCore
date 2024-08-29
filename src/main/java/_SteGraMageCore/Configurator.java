package _SteGraMageCore;

import java.util.List;
import java.util.Set;

public class Configurator {
	
	public static void configure(SteGraMage st, Set<Class<?>> plugins, List<String> messageCodecList, List<String> channelCodecList) {
		
		DecoratorBuilder<Codec<String>> messageCodecBuilder = new DecoratorBuilder<Codec<String>>(plugins);
		DecoratorBuilder<Codec<String>> channelCodecBuilder = new DecoratorBuilder<Codec<String>>(plugins);
		
		
		Codec<String> messageCodec = messageCodecBuilder.buildComponent(messageCodecList);
		Codec<String> channelCodec = channelCodecBuilder.buildComponent(channelCodecList);
		
		st.setChannelCodec(channelCodec);
		st.setMessageCodec(messageCodec);
	}

}
