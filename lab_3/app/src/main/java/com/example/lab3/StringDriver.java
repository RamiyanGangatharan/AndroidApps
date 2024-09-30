package com.example.lab3;
import androidx.annotation.NonNull;

public class StringDriver
{
    @NonNull
    public static String stringToBinary(@NonNull String input)
    {
        StringBuilder binaryResult = new StringBuilder();

        for (char character : input.toCharArray())
        {
            String binaryChar = Integer.toBinaryString(character);
            String reversedBinary = reverseSequence(binaryChar);
            binaryResult.append(reversedBinary).append(" ");
        }
        return binaryResult.toString().trim();
    }

    // Converts binary to string
    @NonNull
    public static String binaryToString(@NonNull String binaryInput)
    {
        StringBuilder stringResult = new StringBuilder();
        String[] binaryArray = binaryInput.split(" ");

        for (String binary : binaryArray)
        {
            String reversedBinary = reverseSequence(binary);
            int charCode = Integer.parseInt(reversedBinary, 2);
            stringResult.append((char) charCode);
        }
        return stringResult.toString();
    }

    // Reverse the binary sequence for proper conversion
    @NonNull
    public static String reverseSequence(@NonNull String input)
    {
        char[] characters = input.toCharArray();
        int leftIndex = 0, rightIndex = characters.length - 1;

        while (leftIndex < rightIndex)
        { // Swap characters
            char tempChar = characters[leftIndex];
            characters[leftIndex] = characters[rightIndex];
            characters[rightIndex] = tempChar;
            leftIndex++;
            rightIndex--;
        }
        return String.valueOf(characters);
    }
}