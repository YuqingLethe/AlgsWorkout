package Array;

/**
 * Created by yuqing on 1/31/21.
 */
public class Array1437CheckIfAllOnesAtLeastLengthKPlacesAway {
    public boolean kLengthApart(int[] nums, int k) {
        int i = 0;
        while (nums[i] == 0) {
            i++;
        }
        while (i < nums.length) {
            if (nums[i] == 1) {
                if (i == nums.length - 1) {
                    break;
                }
                System.out.println(i);
                int count = 0;
                i ++;
                while (i < nums.length && nums[i] == 0 ) {
                    System.out.println("-" + i);
                    count ++;
                    i ++;
                }
                if (count < k) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean WhyThisTimeout(int[] nums, int k) {
        int i = 0;
        while (nums[i] == 0) {
            i++;
        }
        while (i < nums.length) {
            if (nums[i] == 1 && i == nums.length - 1) {
                System.out.println(i);
                int count = 0;
                i ++;
                while (i < nums.length && nums[i] == 0 ) {
                    System.out.println("-" + i);
                    count ++;
                    i ++;
                }
                if (count < k) {
                    return false;
                }
            }
        }
        return true;
    }
}
