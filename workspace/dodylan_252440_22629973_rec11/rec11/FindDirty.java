package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		return growRange(a, element, 0, 1);
    }

    private static int growRange(char[] a, char element, int low, int high) {
        comparisons++;

        if (high >= a.length || a[high] == '&' || a[high] >= element) {
            return binarySearch(a, element, low, Math.min(high, a.length - 1));
        }

        int newLow = high + 1;
        int window = high - low + 1;
        int newHigh = high + window + 1;

        return growRange(a, element, newLow, newHigh);
    }

    private static int binarySearch(char[] a, char element, int low, int high) {
        if (low > high) return -1;

        int mid = low + (high - low) / 2;
        comparisons++;

        if (a[mid] == '&') {
            return binarySearch(a, element, low, mid - 1);
        }
        if (a[mid] == element) {
            return mid;
        }
        if (element < a[mid]) {
            return binarySearch(a, element, low, mid - 1);
        }
        return binarySearch(a, element, mid + 1, high);
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