package com.example.lab3;

import androidx.annotation.NonNull;

public class DecimalDriver {
    /**
     * <a href="https://www.geeksforgeeks.org/java-program-for-decimal-to-binary-conversion/">...</a>
     * Converts a string value or decimal number to its binary representation.
     *
     * @param decimalNumber The decimal number to be converted to binary.
     * @return A string representing the binary equivalent of the given decimal number.
     */
    @NonNull
    public static String decimalToBinary(int decimalNumber) {
        int[] binaryRepresentation = new int[1000];
        int index = 0;

        // Convert decimal number to binary by dividing it by 2 and storing remainders
        while (decimalNumber > 0) {
            binaryRepresentation[index] = decimalNumber % 2;
            decimalNumber = decimalNumber / 2;
            index++;
        }

        StringBuilder binaryString = new StringBuilder();
        for (int j = index - 1; j >= 0; j--) {
            binaryString.append(binaryRepresentation[j]);
        }
        return binaryString.toString();
    }

    /**
     * Converts a binary number string to its decimal equivalent.
     *
     * @param binaryNumber A string representing a binary number to be converted to decimal.
     * @return The decimal equivalent of the binary number.
     */
    public static int binaryToDecimal(@NonNull String binaryNumber) {
        int decimalValue = 0;
        int length = binaryNumber.length();

        // Convert each binary digit to its corresponding decimal value
        for (int i = 0; i < length; i++) {
            if (binaryNumber.charAt(length - i - 1) == '1') {
                decimalValue += (int) Math.pow(2, i);
            }
        }
        return decimalValue;
    }
}