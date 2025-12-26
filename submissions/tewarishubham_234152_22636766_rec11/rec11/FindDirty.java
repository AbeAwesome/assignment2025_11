package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int high = findHigh(a, element, 0, 1);
	    return binarySearch(a, element, 0, high);
	}
	
	private static int findHigh(char[] a, char element, int low, int high) {
        comparisons++; 
        if (a[high] == '&' || a[high] >= element) {
            return high; 
        } else {
            return findHigh(a, element, high, high * 2);
        }
    }
    
    private static int binarySearch(char[] a, char element, int low, int high) {
        if (low > high) {
            return -1;  
        }
        int mid = low + (high - low) / 2;
        comparisons++;  
        if (a[mid] == element) {
            return mid;
        } else if (a[mid] < element) {
            return binarySearch(a, element, mid + 1, high);
        } else {
            return binarySearch(a, element, low, mid - 1);
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