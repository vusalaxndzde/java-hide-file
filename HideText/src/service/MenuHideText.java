package service;

import bean.Config;
import service.inter.menu.MenuHideTextInter;
import util.FileUtil;
import util.InputUtil;

public class MenuHideText implements MenuHideTextInter {

    @Override
    public void process() {
        hide();
    }

    public void hide() {
        String container = InputUtil.requiredText("The image needed to hide the text file: ");
        String steqo_file = FileUtil.newFileName(container, FileUtil.getExtension(container));
        FileUtil.writeBytes(steqo_file, FileUtil.readBytes(container));

        String filename = InputUtil.requiredText("Text file to hide: ");
        byte[] secret_text = FileUtil.readBytes(filename);
        FileUtil.appendBytes(steqo_file, secret_text);

        String fileExtension = FileUtil.getExtension(filename);
        FileUtil.appendBytes(steqo_file, (Config.getExtensionKey() + fileExtension).getBytes());
        System.out.println("Confidential file hidden!");
    }

}
