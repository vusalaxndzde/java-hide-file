package util;

import java.util.Scanner;

public class MenuUtil {

    public static void showMenu() {
        Menu.showAllMenu();
        Scanner sc = new Scanner(System.in);
        System.out.print("Select menu: ");
        int selectedNumber = sc.nextInt();
        Menu menu = Menu.findMenu(selectedNumber);
        menu.process();
    }

}
