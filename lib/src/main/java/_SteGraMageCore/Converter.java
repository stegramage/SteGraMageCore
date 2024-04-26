package _SteGraMageCore;

public interface Converter {

	public int[] channelToIntegers();
	
	public void integersToChannel(int[] canal);
	
	public void openChannel(String path);
	
	public void saveChannel(String path);
}
