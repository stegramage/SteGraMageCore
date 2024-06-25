package _SteGraMageCore;

import java.util.List;
import java.util.Set;

public class Configurator {
	
	public static void configure(SteGraMage st, Set<Class<?>> plugins, List<String> codecList, List<String> converterList) {
		
		DecoratorBuilder<Codec> codecBuilder = new DecoratorBuilder<Codec>(plugins);
		DecoratorBuilder<Converter> converterBuilder = new DecoratorBuilder<Converter>(plugins);
		
		Converter converter = converterBuilder.buildComponent(converterList);
		Codec codec = codecBuilder.buildComponent(codecList);
		
		st.setConverter(converter);
		st.setCodec(codec);
	}

}
