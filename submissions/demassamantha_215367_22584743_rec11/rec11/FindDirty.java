package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		// probe positions 2, 4, 8 ... until found junk
		int m = 1;
		while (m<a.length && a[m] != '&') {
			comparisons++;
			m = 2*m;
		}
		// binary search now that you know boundary
		return binarySearchDirty(a, element, 0, m-1);
	}
	
	private static int binarySearchDirty(char[] a, char element, int low, int high) {
		if (low > high) {
			return -1; // not found
		}
		int mid = (low+high)/2;
		comparisons++;
		if(a[mid] == element) {
			return mid; // found, base case
		}
		if (element < a[mid] || a[mid]=='&') {
			return binarySearchDirty(a, element, low, mid-1);
		}
		// else element > a[mid]
		return binarySearchDirty(a, element, mid+1, high);
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