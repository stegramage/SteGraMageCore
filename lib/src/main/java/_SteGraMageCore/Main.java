package _SteGraMageCore;

public class Main {

	public static void main(String[] args) {
		SteGraMage st = new SteGraMage();
		st.setInterpreter(new ASCIIMessageInterpreter());
		st.setConverter(new PNGConverter());
		
		String msg = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nCurabitur accumsan ultrices elementum.\nCras purus ex, ullamcorper vel condimentum at, interdum non nibh.\nDonec ac tortor enim.\nIn hac habitasse platea dictumst.\nNunc non quam in dolor hendrerit gravida porta ante.";
		st.hide(msg, "image/gato.png");
		
		System.out.println(st.unhide("image/gato_out.png"));

	}

}
