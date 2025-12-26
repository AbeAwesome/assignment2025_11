package rec11;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class RecursionChallengeTest {	

	@Test
	void testSumN() {
		assertEquals(10, RecursionChallenge.sumN(4));
		assertEquals(1, RecursionChallenge.sumN(1));
	}

	@Test
	void testSumList() {
		Node head = Utils.sampleList(5);
		int sum = 0;
		for(Node n = head; n != null; n = n.getNext()) {
			sum += n.getData();
		}
		
		assertEquals(sum, RecursionChallenge.sumList(head));		
		
		head = null;
		assertEquals(0, RecursionChallenge.sumList(head));
	}
    
	@Test
	void testIsSorted() {
		int n = 5;
		Node head = Utils.sampleList(n);
		boolean b = true;
		for(Node node = head; node != null && node.getNext()!=null; node = node.getNext()) {
			if (node.getData() > node.getNext().getData()) {
				b = false;
				break;
			}
		}
		assertEquals(b, RecursionChallenge.isSorted(head));
		
		head = Utils.sampleList(n);
		b = true;
		for(Node node = head; node != null && node.getNext()!=null; node = node.getNext()) {
			if (node.getData() > node.getNext().getData()) {
				b = false;
				break;
			}
		}
		assertEquals(b, RecursionChallenge.isSorted(head));
		
		n=200;
		head = Utils.sampleSortedList(n);
		assertTrue(RecursionChallenge.isSorted(head));
	}


	@Test
	void testAllStar() {
		String astring = "hello";
		assertEquals("h*e*l*l*o", RecursionChallenge.allStar(astring));
		
		astring = "hi";
		assertEquals("h*i", RecursionChallenge.allStar(astring));
		
		astring = "a";
		assertEquals("a", RecursionChallenge.allStar(astring));
		
		astring = "";
		assertEquals("", RecursionChallenge.allStar(astring));
	}

	@Test
	void testNoX() {
		String astring = "ex box dox";
		assertEquals("e bo do", RecursionChallenge.noX(astring));
		
		astring = "x";
		assertEquals("", RecursionChallenge.noX(astring));
		
		astring = "nothing to remove";
		assertEquals("nothing to remove", RecursionChallenge.noX(astring));
	}

	@Test
	void testMult() {
		int x = 8;
		int y = 7;
		assertEquals(56, RecursionChallenge.mult(x, y));
		
		x = - 8;
		y = 7;
		assertEquals(-56, RecursionChallenge.mult(x, y));
		
		
		x = - 6;
		y = - 7;
		assertEquals(42, RecursionChallenge.mult(x, y));
		
		x = 8;
		y = - 7;
		assertEquals(-56, RecursionChallenge.mult(x, y));
		
		x = 0;
		assertEquals(0, RecursionChallenge.mult(x, y));
		
		x=1;
		y=1;
		assertEquals(1, RecursionChallenge.mult(x, y));
		
		x=1;
		y=-1;
		assertEquals(-1, RecursionChallenge.mult(x, y));
		
		x=-1;
		y=-1;
		assertEquals(1, RecursionChallenge.mult(x, y));
	}


}