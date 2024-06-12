//package _US2.java;
//
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//
//import _SteGraMageCore.Discover;
//import resources.Assert;
//
//class CA4 {
//
//	@Test
//	void oneInterpreterTest() {
//		Discover dis = new Discover();
//		
//		Set<Class<?>> result = null;
//		try {
//			result = dis.findClasses("plugins/codec");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		String[] expected = {"ROT13"};
//		
//		Assert.equals(expected, result);
//	}
//
//}
