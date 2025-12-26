package rec11;

public class FindDirty {
	public static int comparisons = 0;

	public static int findInDirtyArray(char[] a, char element) {
		comparisons++;
		if (a[0] == element)
			return 0;
		int high = findScope(1, a, element);
		if (a[high] == element)
			return high;
		comparisons++;
		int low = high / 2;
		return findInDirtyArrayHelper(low, high, a, element);
	}

	public static int findScope(int low, char[] a, char element) {
		comparisons++;
		if (a[low] == element || a[low] == '&' || a[low] > element) {
			return low;
		}
		return findScope(low * 2, a, element);
	}

	public static int findInDirtyArrayHelper(int low, int high, char[] a, char element) {
		if (low > high)
			return -1;
		int mid = (low + high) / 2;
		comparisons++;
		if (a[mid] == element) {
			return mid;
		}
		comparisons++;
		if (a[mid] == '&' || a[mid] > element) {
			return findInDirtyArrayHelper(low, mid - 1, a, element);
		}
		return findInDirtyArrayHelper(mid + 1, high, a, element);
	}

	public static void main(String[] args) {
		char[] a = Utils.getDirtyCharArray();
		int n = a.length;

		char element = Utils.getRandChar();
		System.out.println("Searching for element <" + element + "> in a dirty array of size " + n);
		comparisons = 0;
		int pos = findInDirtyArray(a, element);
		if (pos != -1)
			System.out.println("Found element <" + element + "> at position " + pos);

		System.out.println("Used " + comparisons + " comparisons.");
	}
}
