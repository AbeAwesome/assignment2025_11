package rec11;

public class FindDirty {
    public static int comparisons = 0;

    public static int findInDirtyArray(char[] a, char element) {
        return expandRange(a, element, 1);
    }

    private static int expandRange(char[] a, char element, int idx) {
        comparisons++;

        if (a[idx] == '&' || a[idx] >= element) {
            return binarySearch(a, element, idx / 2, idx);
        }

        return expandRange(a, element, idx * 2);
    }

    private static int binarySearch(char[] a, char element, int l, int r) {
        if (l > r) {
            return -1;
        }

        int mid = (l + r) / 2;
        comparisons++;

        if (a[mid] == '&' || a[mid] > element) {
            return binarySearch(a, element, l, mid - 1);
        }

        if (a[mid] < element) {
            return binarySearch(a, element, mid + 1, r);
        }

        return mid;
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