package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int guessM = 1;
		while (a[guessM] != '&' && element > a[guessM]) {
			
			guessM = 2 * guessM;
		
		}
		return findM(a, element, 0, 1);
		
		
		
		
	}
	
	/*
	 * attempt #1 of findM() method
	 * private static int findM(char [] a, char element, int left, int right) { if
	 * (right < left || element < a[left]) { return -1; }
	 * 
	 * int midpoint = (left + right)/2; comparisons++; if (element > a[midpoint]) {
	 * return findM(a, element, midpoint + 1, right); } else if (element <
	 * a[midpoint]) { return findM(a, element, left, midpoint - 1); } else { //
	 * element == a[midpoint] return midpoint; }
	 * 
	 * }
	 */
	
	private static int findM(char [] a, char element, int left, int right) {
	    if (right < left || element < a[left]) { // ensures element is not out of bounds
	        return -1;
	    }

	    comparisons++;
	    if (element > a[right] && a[right] != '&') { // '&' is less than any letter so we need to check that a[right]
	        return findM(a, element, left , 2*right);
	    }
	    int mid = left + (right - left) / 2;
	    if (a[mid] > element || a[mid] == '&') {
	        return findM(a, element, left, mid-1);
	    } else if (a[mid] < element) {
	        return findM(a, element, mid+1, right);
	    } else {
	        return mid;
	    }
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
		
		System.out.println("Used " + comparisons + " comparisons.");
	}
}