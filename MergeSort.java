package Sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] inputArray = {4, 6, 2, 3, 1, 7, 8, 9, 10, 5};
		mergeSort(inputArray);
		System.out.println(Arrays.toString(inputArray));
	}
	
	public static void mergeSort(int[] inputArray) {
		int inputLength = inputArray.length;
		
		//Recursion stops if either of the two sides only has 1 element.
		if (inputLength < 2) { 
			return;
		}
		
		int midIndex = inputLength / 2; 
		
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[inputLength - midIndex];
		
		//Initializing the left half
		for (int i = 0; i < midIndex; i++) { 
			leftHalf[i] = inputArray[i];
		}

		//Initializing the right half
		for (int i = midIndex; i < inputLength; i++) { 
			rightHalf[i - midIndex] = inputArray[i];
		}
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		//If every half only has 1 element, they can be merged.
		merge(inputArray, leftHalf, rightHalf); 
	}
	
	public static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
		int l = 0; //left half iterator
		int r = 0; //right half iterator
		int k = 0; //inputArray iterator
		
		//While both halfs still have elements, this loop runs.
		while (l < leftHalf.length && r < rightHalf.length) {
			if (leftHalf[l] <= rightHalf[r]) {
				inputArray[k] = leftHalf[l];
				l++;
			}else {
				inputArray[k] = rightHalf[r];
				r++;
			}
			k++;
		}
		
		//If the left half still has elements, this loop will finish the merging, else the next loop
		//will finish right half.
		while (l < leftHalf.length) {
			inputArray[k] = leftHalf[l];
			l++;
			k++;
		}
		
		while (r < rightHalf.length) {
			inputArray[k] = rightHalf[r];
			r++;
			k++;
			}
	}

}
