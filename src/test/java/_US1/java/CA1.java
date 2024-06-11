//package _US1.java;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import org.junit.jupiter.api.Test;
//import _SteGraMageCore.ASCIIMessageCodec;
//import _SteGraMageCore.SteGraMage;
//import resources.MockChannelConverter;
//
//class CA1 {
//
//	@Test
//	void espacioInsuficienteTest() {
//		MockChannelConverter mch = new MockChannelConverter(2);
//		ASCIIMessageCodec mc = new ASCIIMessageCodec();
//		SteGraMage st = new SteGraMage();
//		st.setCodec(mc);
//		st.setConverter(mch);
//				
//		assertThrows(IllegalArgumentException.class, () -> st.hide("hola", "/path/to/nothig"));
//	}
//
//}
