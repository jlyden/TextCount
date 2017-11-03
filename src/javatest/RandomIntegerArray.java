package javatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lydenj
 * @since 10/31/2017
 */
public class RandomIntegerArray {

    public static void main (String[] args){
        int[] randomArray = generateUniqueRandomValues(1,50, 5);
        System.out.println("Bryant's randomArray is " + randomArray);
        List<Integer> randomList = generateUniqueRandomArrayFromRange(1, 50, 5);
        System.out.println("My randomList is " + randomList);
    }

    private static int getRandomInteger(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

	// Bryant's original
    private static int[] generateUniqueRandomValues(int min, int max, int length) {
        //Declare the array and then fill it's first index with a random value
        int[] array = new int[length];
        array[0] = getRandomInteger(min, max);

        //Enter a loop that generates a random value and checks that it is not already present in the array
        int i = 1;
        while( i<length) {
            Integer possibleNum = getRandomInteger(min, max);
            boolean safeToAdd = true;
            for(int j = 0; j<i; j++) {
                if (possibleNum.equals(array[j])) {
                    safeToAdd = false;
                }
            }
            if(safeToAdd) {
                array[i] = possibleNum;
                i++;
            }

        }
        return array;
    }

	// My version
    // Generates ordered array, then pops values at random into a new array
    private static List<Integer> generateUniqueRandomArrayFromRange(int min, int max, int length) {
        List<Integer> orderedList = IntStream.rangeClosed(min, max)
                .boxed().collect(Collectors.toList());
        List<Integer> randomList = new ArrayList<>();
        for (int i=0; i<length; i++) {
            int randomIndex = getRandomInteger(0,orderedList.size()-1);
            int randomValue = orderedList.remove(randomIndex);
            randomList.add(randomValue);
        }
        return randomList;
    }

}
