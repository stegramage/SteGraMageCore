//package _US2.java;
//
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//
//import _SteGraMageCore.Discover;
//import _SteGraMageCore.Codec;
//import resources.Assert;
//
//class CA5 {
//
//	@Test
//	void multiInterpreterTest() {
//Discover dis = new Discover();
//		
//		Set<Codec> result = null;
//		try {
//			result = dis.findClasses("plugins/interpretesMultiples");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		String[] expected = {"_rot13.ROT13", "_base64.Base64"};
//		
//		Assert.equals(expected, result);
//	}
//
//}
