//package _US1.java;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//import _SteGraMageCore.ASCIIMessageCodec;
//import _SteGraMageCore.SteGraMage;
//import resources.MockChannelConverter;
//
//class CA2 {
//
//	@Test
//	void mensajeVacioTest() {
//		MockChannelConverter mch = new MockChannelConverter(2);
//		ASCIIMessageCodec mc = new ASCIIMessageCodec();
//		SteGraMage st = new SteGraMage();
//		st.setCodec(mc);
//		st.setConverter(mch);
//		
//		st.hide("", "/path/to/nothig");
//		
//		assertArrayEquals(mch.getChannelOut(), mch.getChannelIn());
//	}
//
//}
