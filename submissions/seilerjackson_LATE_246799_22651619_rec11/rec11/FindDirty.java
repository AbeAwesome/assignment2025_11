package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int high = findRange(a, element, 0, 1);
		if (high == -1) {
			return -1;
		}
		int low = high / 2;
		return binarySearch(a, element, low, high);
	}
	
	private static int findRange(char [] a, char element, int low, int high) {
		if (high >= a.length) {
			high = a.length - 1;
		}
		comparisons++;
		if (a[high] == '&' || element < a[high]) {
			comparisons++;
			if (element < a[low]) {
				return -1;
			}
			return high;
		}
		comparisons++;
		if (a[high] == element) {
			return high;
		}
		low = high;
		high *= 2;
		return findRange(a, element, low, high);
	}
	
	private static int binarySearch(char[] a, char element, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = low + (high - low) / 2;
		comparisons++;
		if (a[mid] == element) {
			return mid;
		}
		comparisons++;
		if (a[mid] == '&' || element < a[mid]) {
			return binarySearch(a, element, low, mid - 1);
		} else {
			return binarySearch(a, element, mid + 1, high);
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