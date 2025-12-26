package rec11;

public class FindDirty {
    public static int comparisons = 0;        

    public static int findInDirtyArray(char[] a, char element) {
        if (a == null || a.length == 0) {
            return -1;
        }

        if (a[0] == '&') {
            return -1;
        }

        int m = findJunkBoundary(a);

        return binarySearchDirty(a, element, 0, m - 1);
    }

    private static int findJunkBoundary(char[] a) {
        int i = 1;

        while (i < a.length && a[i] != '&') {
            comparisons++;
            i = 2 * i;
        }

        if (i >= a.length) {
            return a.length;
        }
        return i;
    }

    private static int binarySearchDirty(char[] a, char element, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        comparisons++;

        if (a[mid] == element) {
            return mid;
        }

        if (element < a[mid] || a[mid] == '&') {
            return binarySearchDirty(a, element, low, mid - 1);
        } else {
            return binarySearchDirty(a, element, mid + 1, high);
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