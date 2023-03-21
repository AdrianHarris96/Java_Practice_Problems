import java.util.Arrays;

public class mergeSort {
	//Merge sort - O(nlog(n)) - logarithmic in its layers of splitting and merging the list, linear in the operations under each layer
	//Method that uses merge sort to sort an array 
	public void sort(int[] list, int start, int end) {
	    if (start == end) {
	        return;
	    } else if (start == end-1) {
	        if (list[start] <= list[end]) {
	            return;
	        } else { // swap
	            int temp = list[start];
	            list[start] = list[end];
	            list[end] = temp;
	        }
	    }
	    
	    int mid = (end-start)/2; //determining the midpoint 
	    sort(list, start, start + mid); //recursion
	    sort(list, start + mid + 1, end);
	    merge(list, start, start + mid, end);
	}

	//Helper method
	private static void merge(int[] list, int leftHalfStart, int rightHalfStart, int end) {
		//Determining the sizes of the two arrays
	    int leftHalfSize = rightHalfStart - leftHalfStart + 1;
	    int rightHalfSize = end - rightHalfStart;

	    //Creating an array for each half
	    int[] leftHalf = new int[leftHalfSize];
	    int[] rightHalf = new int[rightHalfSize];

	    //Fill the two arrays with their respective elements using the size determined earlier 
	    for (int i=0; i<leftHalfSize; ++i)
	        leftHalf[i] = list[leftHalfStart + i];
	    for (int j=0; j<rightHalfSize; ++j)
	        rightHalf[j] = list[rightHalfStart + 1+ j];

	    int i = 0;
	    int j = 0;

	    //Initialize k as the master index
	    int k = leftHalfStart;
	    //While we are still under the size of both arrays
	    while (i < leftHalfSize && j < rightHalfSize) {
	        if (leftHalf[i] <= rightHalf[j]) {
	        	//if left array has the smaller value, put it first in this master list, iterate through i (left array index)
	            list[k] = leftHalf[i];
	            i++;
	        } else {
	        	//otherwise, put the value from the right array in the master list first, iterate through j (right array index)
	            list[k] = rightHalf[j];
	            j++;
	        }
	        k++; //always iterate through k with each pass of the loop
	    }

	    //Copy remaining elements of the left array if they exists
	    while (i < leftHalfSize) {
	        list[k] = leftHalf[i];
	        i++;
	        k++;
	    }

	    //Copy remaining elements of the right array if they exists
	    while (j < rightHalfSize) {
	        list[k] = rightHalf[j];
	        j++;
	        k++;
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
		mergeSort ob = new mergeSort(); //initialize new sort object
		int[] myArray = {6, 11, 2, 7, 5, 3, 13, 10};
		System.out.println("Pre-sort Array:");
		ob.printArray(myArray);
		System.out.println("Post-sort Array:");
		ob.sort(myArray, 0, myArray.length - 1); //accepts the array, the start index (0), the last element (length of array - 1)
		ob.printArray(myArray);
	}
}
