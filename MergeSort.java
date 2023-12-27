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
		
		if (inputLength < 2) {
			return;
		}
		
		int midIndex = inputLength / 2;
		
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[inputLength - midIndex];
		
		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = inputArray[i];
		}
		
		for (int i = midIndex; i < inputLength; i++) {
			rightHalf[i - midIndex] = inputArray[i];
		}
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		merge(inputArray, leftHalf, rightHalf);
	}
	
	public static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
		int l = 0;
		int r = 0; 
		int k = 0; 
		
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
