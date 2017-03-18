import edu.princeton.cs.algs4.*;

public class MergeSort3Way {

	public static int max(int[] arr){
		int max = arr[0];
		for(int i = 1;i < arr.length;i++){
			if(arr[i] > max){
			  max = arr[i];
			}
		}
		return max;
	}

	public static int[] conquer(int[] left, int[] middle, int[] right){

		int[] merged_array = new int[left.length + middle.length + right.length];

		int maxLeft = max(left);
		int maxMiddle = max(middle);
		int maxRight = max(right);

		int[] tempLeft = new int[left.length+1];
		int[] tempMiddle = new int[middle.length+1];
		int[] tempRight = new int[right.length+1];

		int max = 0;

		if(maxLeft >= maxRight && maxLeft >= maxMiddle){
			max = maxLeft;
		}
		else if(maxRight >= maxLeft && maxRight >= maxMiddle){
			max = maxRight;
		}
		else{
			max = maxMiddle;
		}

		for(int p = 0; p < left.length; p++){
			tempLeft[p] = left[p];
		}
		tempLeft[left.length] = ++max;

		for(int p = 0; p < middle.length; p++){
			tempMiddle[p] = middle[p];
		}
		tempMiddle[middle.length] = ++max;

		for(int p = 0; p < right.length; p++){
			tempRight[p] = right[p];
		}
		tempRight[right.length] = ++max;

		int l = 0;
		int m = 0;
		int r = 0;

		for(int n = 0; n < merged_array.length; n++){

			/*

			System.out.println("l: " + tempLeft[l]);
			System.out.println("m: " + tempMiddle[m]);
			System.out.println("r: " + tempRight[r]);

			*/

			if(tempMiddle[m] >= tempLeft[l] && tempRight[r] >= tempLeft[l]){
				merged_array[n] = tempLeft[l++];
			}
			else if(tempLeft[l] >= tempMiddle[m] && tempRight[r] >= tempMiddle[m]){
				merged_array[n] = tempMiddle[m++];
			}
			else if(tempMiddle[m] >= tempRight[r] && tempLeft[l] >=tempRight[r]){
				merged_array[n] = tempRight[r++];
			}

		}

		return merged_array;

	}

	public static int[] mergeSort4two(int[] left, int[] right){
		int[] merged_array = new int[left.length + right.length];
		int l = 0;
		int r = 0;
		for(int n = 0; n < merged_array.length; n++){
			if(l>=left.length){
				merged_array[n] = right[r++];
			}
			else if(r>=right.length){
				merged_array[n] = left[l++];
			}
			else if(left[l]>=right[r]){
				merged_array[n] = right[r++];
			}
			else if(right[r]>left[l]){
				merged_array[n] = left[l++];
			}
		}
		return merged_array;
	}

	public static int[] divide(int[] arr){

		int length = arr.length;

		if(length <= 1) return arr;

		int leftLength = 0;
		int middleLength = 0;
		int rightLength = 0;

		if(length == 2) {
			int[] left = {arr[0]};
			int[] right = {arr[1]};
			return mergeSort4two(left, right);
		}
		else if(length % 3 == 0) {
			leftLength = length/3;
			middleLength = length/3;
			rightLength = length/3;
		}
		else {
			leftLength = length/3;
			middleLength = length/3;
			rightLength = length - 2*leftLength;
		}

		int[] left = new int[leftLength];
		int[] middle = new int[middleLength];
		int[] right = new int[rightLength];

		for(int i = 0; i < leftLength; i++){
			left[i] = arr[i];
		}

		for(int i = leftLength; i < 2*middleLength; i++){
			middle[i-leftLength] = arr[i];
		}

		for(int i = 2*middleLength; i < rightLength; i++){
			right[i-2*middleLength] = arr[i];
		}

		left = divide(left);
		middle = divide(middle);
		right = divide(right);

		return conquer(left, middle, right);

	}

	public static void main(String[] args){
		int[] arr = {8, 3, 5};

		int[] arr1 = divide(arr);

		for(int i = 0; i < arr1.length; i++){
			System.out.println(arr1[i]);
		}

		
	}

}