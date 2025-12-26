package rec11;

public class FindDirty {
	public static int comparisons = 0;
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		//start of code
		int high = 2;
		
		//yes I stole this code from you
		while (a[high] != '&')
		{
			comparisons++;
			high = high * 2;
		}
		
		return BinaryishSort(a, element, 0, high);
	}
	
	public static int BinaryishSort(char[] a, char element, int low, int high)
	{
		//also stole this
		if (low > high)
		{
			return -1;
		}
		int mid = ((high - low) / 2) + low;
		
		comparisons++;
		if (a[mid] == element)
		{
			return mid;
		}
		else if (a[mid] > element || a[mid] == '&')
		{
			return BinaryishSort(a, element, low, mid - 1);
		}
		else
		{
			return BinaryishSort(a, element, mid + 1, high);
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