package util;

import java.util.Scanner;

public class InputUtil {

    public static String requiredText(String text) {
        System.out.println(text);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
