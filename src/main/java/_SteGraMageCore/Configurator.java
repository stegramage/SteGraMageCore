package _SteGraMageCore;

import java.util.List;
import java.util.Set;

public class Configurator {
	
	public static <M,C> void configure(Core<M,C> core, Set<Class<?>> plugins, List<String> messageCodecList, List<String> channelCodecList) {
		
		DecoratorBuilder<Decoder<M>> messageDecoderBuilder = new DecoratorBuilder<Decoder<M>>(plugins);
		DecoratorBuilder<Encoder<M>> messageEncoderBuilder = new DecoratorBuilder<Encoder<M>>(plugins);
		DecoratorBuilder<Decoder<C>> channelDecoderBuilder = new DecoratorBuilder<Decoder<C>>(plugins);
		DecoratorBuilder<Encoder<C>> channelEncoderBuilder = new DecoratorBuilder<Encoder<C>>(plugins);

		Decoder<M> messageDecoder = messageDecoderBuilder.buildComponent(messageCodecList);
		Encoder<M> messageEncoder = messageEncoderBuilder.buildComponent(messageCodecList);
		Decoder<C> channelDecoder = channelDecoderBuilder.buildComponent(channelCodecList);
		Encoder<C> channelEncoder = channelEncoderBuilder.buildComponent(channelCodecList);
		
		core.setMessageDecoder(messageDecoder);
		core.setMessageEncoder(messageEncoder);
		core.setChannelDecoder(channelDecoder);
		core.setChannelEncoder(channelEncoder);
	}

}
