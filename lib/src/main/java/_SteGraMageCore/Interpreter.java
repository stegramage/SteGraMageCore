package _SteGraMageCore;

public interface Interpreter {

	public byte[] interpretMessage(String message);
	
	public String interpretBytes(byte[] channel);
}
