package starter_code.rec11;

public class FindDirty {
	public static int comparisons = 0;
	
	private static int findHigh (char [] junkArr) {
		int i=1;
		
		while (i < junkArr.length && junkArr[i] != '&') {
			comparisons++;
			i = 2 * i;
		}
		return i;
	}
	
	public static int findInDirtyArray(char [] a, char element) {
		//TO DO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int high = findHigh(a);
		return binary(a, element, 0, high);
	}
	private static int binary (char [] array, int element, int low, int high) {
		comparisons++;
		if (low > high) {
			return -1;
		}
		int mid = (high - low) / 2 + low;
		if (array[mid] == element) {
			return mid;
		}
		if (array[mid] < element) {
			return binary(array, element, mid + 1, high);
		} 
		else {
			return binary(array, element, low, mid-1);
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