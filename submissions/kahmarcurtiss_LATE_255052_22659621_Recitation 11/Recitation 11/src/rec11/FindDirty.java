package rec11;

public class FindDirty 
{
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) 
	{
		int high = findRange(a, element, 1);
		//figure out high boundaray using exponential search
		
		if(high == 1)
		{
			return -1;
			//find range cant find valid range then done
		}
		
		int low = high/2;
		//low boundary is halved of the high
				
		return binarySearch(a, element, low, high);
		//search between low and high
		
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
	}
	
	private static int findRange(char [] a, char element, int index)
	{
		if(index >= a.length || index == '&')
			return index;
			//stop if exceed array bounds
		
		comparisons ++;
		
		if(a[index] == element)
		{
			return index;
			//if target found return index
		}
		if(a[index] < element)
		{
			return findRange(a, element, index * 2);
			//double our index if we havent found index
		}
		return index;
		//out of bounds
	}
	
	private static int binarySearch(char [] a, char element, int low, int high)
	{
		if(low > high)
		{
			return -1;
			//no room left
		}
		
		int mid = ((low + high)/2);
		comparisons ++;
		//recursive count how many times x appears. shrink search range.
			
		if(element == a[mid])
		{
			return mid;
			//found target return index
		}
		else if(element < a[mid])
		{
			return binarySearch(a , element, low, mid - 1);
			//target is in the left half, shrink search range to low by mid -1
		}
		else 
		{
			return binarySearch(a, element, high, mid + 1);
			//target is in high section expand search range to high by mid +1
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