package service;

import util.FileUtil;
import util.InputUtil;

public class ExtractText {

    public static void extract() {
        String steqo_fayl = InputUtil.requiredText("Steqo fayl: ");
        byte[] content = FileUtil.readBytes(steqo_fayl);
        byte[] key = hexStringToByteArray("FFD9");
        int index = findIndex(content, key);
        byte[] secretArr = new byte[content.length - index];

        for (int i = 0; index < content.length; i++, index++) {
            secretArr[i] = content[index];
        }
        FileUtil.writeBytes(newFileName(steqo_fayl), secretArr);
    }

    public static int findIndex(byte[] content, byte[] key) {
        int index = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i] == key[0] && content[i + 1] == key[1]) {
                index = i + 2;
            }
        }
        return index;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String newFileName(String filename) {
        String[] arr = filename.split("\\.");
        return arr[0] + " - new.docx";
    }

}
