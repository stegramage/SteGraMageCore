package _SteGraMageCore;

public interface DataSource<T> {
	
	public T read(String path);
	
	public void write(T data);

}
