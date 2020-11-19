package de.hfu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativMonat() {
		Util.istErstesHalbjahr(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullMonat() {
		Util.istErstesHalbjahr(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDreizehnMonat() {
		Util.istErstesHalbjahr(13);
	}
	
	@Test
	public void testIstErsteHalbjahr() {
		assertTrue(Util.istErstesHalbjahr(1));
		assertTrue(Util.istErstesHalbjahr(6));
		assertFalse(Util.istErstesHalbjahr(7));
		assertFalse(Util.istErstesHalbjahr(12));
	}

}
