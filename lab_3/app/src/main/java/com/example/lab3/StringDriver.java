package com.example.lab3;

import androidx.annotation.NonNull;

public class StringDriver {
    @NonNull
    public static String stringToBinary(@NonNull String input) {
        StringBuilder binaryResult = new StringBuilder();

        for (char character : input.toCharArray()) {
            String binaryChar = Integer.toBinaryString(character);
            String paddedBinary = String.format("%8s", binaryChar).replace(' ', '0');
            binaryResult.append(paddedBinary).append(" ");
        }
        return binaryResult.toString().trim();
    }

    @NonNull
    public static String binaryToString(@NonNull String binaryInput) {
        StringBuilder stringResult = new StringBuilder();
        String[] binaryArray = binaryInput.split(" ");

        for (String binary : binaryArray) {
            int charCode = Integer.parseInt(binary, 2);
            stringResult.append((char) charCode);
        }
        return stringResult.toString();
    }
}