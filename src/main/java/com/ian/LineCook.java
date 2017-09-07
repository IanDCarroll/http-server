package com.ian;

public class LineCook {
    public static byte[] marinateBytes(byte[][] inputs) {
        final int firstIndexOfInput = 0;
        int appropriateIndexOfMarinade = 0;
        byte[] marinade = new byte[getRightSizedPot(inputs)];
        for (byte[] input : inputs) {
            System.arraycopy(input, firstIndexOfInput, marinade, appropriateIndexOfMarinade, input.length);
            appropriateIndexOfMarinade += input.length;
        }
        return marinade;
    }

    public static int getRightSizedPot(byte[][] inputs) {
        int potSize = 0;
        for (byte[] input : inputs) {
            potSize += input.length;
        }
        return potSize;
    }
}
