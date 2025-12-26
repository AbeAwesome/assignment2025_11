package rec11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DivideAndConquerTest {
	
	@BeforeEach
	void resetComparisons(){
		// reset number of comparisons on each test
		CountInSorted.comparisons = 0;
		FindDirty.comparisons = 0;
	}

	@Test
	void testCountSorted10() {		
		// set up random int array with 10 elements 
		int n = 10;
		int [] a = Utils.getRandIntArray(n, 10);
		
		// sort the array
		Arrays.sort(a);
		
		// test that countSorted returns the proper number of occurrences for each element
		for(int i = 0; i < a.length; ) {
			// get the current element
			int curr = a[i];
			int currCount = 0;
			// count how many times that element actually occurs
			while(i < a.length && a[i] == curr) {
				currCount++;
				i++;
			}
			// assert that the iterative calculated sum is equal to that of the recursive
			assertEquals(currCount, CountInSorted.countSorted(a, curr, 0, a.length));
		}		
	}
	
	@Test
	void testCountSorted1024() {		
		// set up array 
		int n = 1024;
		int element = -3;
		
		int[] a = Utils.getRandIntArrayWithRepeats(n, 12, element);
		
		// sort array
		Arrays.sort(a);		
				
		// assert that the iterative calculated count is equal to that of the recursive
		assertEquals(12, CountInSorted.countSorted(a, element, 0, a.length));
		
		// now see if the complexity is log n
		assertTrue(CountInSorted.comparisons <= 21);
	}
	
	@Test
	void testCountSorted512Repeats() {		
		// set up array
		int n = 1024;
		int element = -3;
		
		int[] a = Utils.getRandIntArrayWithRepeats(n, 512, element);
		
		// sort array
		Arrays.sort(a);			
		
		// assert that the iterative calculated count is equal to that of the recursive
		assertEquals(512, CountInSorted.countSorted(a, element, 0, a.length));
		System.out.println(CountInSorted.comparisons);
		
		// now see if the complexity is log n
		assertTrue(CountInSorted.comparisons <= 19);
	}
	
	@Test
	void testCountSorted1024Repeats() {		
		// set up array 
		int n = 1024;
		int element = -3;		
		int[] a = Utils.getRandIntArrayWithRepeats(n, 1024, element);
		
		// sort array 
		Arrays.sort(a);
		
				
		// assert that the iterative calculated count is equal to that of the recursive
		assertEquals(1024, CountInSorted.countSorted(a, element, 0, a.length));
		
		// now see if the complexity is log n
		assertTrue(CountInSorted.comparisons <= 21);		
	}
	
	@Test 
	void testDirtyArray() {		
		// set up array and get a random char
		char [] a = Utils.getDirtyCharArray();		
		char element = Utils.getRandChar();
				
		// set default values for lowest and highest possible indexes of element
		int minElementIndex = -1;
		int maxElementIndex = -1;
		
		// set default for number of non-junk characters
		int m = 0;

		for(int i = 0; i < a.length && (a[i] != '&' || a[i] <= element); i++) {			
			// if element is in the array 
			if(a[i] == element) {
				
				// assign index range
				minElementIndex = i;
				while(i+1 < a.length && a[i+1] == element) i++;
				maxElementIndex = i;
				
				// calculate m
				while(i+1 < a.length && a[i+1] != '&') i++;
				m = i;
				break;
			}
			
			// if element is not in the array
			if(i+1 < a.length && a[i+1] == '&') {
				m = i;
				break;
			}
			
		}
		
		
		// assert that the recursively found index of element is in the range of possible indexes of element 
		int actualPos = FindDirty.findInDirtyArray(a, element, 0, 1);
		assertTrue(actualPos >= minElementIndex && actualPos <= maxElementIndex);
		
		// calculate log m 		
		double maxComparisons = 2*Math.log(2*m)/Math.log(2) + 2;
		
		System.out.println("Number of non-junk characters was " + m);
		System.out.println("Maximum comparisons should be " + (int)maxComparisons + "; you used " + FindDirty.comparisons + " comparisons");
		
		// now see if the complexity is log m
		assertTrue((double)FindDirty.comparisons <= maxComparisons);		
	}
	


}