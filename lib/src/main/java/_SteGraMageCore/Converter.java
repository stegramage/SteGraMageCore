package _SteGraMageCore;

public interface Converter {

	public byte[] channelToBytes();
	
	public void bytesToChannel(byte[] canal);
	
	public void openChannel(String path);
	
	public void saveChannel(String path);
}
