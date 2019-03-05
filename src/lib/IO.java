package lib;

import java.util.Scanner;

public abstract class IO {

    public static void println(Object str) {
        System.out.println(str);
    }

    public static void println(String format, Object... args) {
        System.out.printf(format + "%n", args);
    }

    public static void print(Object str) {
        System.out.print(str);
    }

    public static void print(String format, Object... args) {
        System.out.format(format, args);
    }

    public static void printf(String format, Object... args) {
        print(format, args);
    }

    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static byte scanByte() {
        return scanner().nextByte();
    }

    public static short scanShort() {
        return scanner().nextShort();
    }

    public static int scanInt() {
        return scanner().nextInt();
    }

    public static long scanLong() {
        return scanner().nextLong();
    }

    public static float scanFloat() {
        return scanner().nextFloat();
    }

    public static double scanDouble() {
        return scanner().nextDouble();
    }

    public static char scanChar() {
        return scanNext().charAt(0);
    }

    public static boolean scanBoolean() {
        char answer = scanNext().charAt(0);
        return Character.isLetterOrDigit(answer) && (Character.toUpperCase(answer) == 'Y' || Character.toUpperCase(answer) == 'T' || Character.digit(answer, 2) == 1);
    }

    public static String scanNext() {
        return scanner().next();
    }

    public static String scanNextLine() {
        return scanner().nextLine();
    }

    public static byte scanByte(String message) {
        print(message);
        return scanByte();
    }

    public static short scanShort(String message) {
        print(message);
        return scanShort();
    }

    public static int scanInt(String message) {
        print(message);
        return scanInt();
    }

    public static long scanLong(String message) {
        print(message);
        return scanLong();
    }

    public static float scanFloat(String message) {
        print(message);
        return scanFloat();
    }

    public static double scanDouble(String message) {
        print(message);
        return scanDouble();
    }

    public static char scanChar(String message) {
        print(message);
        return scanChar();
    }

    public static boolean scanBoolean(String message) {
        String answerFormat = " [( Y )es / ( T )rue / ( 1 )] ";
        print(message + answerFormat);
        return scanBoolean();
    }

    public static String scanNext(String message) {
        print(message);
        return scanNext();
    }

    public static String scanNextLine(String message) {
        print(message);
        return scanNextLine();
    }

    public static byte scanByte(String message, Object... args) {
        print(message, args);
        return scanByte();
    }

    public static short scanShort(String message, Object... args) {
        print(message, args);
        return scanShort();
    }

    public static int scanInt(String message, Object... args) {
        print(message, args);
        return scanInt();
    }

    public static long scanLong(String message, Object... args) {
        print(message, args);
        return scanLong();
    }

    public static float scanFloat(String message, Object... args) {
        print(message, args);
        return scanFloat();
    }

    public static double scanDouble(String message, Object... args) {
        print(message, args);
        return scanDouble();
    }

    public static char scanChar(String message, Object... args) {
        print(message, args);
        return scanChar();
    }

    public static boolean scanBoolean(String message, Object... args) {
        String answerFormat = " [( Y )es / ( T )rue / ( 1 )] ";
        print(message + answerFormat, args);
        return scanBoolean();
    }

    public static String scanNext(String message, Object... args) {
        print(message, args);
        return scanNext();
    }

    public static String scanNextLine(String message, Object... args) {
        print(message, args);
        return scanNextLine();
    }

}
