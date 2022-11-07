package util;

import service.MenuExitService;
import service.MenuExtractText;
import service.MenuHideText;
import service.MenuUnknownService;
import service.inter.Process;

public enum Menu {

    HIDE(1, "Hide text", new MenuHideText()),
    EXTRACT(2, "Extract text", new MenuExtractText()),
    EXIT(3, "Exit", new MenuExitService()),
    UNKNOWN(new MenuUnknownService());

    private int number;
    private String label;
    private Process service;

    Menu() {
    }

    Menu(Process service) {
        this.service = service;
    }

    Menu(int number, String label, Process service) {
        this.number = number;
        this.label = label;
        this.service = service;
    }

    public void process() {
        service.process();
        MenuUtil.showMenu();
    }

    @Override
    public String toString() {
        return number + ". " + label;
    }

    public static void showAllMenu() {
        for (Menu m : Menu.values()) {
            if (m != Menu.UNKNOWN) {
                System.out.println(m);
            }
        }
    }

    public static Menu findMenu(int selectedNumber) {
        for (Menu m : Menu.values()) {
            if (m != Menu.UNKNOWN && m.number == selectedNumber) {
                return m;
            }
        }
        return Menu.UNKNOWN;
    }



}
