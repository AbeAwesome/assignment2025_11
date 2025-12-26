package rec11;


public class RecursionChallenge {	
	//exercise 1
	public static int sumN(int n) {
		if (n == 1)
			return 1;
		return n + sumN(n-1);
	}
	
	//exercise 2
	public static int sumList (Node node) {
		if (node == null)
			return 0;
		return node.getData() + sumList(node.getNext()); 
	}
	
	//exercise 3
	public static boolean isSorted (Node node) {
		 if (node == null || node.getNext() == null)
			 return true;
		 if (node.getData() > node.getNext().getData())
		     return false;
		 
		 // you compared t[0] and t[1] they are ok
		 // you now can continue without t[0]
		 return isSorted(node.getNext());
	}
	
	//exercise 4
	public static String allStar(String astring) {
		if (astring.length() == 0)
			return "";
		if (astring.length() == 1)
			return astring;
		
		return astring.substring(0, 1) + '*' + allStar(astring.substring(1));
	}
	
	//exercise 5
	public static String noX(String astring) {
		 if (astring.length() == 0)
			 return "";
		 if (astring.charAt(0) == 'x') {   //remove it and continue			 
			 return noX(astring.substring(1));
		 }
		 else {   // we must keep this char but we continue cleaning the rest of a string
			 return astring.substring(0, 1) + noX(astring.substring(1));
		 }	
	}	
	
	//exercise 6  (most challenging)
	public static int mult (int n, int m) {
		if (m == 0)
	        return 0;

	    if (m < 0)
	        return -n + mult(n, m + 1);
	    else
	        return n + mult(n, m - 1);
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
		
		int x = 8;
		int y = 7;
		
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = - 8;
		y = 7;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = - 8;
		y = - 7;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = 8;
		y = - 7;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
	}
}

