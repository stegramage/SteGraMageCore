package _SteGraMageCore;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SteGraMage {

	private Converter _cc;
	private Codec _mc;
	private List<String> _codecList, _converterList;
	private static String _path;
	private static Set<Class<?>> _plugins;
	private String _messageUnhided;
	private static Set<Observer> _observers;
	
	public static void loadPlugins(String path) {
		_path = path;
		loadPluginsSet();
	}

	private static void loadPluginsSet() {
		Discover dis = new Discover();
		Set<Class<?>> pCod = new HashSet<Class<?>>(); 
		Set<Class<?>> pCon = new HashSet<Class<?>>();
		try {
			pCod = dis.findClasses(_path, Codec.class);
			pCon = dis.findClasses(_path, Converter.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		pCod.addAll(pCon);
		_plugins = pCod;
	}
	
	public static SteGraMage defaultInstance() {
		SteGraMage ret = new SteGraMage();
		ret.configure(new ArrayList<String>(), new ArrayList<String>());
		return ret;
	}
	
	public static SteGraMage createInstance(List<String> codecList) {
		SteGraMage ret = new SteGraMage();
		ret.configure(codecList, new ArrayList<String>());
		return ret;
	}
	
	private SteGraMage() {
		if (_observers == null)
			_observers = new HashSet<Observer>();
		defaultCodecList();
		defaultConverterList();
	}
	
	private void configure(List<String> codecList, List<String> converterList) {
		if (_plugins == null) {
			loadPluginsSet();
		}
		
		if (_cc != null || _mc != null) {
			defaultCodecList();
			defaultConverterList();
		}
		
		addCodecDecoratorsToList(codecList);
		addConverterDecoratorsToList(converterList);
		
		DecoratorBuilder<Codec> codecBuilder = new DecoratorBuilder<Codec>(_plugins);
		DecoratorBuilder<Converter> converterBuilder = new DecoratorBuilder<Converter>(_plugins);
		
		_cc = converterBuilder.buildComponent(_converterList);
		_mc = codecBuilder.buildComponent(_codecList);
	}
	
	private void addConverterDecoratorsToList(List<String> decoratorsList) {
		addDecoratorsToList(_converterList, decoratorsList);
	}

	private void addDecoratorsToList(List<String> components, List<String> decorators) {
		components.addAll(decorators);		
	}

	private void addCodecDecoratorsToList(List<String> decoratorsList) {
		addDecoratorsToList(_codecList, decoratorsList);		
	}

	private void defaultCodecList() {
		_converterList = new ArrayList<String>();
		_converterList.add("_SteGraMageCore.ChannelConverter");
	}

	private void defaultConverterList() {
		_codecList = new ArrayList<String>();
		_codecList.add("_SteGraMageCore.ASCIIMessageCodec");
	}
	
	public void hide(String message, String channel) {
		_cc.openChannel(channel);
		char[] aux = hide(_mc.encodeMessage(message), _cc.channelToIntegers());
		_cc.integersToChannel(aux);
		_cc.saveChannel(channel);
		notifyObservers();
	}
	
	char[] hide(int[] message, char[] channel) {
		if(message.length > channel.length * 8)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < message.length; i++) {
			if (message[i] == 1)
				channel[i] = Character.toUpperCase(channel[i]);
			else
				channel[i] = Character.toLowerCase(channel[i]);
		}
		
		return channel;
	}
	
	public void unhide(String channel) {
		_cc.openChannel(channel);
		int[] aux = unhide(_cc.channelToIntegers());
		_messageUnhided = _mc.decodeChannel(aux);
		notifyObservers();
	}
	
	int[] unhide(char[] channel) {
		int[] b_mensaje = new int[channel.length];
		
		for(int i = 0; i < channel.length; i++) {
			b_mensaje[i] = extractBit(channel[i]); 	
		}
		
		return b_mensaje;
	}
	
	private int extractBit(char channel) {
		return Character.isUpperCase(channel) ? 1 : 0;
	}

	public String getMessageUnhided() {
		return _messageUnhided;
	}
	
	public void clearMessageUnhided() {
		_messageUnhided = "";
	}
	
	public void register(Observer obs) {
		_observers.add(obs);
	}
	
	private void notifyObservers() {
		for(Observer obs : _observers) {
			obs.update(this);
		}
			
	}
		
	public static Set<String> getPlugins() {
		Set<String> ret = new HashSet<String>();
		_plugins.forEach(c -> ret.add(c.getName()));;
		return ret;
	}
	
	public void setConverter(Converter c) {
		_cc = c;
	}

}
