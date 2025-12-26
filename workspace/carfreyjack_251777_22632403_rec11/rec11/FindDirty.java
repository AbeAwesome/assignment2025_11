package rec11;

import java.util.Arrays;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		
		// cut in half, pass new array through method w recursion, rinse and repeat
		int bound = dirtyArraySearch(a, element, 1);
		System.out.println("right bound is "+bound+" which is "+a[bound]);
		
		System.out.println("here is the array up to and including bound:");
		for (int i = 0; i < bound+1; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
		
		return elementSearch(a, element, 0, bound);
	}
	
	public static int dirtyArraySearch(char [] a, char element, int start) {
		// double value from 0 to when dirty array ends?
		// basically go 1 then 2 then 4 then 8
		// then once it hits greater than the element or the junk array itll return that as the bound
		// then we'll just search in there
		comparisons++;
		if (a[start] >= element || a[start] == '&')
			return start;
//			return dirtyArraySearch(a, element, start*2);
		
		return dirtyArraySearch(a, element, start*2);
	}
	
	public static int elementSearch(char[] a, char element, int leftBound, int rightBound) {
		
		if (leftBound > rightBound)
			return -1;
		
		int middle = (rightBound+leftBound) / 2;
		
		comparisons++;
		if (a[middle] == element) {
			System.out.println("found the element @ pos "+ middle);
			return middle;
		}
		
//		comparisons++;
		if (a[middle] > element || a[middle] == '&') {
			return elementSearch(a, element, leftBound, middle-1);
		} else { // a[middle] > element
			return elementSearch(a, element, middle+1, rightBound);
		}
		
//		return -1;
	}
	
	public static void main (String [] args) {
		char [] a = Utils.getDirtyCharArray();
		int n = a.length;
		
		char element = Utils.getRandChar();
		System.out.println("Searching for element <" + element + "> in a dirty array of size " + n);
		comparisons = 0;
		int pos = findInDirtyArray (a, element);
		if (pos != -1)
			System.out.println("Found element <" + element + "> at position " + pos);
		else
			System.out.println("Not found.");
		System.out.println("Used " + comparisons + " comparisons.");
	}
}