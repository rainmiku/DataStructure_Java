package com.package1;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTableExercise {

    public int mostFrequent(int[] numbers){

        Map<Integer,Integer> map = new HashMap<>();
        for (var number:numbers) {
            var count = map.getOrDefault(number,0);
            map.put(number,count+1);
        }

        int max = -1;
        int result = numbers[0];
        for (var item:map.entrySet()) {
            if (item.getValue() > max){
               max = item.getValue();
               result = item.getKey();
            }
        }
        return result;
    }

    public int countPairsWithDiff(int[] numbers, int k){
        
        Set<Integer> set = new HashSet<>();

        for (var number:numbers)
            set.add(number);

        int result = 0;
        for (var number:numbers)
        {
            if(set.contains(number+k))
                result++;
        }
        return result;
    }

    public int[] twoSum(int[] numbers, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <numbers.length ; i++) {
            if (!map.containsKey(numbers[i])){
                map.put(numbers[i],i);
            }
        }

        int [] result = new int[2];
        for (var item : map.entrySet()) {
            if (map.containsKey(target - item.getKey())){
                result[0] = item.getValue();
                result[1] = map.get(target-item.getKey());
            }
        }
        return result;
    }
}
