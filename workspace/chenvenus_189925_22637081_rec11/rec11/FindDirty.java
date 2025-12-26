package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		if (a == null || a.length == 0) return -1;

		// first character junk → no valid letters at all
		comparisons++;
		if (a[0] == '&') return -1;

		// Phase 1: find approximate upper bound of the valid region
		int bound = findBound(a, 1);

		// Phase 2: binary search inside [0, bound]
		return binarySearchDirty(a, element, 0, bound);
	}

// ------------ helpers (allowed) ------------

	private static int findBound(char[] a, int index) {
		// if index outside array → whole valid region inside [0, a.length-1]
		if (index >= a.length) {
			return a.length - 1;
		}

		comparisons++;
		if (a[index] == '&') {
			// first junk → valid region ends at index - 1
			return index - 1;
		}

		// still valid letter → double index
		return findBound(a, index * 2);
	}

	private static int binarySearchDirty(char[] a, char target, int low, int high) {
		if (low > high) return -1;

		int mid = (low + high) / 2;

		// protect out-of-bound (should rarely happen)
		if (mid >= a.length) {
			return binarySearchDirty(a, target, low, high - 1);
		}

		comparisons++;
		if (a[mid] == '&') {
			// mid and right side are junk → search left half
			return binarySearchDirty(a, target, low, mid - 1);
		}

		comparisons++;
		if (a[mid] == target) return mid;

		comparisons++;
		if (a[mid] < target) {
			return binarySearchDirty(a, target, mid + 1, high);
		} else {
			return binarySearchDirty(a, target, low, mid - 1);
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