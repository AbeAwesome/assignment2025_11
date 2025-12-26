package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		if(a.length == 0)
			return -1;
		int m = 0;
		for(int i = 1; i < a.length; i*=2)
		{
			comparisons++;
			if(a[i] == '&')
			{
				m = i;
				break;
			}
		}
		return findPos(a, 0, m, element);
	}
	
	public static int findPos(char[] a, int low, int high, char target)
	{
		if(low > high)
		{
			return -1;
		}
		int mid = (low + high) / 2;
		comparisons++;
		if(a[mid] == target)
		{
			return mid;
		}
		else if(a[mid] < target)
		{
			return findPos(a, mid + 1, high, target);
		}
		else // a[mid] > target
		{
			return findPos(a, low, mid - 1, target);
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