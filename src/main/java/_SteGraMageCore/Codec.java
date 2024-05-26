package _SteGraMageCore;

public interface Codec {

	public int[] encodeMessage(String message);
	
	public String decodeChannel(int[] channel);
}
