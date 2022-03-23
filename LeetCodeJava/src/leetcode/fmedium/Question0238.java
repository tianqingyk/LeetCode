package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/8/2022
 */
public class Question0238 {

    /**
     * 238. Product of Array Except Self
     */

    /**
     * Solution 1
     * Runtime: 5 ms, faster than 12.29% of Java online submissions for Product of Array Except Self.
     * Memory Usage: 52.5 MB, less than 79.12% of Java online submissions for Product of Array Except Self.
     */
    public int[] productExceptSelf(int[] nums) {
        if(nums.length == 1) return new int[]{0};
        if(nums.length == 2) return new int[]{nums[1], nums[0]};
        return productExceptSelf(nums, 0, nums.length-1);
    }

    private int[] productExceptSelf(int[] nums, int left, int right) {
        if(left == right){
            return new int[]{1, nums[left]};
        }
        if(left == right + 1){
            return new int[]{nums[left+1],nums[left], nums[left] * nums[left+1]};
        }


        int pivot = left + (right - left) / 2;
        int[] leftArray = productExceptSelf(nums, left, pivot);
        int leftProduct = leftArray[leftArray.length -1];
        int[] rightArray = productExceptSelf(nums, pivot+1,right);
        int rightProduct = rightArray[rightArray.length -1];

        int[] array;
        if(right - left == nums.length - 1) array = new int[right-left+1];
        else array = new int[right-left+2];

        for(int i = 0; i <= array.length-1; i++){
            if(i + left <= pivot) {
                array[i] = leftArray[i] * rightProduct;
            }else if(i+left <= right){
                array[i] = rightArray[i - leftArray.length+1] * leftProduct;
            }else{
                array[i] = leftProduct * rightProduct;
            }
        }

        return array;
    }

    /**
     * Solution 2
     * Left and Right product Lists
     */
    public int[] productExceptSelf2(int[] nums) {
        if(nums.length == 1) return new int[]{0};
        if(nums.length == 2) return new int[]{nums[1], nums[0]};

        int[] leftToRight = new int[nums.length];
        leftToRight[0] = 1;
        int[] rightToLeft = new int[nums.length];
        rightToLeft[nums.length-1] = 1;

        for(int i = 1;i< nums.length; i ++){
            leftToRight[i] = leftToRight[i-1] * nums[i-1];
            rightToLeft[nums.length - i -1] = rightToLeft[nums.length-i] * nums[nums.length-i];
        }

        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            result[i]= leftToRight[i] * rightToLeft[i];
        }
        return result;
    }

    /**
     * Solution 3
     * O(1) space approach
     */
    public int[] productExceptSelf3(int[] nums) {

        // The length of the input array
        int length = nums.length;

        // Final answer array to be returned
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}
