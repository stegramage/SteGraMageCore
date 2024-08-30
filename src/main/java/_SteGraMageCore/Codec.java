package _SteGraMageCore;

import java.util.List;

public interface Codec<T> {

	public List<Integer> encode(T message);
	
	public T decode(List<Integer> data);
	
}
