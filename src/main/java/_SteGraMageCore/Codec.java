package _SteGraMageCore;

import java.util.List;

public interface Codec<T> {

	public List<Integer> encode(T data);
	
	public T decode(List<Integer> data);
	
}
