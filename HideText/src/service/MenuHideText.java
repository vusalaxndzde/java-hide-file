package service;

import service.inter.menu.MenuHideTextInter;
import util.FileUtil;
import util.InputUtil;

public class MenuHideText implements MenuHideTextInter {

    @Override
    public void process() {
        hide();
    }

    public void hide() {
        String container = InputUtil.requiredText("Text faylın gizlədilməsi üçün lazım olan şəkil: ");
        String steqo_file = newFileName(container);

        String filename = InputUtil.requiredText("Gizlənəcək text faylı: ");
        FileUtil.writeBytes(steqo_file, FileUtil.readBytes(container));
        byte[] secret_text = FileUtil.readBytes(filename);
        FileUtil.appendBytes(steqo_file, secret_text);
        System.out.println("Məxfi məlumat gizlədildi!");
    }

    public String newFileName(String filename) {
        String[] arr = filename.split("\\.");
        return arr[0] + " - new.jpg";
    }

}
