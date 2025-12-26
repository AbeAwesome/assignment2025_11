package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		comparisons = 0;
	    return findEnd(a, element, 0, 1);
	}
	private static int findEnd(char[] a, char element, int start, int end) {
	    if(end >= a.length || a[end] == '&' || a[end] >= element) {
	        return binarySearchDirty(a, element, start, end);
	    }
	    return findEnd(a, element, end, end + (end - start + 1));
	}
	private static int binarySearchDirty(char[] a, char element, int low, int high) {
	    if(low > high) return -1;
	    int mid = low + (high - low) / 2;
	    comparisons++;
	    
	    if(a[mid] == element) {
	    	return mid;
	    }
	    if(a[mid] == '&' || a[mid] > element) {
	    	return binarySearchDirty(a, element, low, mid - 1);
	    }
	    return binarySearchDirty(a, element, mid + 1, high);
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