package _SteGraMageCore;

public interface Observer {

	public <M,C> void update(Core<M,C> st);
}
