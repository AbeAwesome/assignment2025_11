package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	
	// recursive function that splits function into 2 parts until it gets to its lowest level
	// how function will work:
	// binary go through the array until it finds the item
	// once it does call the extra function
	// the extra function will split it into 2 parts
	// it will keep splitting and not keeping the one side until it finds the element
	// once it finds the element it will split into 2 again
	// the side w the element will have its count added to a total
	// the other side will keep binary'ing through until it finds the last one or 
	// it 
	
	public static int countSorted (int [] sortedA, int element) {
		
//		System.out.println("elemnt is "+element);
//		System.out.println("array is "+Arrays.toString(sortedA));
		int[] middleVals = getMiddle(sortedA,element,0,sortedA.length-1);
//		int [] middleVals = 
//		System.out.println("getMiddle used: "+comparisons+" comparisons");
//		System.out.println("the middle is: "+middle);
		
		if (middleVals == null) {
			return -1;
		}
		if (middleVals[0] == middleVals[2]) {
//			System.out.println("Returning "+sortedA[middleVals[1]]);
			return 1;
		}
		
		int leftBound;
		int rightBound;
//		System.out.println(Arrays.toString(middleVals));
		comparisons++;
		if (sortedA[middleVals[0]] != element) {
			leftBound = findBounds(sortedA, middleVals[0], middleVals[1], element,false);
		} else {
			leftBound = middleVals[0];
		}
//		System.out.println(comparisons +" were used after left bound was found");
		comparisons++;
		if (sortedA[middleVals[2]] != element) {
			rightBound = findBounds(sortedA, middleVals[1], middleVals[2], element,true);
		} else {
			rightBound = middleVals[2];
		}
//		System.out.println(comparisons +" were used after right bound was found");
		
		if (leftBound == -1) {
			leftBound = middleVals[1];
		}
		if (rightBound == -1) {
			rightBound = middleVals[1];
		}
		if (rightBound == -1 && leftBound == -1)
			return -1;
//		System.out.println();
//		System.out.println("leftbound is: "+leftBound+" and rightbound is "+rightBound);
//		System.out.println("Overall "+comparisons+" comparisons were used");
		return (rightBound-leftBound)+1;
	}

	public static int[] getMiddle(int[] sortedA, int element, int bottom, int top) {
		
		if (bottom > top) 
			return null;
		
		int middle = (top + bottom) / 2;
		
		if (middle >= sortedA.length) 
			return null;
		
		comparisons++; // saving the comparison of a valueso i only increment comparisons once
		int compare = sortedA[middle] - element;
		
		
		if (compare == 0) {
//			System.out.println("found element "+element+" at position "+middle);
//			System.out.println("Bottom index is "+bottom+" and bottom value is "+sortedA[bottom]);
//			System.out.println("Middle index is "+middle+" and bottom value is "+sortedA[middle]);
//			System.out.println("Top    index is "+top+" and top    value is "+sortedA[top]);
			int [] ret = {bottom, middle, top};
			return ret;
			
		}
		comparisons++;
		if (compare < 0) {
//			System.out.println("Undershot @ index "+middle+" which is "+sortedA[middle]+" top is "+top+" bottom is "+bottom);
			return getMiddle(sortedA, element, middle+1, top);
			
		} else { // sortedA[middle] > element
//			System.out.println("Overshot @ index "+middle+" which is "+sortedA[middle]+" top is "+top+" bottom is "+bottom);
			return getMiddle(sortedA,element,bottom,middle-1);
		}
	}
	
public static int findBounds(int[] sortedA, int bottom, int top, int element, boolean right) {
	// maybe save the last step? so if you binary search until you find the bad elements
	// then binary search until you find the good one, and then attempt to go forward again
	// so keep binary searching forward, saving the furthest element, until you cant get the last one
	// so i guess use recursion until reaching the end, and only stop when furthest right is element?
	
	// use binary search to eliminate the majority of items. then, once binary search finds the element
	// compare right to element. if its bad, keep going right until you find another element.
	// once another element is found, try going left again. if you hit an element, go right
		
//	if (top-bottom == 0) {
//		System.out.println("TEST");
//		if (right) {
//			return bottom-1;
//		} else {
//			return top;
//		}
//		
//	}
	if (bottom > top) {
//		System.out.println("bottom is bigger");
		if (right) {
			return bottom-1;
		} else {
			return top+1;
		}
	}
	int middle = (top + bottom)/2;
	
	comparisons++;
	if (sortedA[middle] == element) {
		if (right) {
			if (middle == top) {
//				System.out.println("middle is top returning "+middle);
				return middle;
			}
			comparisons++;
			if (sortedA[middle+1] != element) {
//				System.out.println("found edge at "+middle);
				return middle;
			}
//			comparisons++;
//			if (sortedA[top] == element) {
//				return top;
//			}
//			System.out.println("going right at "+middle+"to find more elements");
			return findBounds(sortedA, middle+1, top,element,true);	
		} else {
			if (middle == bottom) {
//				System.out.println("middle is bottom returning "+middle);
				return middle;
			}
			comparisons++;
			if (sortedA[middle-1] != element) {
//				System.out.println("found edge at "+middle);
				return middle;
			}
//			comparisons++;
//			if (sortedA[bottom] == element) {
//				return bottom;
//			}
//			System.out.println("going left at "+middle+" to find more element");
			return findBounds(sortedA, bottom, middle-1,element,false);
		}
	} else {
		if (right) {
//			System.out.println("no element found at "+middle+" going left");
			return findBounds(sortedA, bottom, middle-1, element,true);
			
		}
//		System.out.println("no element found at "+middle+" going right");
		return findBounds(sortedA, middle+1, top, element, right);
		
	}
	
//	return 0;
	
}

	
	public static void main (String [] args) {
		
		int n = 30;
		int [] a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		System.out.println("Input array of size  " + n + ": "+ Utils.arrToString(a));
		
//		System.out.println("Testing getMiddle w 7: "+getMiddle(a,7,0,a.length));
		
//		 test correctness first
//		for (int i=0; i < a.length; i++)
//			System.out.println("Element " + a[i] + " occurs " + countSorted(a, a[i]) + " times.");
//		System.out.println("Element 4 occurs "+countSorted(a,4)+" times");
//		System.out.println("Element -3 occurs " + countSorted(a, -3) + " times.");
//		System.out.println("Element 100 occurs " + countSorted(a, 100) + " times.");
		
		System.out.println("Element 7 occurs "+countSorted(a,7)+"times");
//		System.out.println("Element 0 occurs "+countSorted(a,0)+" times");
//		System.out.println("Element 9 occurs "+countSorted(a,9)+" times");
		
		/*
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
		*/
	}
}

