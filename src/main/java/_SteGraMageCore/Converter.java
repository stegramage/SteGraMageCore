package _SteGraMageCore;

public interface Converter {

	public char[] channelToIntegers();
	
	public void integersToChannel(char[] canal);
	
	public void openChannel(String path);
	
	public void saveChannel(String path);
}
