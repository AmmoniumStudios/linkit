package org.ammonium.linkit.util;

import java.util.random.RandomGenerator;

public class CodeGenerator {

    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static char RandomNumberGenerator() {
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        int index = randomGenerator.nextInt(0, ALPHANUMERIC.length());
        return ALPHANUMERIC.charAt(index);
    }

    public static String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 1; i < 12; i++) {
            code.append(RandomNumberGenerator());
        }
        return code.toString();
    }
}
