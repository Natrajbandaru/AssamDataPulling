package net.javaguides.springboot.controller;

import java.util.Arrays;

public class DSA {

	public static int  mainfun(int n) {
		if(n<2) {
			return n;
		}
		int a= mainfun(n-1)+mainfun(n-2);
		return a;
	}
   public static int  mainfunloop(int n) {
		int val=0;
		int a=0;
		for(int i=1;i<n;i++) {
			val = val+i;
		}
		return val;
	}
   
   public static void sumofdigits(int num) {
	   int total=0;
	   while(num!=0) {
		   total=total+num%10;
		   num=num/10;
	   }
	   System.out.println(total);
   }
   public static Integer sumofRecur(int num) {
	   if(num==0) {
		   return 0;
	   }
	   return  sumofRecur(num/10) + num%10 ;
   }
   
   public static  void reverse(int num) {
	   int rev = 0;
	   while(num!=0) {
	     rev = rev * 10 + num%10; 
	     num=num/10;
	   }
	   System.out.println(rev);
   }
   
   public static Integer reverseData(int num) {
	   
	   if(num==0) {
		   return 1;
	   }
	   
	   return num%10*10 + reverseData(num/10);
   }
   
   public static void intarr(int arr[][], int target) {
	   int[] arra=new int[2];
	   for(int i=0;i<arr.length;i++) {
		   for(int j=0;j<arr[i].length;j++) {
			   if(arr[i][j]==target) {
				   arra[0]=i+1;
				   arra[1]=j+1;
			   }
		   }
		   
	   }
	   System.out.println(Arrays.toString(arra));
   }
   
   public static void binaryserach(int[] arr,int target) {
	   int ans = -1,low=0,high=arr.length-1;
	   
	   while(low<=high) {
		   int mid=(low+high)/2;

		   if(target < arr[mid]) {
			   high = mid-1;
		   }
		   else if(target>arr[mid]) {
			   low = mid+1;
		   }
		   else {
			   ans = mid;
			   break;
		   }
	   }
	   System.out.println(ans);
   }
   
   
   public static int findpevot(int[] arr) {
	   int low=0,high=arr.length-1;
	   while(low<high) {
		   int mid=(low+high)/2;
           if(mid<high && arr[mid]>arr[mid+1]) {
        	   return arr[mid];
           }
           else if(mid>low && arr[mid] <arr[mid-1]) {
        	   return arr[mid-1];
           }
           if(arr[low]>arr[mid]) {
        	   high=mid-1;
           }
           else if(arr[low]<arr[mid]) {
        	   low= mid+1;
           }
	   }
	   return arr[arr.length-1];
   }
   
   public static void bubboleSort(int[] arr) {
	   for(int i=0;i<arr.length;i++) {
		   for(int j=0;j<arr.length-1-i;j++) {

			   if(arr[j] > arr[j+1]) {
				   int temp = arr[j];
				   arr[j]   = arr[j+1];
				   arr[j+1] = temp;
			   }
		   }
	   }
   }
   
   public static int maxone(int[] arr, int low, int high) {
	   int num=arr[0];
	   int index=0;
	   for(int i=0;i<=high;i++) {
		   if(num < arr[i]) {
			   num = arr[i];
			   index=i;
		   }
	   }
	   return index;
   }
   
   public static void selectionsort(int[] arr) {
	   
	   int i=0;
	   int last=arr.length-1;
	   while(i<arr.length) {
		   int maxone = maxone(arr,0,last);
		   
		    int temp=arr[last];
		    arr[last]=arr[maxone];
		    arr[maxone]=temp;
		   //arr[last]=arr[maxone];
		   last--;
		   i++;
	   }
	   
	   System.out.println(Arrays.toString(arr));
   }
   
   public static void cyclicsort1(int[]  arr) {

	  int i=0;
	  while(i < arr.length) {
		  int index=arr[i];
		  if(arr[i]!=arr[index]) {
			  System.out.println("pp");
			  int temp  = arr[index];
			  arr[index] = arr[i];
			  arr[i]=temp;
		  }
		  i++;
	  }
	  System.out.println(Arrays.toString(arr));
   }
   public static void cyclicSort(int[] nums) {
       int i = 0;
       while (i < nums.length) {
           int correctIndex = nums[i] - 1; // Correct index for nums[i]
           if (nums[i] != nums[correctIndex]) { // If nums[i] is not in its correct position
               swap(nums, i, correctIndex); // Swap nums[i] with the element at its correct index
           } else {
               i++; // Move to the next element
           }
       }
       System.out.println(Arrays.toString(nums));
   }
   
   public static int cyclic(int[] arr) {
	   
	   int i=0;
	   
	   while(i<arr.length) {
		   int correctindex= arr[i];
		   if(arr[i] != arr[correctindex]) {
			   int temp=arr[correctindex];
			   arr[correctindex]=arr[i];
			   arr[i]=temp;
		   }
		   else {
			   return arr[i];
		   }
		   i++;
	   }
	   System.out.println(Arrays.toString(arr));
	return i;
   }
   
   

   private static void swap(int[] nums, int i, int j) {
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
   }
   
   
   public static void subset(String data) {
		  System.out.println(data);

	  if(data.isEmpty()) {
		  System.out.println(data);
		  return;
	  }
	  char ch = data.charAt(0);
	  
	  if(ch=='a') {
		  subset(data.substring(1));
	  }else {
		  subset(ch+data.substring(1));
	  }
   }
   
   public static void combination(String process,String unprocess) {
	   
	   if(unprocess.isEmpty()) {
		   System.out.println(process);
		   return;
	   }
	   
	   char ch = unprocess.charAt(0);
	   
	   combination(process+ch, unprocess.substring(1));
	   combination(process, unprocess.substring(1));
   }
   
   public static void permitation(String process, String unprocess) {
	   if(unprocess.isEmpty()) {
		   System.out.println(process);
		   return;
	   }
	   char ch = unprocess.charAt(0);
	   for(int i=0; i<=process.length();i++) {
		   String first = process.substring(0,i);
		   String secound = process.substring(i,process.length());
		   permitation(first+ch+secound,unprocess.substring(1));
	   }
   }
   
   public static Integer maze(int row, int col) {
	   
	   if(row>=2 && col>=2) {
		   return 1;
	   }
	   Integer maze1 =1;
	   Integer maze2=1;
	   if(row < 2) {
		    maze1 = maze(row+1,col);
	   }
	   else if(col < 2) {
		    maze2 = maze(row,col+1);
	   }
	   
	   return maze1+maze2;
	   
   }
   
   
   public static Integer maze12(int row, int col) {
	   
	   if(row==1 || col==1) {
		   return 1;
	   }
//	   Integer maze1 =1;
//	   Integer maze2=1;
//	   if(row < 2) {
		   Integer   maze1 = maze(row-1,col);
//	   }
//	   else if(col < 2) {
		   Integer   maze2 = maze(row,col-1);
	 //  }
	   
	   return maze1+maze2;
	   
   }
   
//   public static void maze132(int row, int col,String root) {
//	   
//	   if(row==1 && col==1) {
//		   System.out.println(root);
//		   return ;
//	   }
//
//	   if(row > 1) {
//		    maze132(row-1,col ,root+"D");
//	   }
//	   else if(col > 1) {
//		    maze132(row,col-1,root+"R");
//	   }
//	   
//   }
//   
//   public static void maze132(int row, int col, String root) {
//	    if (row == 1 && col == 1) {
//	        System.out.println(root);
//	        return;
//	    }
//
//	    if (row > 1) {
//	        maze132(row - 1, col, root + "D");
//	    }
//	    if (col > 1) {
//	        maze132(row, col - 1, root + "R");
//	    }
//	}
   
   
   
   

   
	public static void main(String[] args) {
	//	maze132(3,3,"");
	//   String maze132 = maze132(3,3,"");
	   
		//System.out.println(maze);
	//	permitation("","abc");
	//	System.out.println(cyclic);
	}
}
