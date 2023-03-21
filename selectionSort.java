import java.util.Arrays;

public class selectionSort {
	//Selection sort - best case scenario = O(n); worst case scenario = O(n^2)
	//Method to sort int array
	public void sort(int[] list) {
	int minIndex;
	int nextSmallest;

	for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
		minIndex = unSortedStart;
		//Use this loop to locate the index with the smallest ("min") index
		for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) {
			if (list[currentIndex] < list[minIndex]) {
				minIndex = currentIndex; //set the minIndex to the newly found smaller element
			}
		}

		//Swap the found minimum element with the first element 
		nextSmallest = list[minIndex];
		list[minIndex] = list[unSortedStart];
		list[unSortedStart] = nextSmallest;
		}
	}

	//Method to sort for doubles - overloading 
	public void sort(double[] list) {
	int minIndex;
	double nextSmallest;

	for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
		minIndex = unSortedStart;
		for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) {
			if (list[currentIndex] < list[minIndex]) {
				minIndex = currentIndex; //set the minIndex to the newly found smaller element
			}
		}

		nextSmallest = list[minIndex]; 
		list[minIndex] = list[unSortedStart];
		list[unSortedStart] = nextSmallest;
		}
	}

	//Method to print array
	public void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

	//Driver code
	public static void main(String[] args) {
		selectionSort ob = new selectionSort(); //initialize new sort object
		int[] myArray = {3, 4, 5, 8, 9, 2};
		ob.sort(myArray);
		ob.printArray(myArray);
	}
}

