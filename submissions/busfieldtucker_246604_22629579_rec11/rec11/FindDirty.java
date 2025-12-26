package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		
		int bound = getJunkBound(a);
		
		return binarySearch(a, element, 0, bound);
	}
	
	private static int getJunkBound(char[] a) {
		int i = 1;
		
		while (i < a.length && a[i] != '&') {
			comparisons++;
			i*=2;
		}
		return i;
	}
	
	private static int binarySearch(char[] a, char elem, int start, int end) {
		if (start > end) return -1;
		int mid = start + (end-start)/2;
		
		comparisons++;
		if (a[mid] == elem) {
			return mid;
		} else if (a[mid] > elem) { // search left
			return binarySearch(a, elem, start, mid-1);
		} else {
			return binarySearch(a, elem, mid+1, end);
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