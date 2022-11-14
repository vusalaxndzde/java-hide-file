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

        String extKey = "@ext";
        int indexExt = findExtensionIndex(content, extKey);
        String extStr = getExtension(indexExt, content);

        String hexCode = "FFD9";
        byte[] secretArr = getSecretContent(hexCode, indexExt, extKey, content);
        FileUtil.writeBytes(FileUtil.newFileName(steqo_fayl, extStr), secretArr);
        System.out.println("Confidential file extracted!");
    }

    public byte[] getSecretContent(String hexCode, int indexExt, String extKey, byte[] content) {
        byte[] key = hexStringToByteArray(hexCode);
        int index = findSecretContentIndex(content, key);
        byte[] secretArr = new byte[indexExt - extKey.length() - index];
        for (int i = 0; i < secretArr.length; i++, index++) {
            secretArr[i] = content[index];
        }
        return secretArr;
    }

    public String getExtension(int indexExt, byte[] content) {
        byte[] extArr = new byte[content.length - indexExt];
        for (int i = 0; i < extArr.length; i++, indexExt++) {
            extArr[i] = content[indexExt];
        }
        return new String(extArr);
    }

    public int findSecretContentIndex(byte[] content, byte[] key) {
        int index = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i] == key[0]) {
                if (check(i, content, key)) {
                    index = i + key.length;
                }
            }
        }
        return index;
    }

    public int findExtensionIndex(byte[] content, String extKeyStr) {
        byte[] extKey = extKeyStr.getBytes();
        int index = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i] == extKey[0]) {
                if (check(i, content, extKey)) {
                    index = i + extKey.length;
                    break;
                }
            }
        }
        return index;
    }

    private boolean check(int index, byte[] content, byte[] extKey) {
        int keyIndex = 0;
        while (keyIndex != extKey.length) {
            if (extKey[keyIndex] != content[index]) {
                return false;
            }
            keyIndex++;
            index++;
        }
        return true;
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
