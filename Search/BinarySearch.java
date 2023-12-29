package Search;

import Sort.MergeSort;

public class BinarySearch {

	public static void main(String[] args) {
		int[] inputArray = {4, 6, 2, 3, 1, 7, 8, 9, 10, 5};
		
		MergeSort.mergeSort(inputArray); // Sorting our inputArray, since binarySeach only works this way.
		
		int valueToFind = 6; // The value we are looking for
	
		int indexOfValue = binarySearch(inputArray, valueToFind);
		System.out.println(indexOfValue);
	}
	
	public static int binarySearch(int[] inputArray, int valueToFind) {
		// The to pointers that show us the beginning and the end of the part we have to search in.
		int lowerBound = 0; 
		int upperBound = inputArray.length - 1;
		
		// The Loop terminates, if the lowerBound is bigger then the upperBound, since then the searched value doesn't exist.
		while (lowerBound <= upperBound) {
			int middle = (lowerBound + upperBound) / 2;
			
			if (inputArray[middle] == valueToFind) {
				return middle;
			}else if (inputArray[middle] < valueToFind) {
				lowerBound = middle + 1;
			}else {
				upperBound = middle - 1;
			}
		}
		return -1;
	}
	
}
