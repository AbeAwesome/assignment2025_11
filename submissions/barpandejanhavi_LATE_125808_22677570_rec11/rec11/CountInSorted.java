package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		int first=findFirst(sortedA, element, 0, sortedA.length-1);
		if (first == -1){
			return 0;
		}
		int last=findLast(sortedA, element, 0, sortedA.length-1);
		return last-first+1;		
	}

	private static int findFirst(int[] sortedA, int element, int left, int right){
		if (left>right){
			return -1;
		}
		int mid=(left+right)/2;
		comparisons++;
		if(sortedA[mid]==element){
			int prev=findFirst(sortedA,element,left,mid-1);
			return(prev==-1)? mid: prev;
		}
		comparisons++;
		if (sortedA[mid]<element){
			return findFirst(sortedA,element,mid+1,right);
		}
		else{
			return findFirst(sortedA,element,left,mid-1);
		}
	}

	private static int findLast(int[] sortedA, int element, int left, int right){
		if (left>right){
			return -1;
		}
		int mid=(left+right)/2;
		comparisons++;
		if(sortedA[mid]==element){
			int next=findLast(sortedA,element,mid+1,right);
			return(next==-1)?mid:next;
		}

		comparisons++;
		if(sortedA[mid]<element){
			return findLast(sortedA,element,mid+1,right);
		}
		else{
			return findLast(sortedA,element,left,mid-1);
		}
	}
	public static void main (String [] args) {
		int n = 10;
		int [] a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		System.out.println("Input array of size  " + n + ": "+ Utils.arrToString(a));
		
		// test correctness first
		for (int i=0; i < a.length; i++)
			System.out.println("Element " + a[i] + " occurs " + countSorted(a, a[i]) + " times.");
		
		System.out.println("Element -3 occurs " + countSorted(a, -3) + " times.");
		System.out.println("Element 100 occurs " + countSorted(a, 100) + " times.");
		
		n = 1024;
		// now see if the complexity is log n
		int element = -3;
		a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024: item not found");
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 21: 2 logn + 1]");
		
		a = Utils.getRandIntArrayWithRepeats(n, 512, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024 with 512 repeats");
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 19: 2 logn + 1]");
		
		a = Utils.getRandIntArrayWithRepeats(n, 1024, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024 with 1024 repeats");
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 21: 2 logn + 1]");
	}
}