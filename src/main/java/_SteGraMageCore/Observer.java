package _SteGraMageCore;

public interface Observer {

	public <M,C> void update(SteGraMage<M,C> st);
}
