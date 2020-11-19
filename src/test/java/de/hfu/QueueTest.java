package de.hfu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class QueueTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullMaxQueue() {
		Queue q = new Queue(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativMaxQueue() {
		Queue q = new Queue(-1);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testDequeueOnEmptyQueue() {
		Queue q = new Queue(1);
		q.dequeue();
	}
	
	@Test
	public void testEnqueue() {
		Queue q = enqueueNums(new int[]{1, 2, 3, 4});
		assertTrue("Values should be {4, 2, 3}. Actual values:" + Arrays.toString(q.queue), 
				Arrays.equals(new int[]{4, 2, 3}, q.queue));
	}
	
	@Test
	public void testDequeue() {
		Queue q = enqueueNums(new int[]{1, 2, 3});
		q.dequeue();
		assertEquals(1, q.head);
	}
	
	private Queue enqueueNums(int[] nums) {
		Queue q = new Queue(3);
		for (int n : nums) {
			q.enqueue(n);
		}
		return q;
	}

}
