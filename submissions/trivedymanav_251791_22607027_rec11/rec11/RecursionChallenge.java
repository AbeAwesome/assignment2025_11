package rec11;


public class RecursionChallenge {	
	//exercise 1: return the sum of int numbers from 1 to n inclusive
	public static int sumN(int n) {
		// throw error for invalid input
		if (n < 1)
			throw new IllegalArgumentException("The value of " + n + " is invalid.");
		
		// base case of n == 1
		if (n == 1)
			return 1;
		
		// recursive case is sumN of 1 to n - 1 inc.
		return n + sumN(n - 1);
	}
	
	//exercise 2: return sum of the data in a linked list of numbers
	public static int sumList (Node node) {
		// base case is last node
		if (node == null)
			return 0;
		
		// recursive case is sum from next node
		return node.getData() + sumList(node.getNext());
	}
	
	//exercise 3: checks if all the elements of the list (defined by head node) are sorted
	public static boolean isSorted (Node node) {
		// base case is if there is no next or if current node is null (checked in proper order to prevent unsafe checks)
		if (node == null || node.getNext() == null)
			return true;
		
		// second base case is if this node and the next are not in order, so return false for sorted
		else if (node.getNext().getData() < node.getData())
			return false;
		
		// recursive case is checking the next node for being sorted with its next node
		return isSorted(node.getNext());
	}
	
	//exercise 4: returns new string where character '*' is inserted between each character of astring
	public static String allStar(String astring) {
		// base case is if there is only one (or less which means 0) chars left in string
		if (astring.length() <= 1)
			return astring;
		
		// recursive case is getting the first char and adding in the * after it then calling the same thing for the rest of the string
		return astring.charAt(0) + "*" + allStar(astring.substring(1));
	}
	
	//exercise 5: returns a new string in which all characters 'x' are removed
	public static String noX(String astring) {
		// base case is the empty string as we guaranteed have no x's
		if (astring.length() < 1)
			return "";
		
		// first recursive case is if the first char is x, in which remove it and then use the method on the rest of the string
		else if (astring.charAt(0) == 'x')
			return noX(astring.substring(1));
		
		// second recursive case is if the first char isn't x, in which case add it in and use the method on the rest of the string
		return astring.charAt(0) + noX(astring.substring(1));
	}	
	
	//exercise 6: output the product of the two integers n and m. 
	// do not use multiplication operator 
	//limited to using addition/subtraction and negation  
	public static int mult (int n, int m) {
		// base case for if either are 0 as mult by 0 is trivially 0
		if (n == 0 || m == 0)
			return 0;
		
		// if m is negative, then algorithm doesn't work properly (as the right shift (int division by 2) will never result in 0)
		// but if we know m is negative, we can just factor out the negative from m
		if (m < 0)
			return -mult(n, -m);
		
		// calculate n * floor(m/2) and consider recursive cases for even and odd by combining additions
		// calculate it only once to reduce depth of recursive tree
		int subMult = mult(n, m >> 1);
		if ((m & 1) == 0) // even as the first bit in the int is 0
			return subMult + subMult;
		
		// odd, first bit in the int is 1
		return subMult + subMult + n;
		
		// I just wanted to practice with bits from CS0447 by using them in this algorithm
	}
	
	public static void main (String [] args) {
		int n = 5;
		
		System.out.print("Testing Ex. 1: sum of first " + n + " integers: ");
		System.out.println(sumN(n)); // should be 15 
		
		Node list = Utils.sampleList(n);
		System.out.print("\nTesting Ex. 2: sum list " + n + " rand integers: ");
		Utils.printList(list);
		System.out.println("sum = " + sumList(list));
		
		list = Utils.sampleSortedList(n);
		System.out.print("\nTesting Ex. 2: sum list " + n + " sorted integers: ");
		Utils.printList(list);
		System.out.println("sum = " + sumList(list)); //should be 10
		
		list = Utils.sampleList(n);		
		System.out.println("\nTesting Ex. 3: input list:");
		Utils.printList(list);
		System.out.println("is the list sorted? "+ isSorted(list)); // false
		
		list = Utils.sampleSortedList(n);
		System.out.println("Testing Ex. 3: sorted input list:");
		Utils.printList(list);
		System.out.println("is the list sorted? "+ isSorted(list)); // true
		
		String astring = "hello";
		System.out.println("\nTesting Ex. 4: allStar input string: '" + astring 
				+ "' output string: '" + allStar(astring) +"'" ); //'h*e*l*l*o'
		
		astring = "a";
		System.out.println("Testing Ex. 4: allStar input string: '" + astring 
				+ "' output string: '" + allStar(astring) +"'"  ); // a
		
		astring = "rex pex fex";
		System.out.println("\nTesting Ex. 5: noX input string: '" + astring 
				+ "' output string: '" + noX(astring)  +"'" );	//'re pe fe'	
		
		int x = 8023324;
		int y = 7635214;
		
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = -x;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		y = -y;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = -x;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
	}
}

