package lt.esdc.shapes.util;

public class IDGenerator {
    private static long counter = 1;

    public static long generateId() {
        return counter++;
    }
}
