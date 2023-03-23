import java.util.Random;

public class binarySearch {
	//Binary search - best case scenario = O(1); worst case scenario = log(n)
	public static int search(Comparable[] list, Comparable target) {
		int start = 0; 
		int end = list.length - 1; 
		int mid = 0; //initializing the various varibles 

		while (start <= end) {
			mid = (start + end) / 2;
			if (list[mid].compareTo(target) == 0) {
				//if the midpoint and the target are the same, it will return 0
				return mid;
			} else if (target.compareTo(list[mid]) < 0) {
				end = mid - 1; //set the end to less than the mid (AKA focus on sublist to the left of the midpoint)
			} else {
				start = mid + 1; //set the start to more than the mid (AKA focus on sublist to the right of the midpoint)
			}
		}
		return -1;
	}

	//Driver code 
	public static void main(String[] args) {
		Integer[] numbers = new Integer[100000]; //Creating an array with 100k elements
		Random rand = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = rand.nextInt(100000); //iterate through the indices in the array and fill with random int 
		}
		binarySearch ob = new binarySearch(); //New binarySearch object
		System.out.println(ob.search(numbers, 15)); //invoke the search method 
	}
}