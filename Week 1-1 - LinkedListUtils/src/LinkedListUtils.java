
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
		if (list == null){
			return;
		}
		
		if (list.size() == 0) {
			list.add(value);
		}
		
		if (value < list.getFirst()) {		
			list.addFirst(value);	
		} else if (value > list.getLast()) {	
			list.addLast(value);
		} else {	
			for (int i = 0; i < list.size(); i++) {		
				if (value < list.get(i)) {
					list.add(i, value);
					break;
				}	
			}	
		}
	}
	
	public static void removeMaximumValues(LinkedList<String> list, int N) {
		if (list == null || N < 0 || list.size() == 0) {
			return;
		}

		for (int i = 0; i < N; i++) {
			if (list.size() > 0) {
				String current = list.getFirst();

				for (String next : list) {
					if ((next.compareTo(current)) > 0) {
						current = next;
					}
				}

				if (current != null) {

					list.remove(current);

					// check for repetitions
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j).equals(current))
							list.remove(j);
					}
				}
			}
		}

	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		if (one == null || two == null || one.size() == 0 || two.size() == 0)
			return false;

		int i = 0;
		int j = 0;
		boolean match = false;

		while (i < one.size()) {
			if (one.get(i) == two.get(j)) {
				match = true;
				i++;
				j++;
				// if elements are equal start comparing with second list
				while (j < two.size() && i < one.size()) {
					if (!(one.get(i) == two.get(j))) {
						match = false;
						j = 0;
						break;
					} else {
						i++;
						j++;
					}
				}
				if (match && j == two.size()) return true;
			} else {
				// if elements are not equal increase index for first list
				i++;
			}
		}
		
		return false;
	}
		
	public static void main(String[] args) {
/*		LinkedList<Integer> list = new LinkedList<Integer>();
			
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		
		System.out.println(list);
		
		LinkedListUtils.insertSorted(list, 11);
		
		System.out.println(list);*/
		
/*		LinkedList<String> stringList = new LinkedList<String>();
		
		stringList.add("one");
		stringList.add("hello");
		stringList.add("eclipse");
		stringList.add("subsequence");
		stringList.add("house");
		stringList.add("subsequence");

		
		System.out.println(stringList);
		
		LinkedListUtils.removeMaximumValues(stringList, 10);
		
		System.out.println(stringList);*/
		
		LinkedList<Integer> intListOne = new LinkedList<Integer>();
		LinkedList<Integer> intListTwo = new LinkedList<Integer>();
		
		intListOne.add(0);
		intListOne.add(1);
		intListOne.add(0);
		intListOne.add(1);
		intListOne.add(2);
		intListOne.add(3);		
		intListOne.add(4);
		intListOne.add(5);
		
		intListTwo.add(0);
		intListTwo.add(1);
		intListTwo.add(2);
		
		System.out.println(LinkedListUtils.containsSubsequence(intListOne, intListTwo));
	}
}
