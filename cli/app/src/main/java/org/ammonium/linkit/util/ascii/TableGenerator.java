package org.ammonium.linkit.util.ascii;

import java.util.List;

public final class TableGenerator {


    /**
     * Generate an ASCII table as a string.
     *
     * @param headers Header of the table
     * @param data    The data used in the table
     * @return String representation of the ASCII table
     */
    public static String generateTable(List<String> headers, List<List<String>> data) {

        int[] maxWidths = getMaxWidth(headers, data);

        StringBuilder builder = new StringBuilder();
        builder.append(addNewLine(headers, maxWidths));
        builder.append(createRule(maxWidths));

        for (List<String> datum : data) {
            builder.append(addNewLine(datum, maxWidths));
        }

        return builder.toString();
    }

    private static String addNewLine(List<String> row, int[] maxWidths) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < row.size(); i++) {

            String dataPiece = row.get(i);
            builder.append("| %s ".formatted(dataPiece));
            int lengthDifference = maxWidths[i] - dataPiece.length();
            builder.append(" ".repeat(Math.max(0, lengthDifference)));
        }
        builder.append("|\n");

        return builder.toString();
    }

    private static int[] getMaxWidth(List<String> headers, List<List<String>> data) {

        int[] maxWidth = new int[headers.size()];

        // Iterates over each column
        for (int i = 0; i < maxWidth.length; i++) {

            // Checks width of header
            maxWidth[i] = headers.get(i).length();
        }

        for (List<String> row : data) {

            for (int j = 0; j < row.size(); j++) {

                if (row.get(j).length() > maxWidth[j])
                    maxWidth[j] = row.get(j).length();
            }
        }

        return maxWidth;
    }

    private static String createRule(int[] maxWidths) {

        StringBuilder builder = new StringBuilder();

        for (Integer maxWidth : maxWidths) {

            builder.append("|-");
            builder.append("-".repeat(Math.max(0, maxWidth)));
            builder.append("-");
        }

        builder.append("|\n");
        return builder.toString();
    }
}
