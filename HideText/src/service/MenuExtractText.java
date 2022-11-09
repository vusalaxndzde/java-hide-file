package service;

import service.inter.menu.MenuExtractTextInter;
import util.FileUtil;
import util.InputUtil;

public class MenuExtractText implements MenuExtractTextInter {

    @Override
    public void process() {
        extract();
    }
    public void extract() {
        String steqo_fayl = InputUtil.requiredText("Stego file: ");
        byte[] content = FileUtil.readBytes(steqo_fayl);
        byte[] key = hexStringToByteArray("FFD9");
        int index = findIndex(content, key);
        byte[] secretArr = new byte[content.length - index];

        for (int i = 0; index < content.length; i++, index++) {
            secretArr[i] = content[index];
        }
        FileUtil.writeBytes(FileUtil.newFileName(steqo_fayl, new MenuExtractText()), secretArr);
    }

    public int findIndex(byte[] content, byte[] key) {
        int index = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i] == key[0] && content[i + 1] == key[1]) {
                index = i + 2;
            }
        }
        return index;
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
