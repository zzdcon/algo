package algo.contest;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

class CreateTargetArray {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<index.length; i++) {
            list.add(index[i], nums[i]);
        }
        int[] targets = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            targets[i] = list.get(i);
        }
        return targets;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(new CreateTargetArray().createTargetArray(new int[]{1,2,3,4,0},
                new int[]{0,1,2,3,0})));
    }
}