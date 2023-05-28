package classes;

import java.util.Arrays;



public class ArrayFunctions{
    //creates a new array and appends the new value at the end of it
    public static IssueBean[] appendToArray(IssueBean[] array, IssueBean val)
        {
            array = Arrays.copyOf(array,array.length + 1);
            array[array.length -1] = val;
            return array;
        }
}