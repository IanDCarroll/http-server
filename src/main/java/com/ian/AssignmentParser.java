package com.ian;

public class AssignmentParser {
    private static final String emptyString = "";
    private static final String assignmentOperator = "=";
    private static final String expandedAssignment = " = ";

    public static String[] expandAssignmentOperators(String[] unexpandedParams) {
        String[] expandedParams = new String[unexpandedParams.length];
        for (int i = 0; i < unexpandedParams.length; i++) {
            expandedParams[i] = expandOneAssignmentOperator(unexpandedParams[i]);
        }
        return expandedParams;
    }

    public static String expandOneAssignmentOperator(String oneUnexpandedParam) {
        StringBuilder oneExpandedParam = new StringBuilder();
        String[] paramLetters = oneUnexpandedParam.split(emptyString);
        boolean foundAssignmentOperator = false;
        for (String letter : paramLetters) {
            if (foundAssignmentOperator) {
                oneExpandedParam.append(letter);
            } else {
                foundAssignmentOperator = thisIsTheAssignmentOperator(letter);
                oneExpandedParam.append(expandIfAssignmentOperator(letter));
            }
        }
        return oneExpandedParam.toString();
    }

    public static String expandIfAssignmentOperator(String letter) {
        return (thisIsTheAssignmentOperator(letter)) ? expandedAssignment : letter;
    }

    public static boolean thisIsTheAssignmentOperator(String letter) {
        return letter.equals(assignmentOperator);
    }
}
