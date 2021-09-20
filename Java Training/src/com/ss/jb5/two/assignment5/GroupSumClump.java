/**
 * recursive GroupSumClump method to see if a sum can be given of clumps of the same integer
 */
package com.ss.jb5.two.assignment5;

/**
 * @author alecs
 *
 */
public class GroupSumClump {

	public Boolean groupSumClump(int start, int[] numbers, int target) {
		if (start >= numbers.length)
			return (target == 0) ? true : false;
		int clumpEnd = start;
		while (++clumpEnd < numbers.length && numbers[start] == numbers[clumpEnd]);
		if (groupSumClump(clumpEnd, numbers, target - ((clumpEnd - start) * numbers[start])))
			return true;
		return groupSumClump(clumpEnd, numbers, target);
	}

}
