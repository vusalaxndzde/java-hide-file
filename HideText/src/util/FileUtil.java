package util;

import service.MenuExtractText;
import service.MenuHideText;
import service.inter.Process;

import java.io.*;

public class FileUtil {

    public static byte[] readBytes(String filename) {

        try (FileInputStream reader = new FileInputStream(filename)) {
            return reader.readAllBytes();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }

    private static void writeBytes(String filename, byte[] arr, boolean append) {
        try (FileOutputStream writer = new FileOutputStream(filename, append)) {
            writer.write(arr);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void appendBytes(String filename, byte[] arr) {
        writeBytes(filename, arr, true);
    }

    public static void writeBytes(String filename, byte[] arr) {
        writeBytes(filename, arr, false);
    }

    public static String newFileName(String filename, Process obj) {
        String[] arr = filename.split("\\.");
        if (obj instanceof MenuHideText) {
            return arr[0] + " - new.jpg";
        } else if (obj instanceof MenuExtractText) {
            return arr[0] + " - new.docx";
        }
        return null;
    }

}
