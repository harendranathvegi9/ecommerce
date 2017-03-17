package com.aripd.util;

import java.text.Normalizer;

public class StringUtil {

    public static void main(String[] args) {
        System.out.println("slugify is " + slugify("  ÖÖßÖ     öçşığü   ÖÇŞİĞÜ        "));
        System.out.println(convertToLatin("  ÖÖßÖ  öçşığü ÖÇŞİĞÜ   "));
        System.out.println(camelCaseToUnderscore("DeNeMe1deNeMe2"));
        System.out.println(filenameToExtension("test1/test2/deneme1.txt"));
    }

    public static String camelCaseToUnderscore(String camelCase) {
        return camelCase.trim().replaceAll("(?<!^)[A-Z](?!$)", "_$0").toLowerCase();
    }

    public static String filenameToExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }

    public static String filenameToExtensionByContentType(String contentType) {
        switch (contentType) {
            case "application/pdf":
                return "pdf";
            case "application/vnd.ms-excel":
                return "xls";
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "xlsx";
            case "application/msword":
                return "doc";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "docx";
            case "image/jpeg":
                return "jpg";
            case "image/png":
            case "image/x-png":
                return "png";
            case "application/zip":
                return "zip";
            default:
                return null;
        }
    }

    public static String filenameToExtensionWithDot(String fileName) {
        return "." + filenameToExtension(fileName);
    }

    public static String slugify(String input) {
        String output = "";
        try {
            output = input.trim();
            output = convertToLatin(output);
            output = output.replaceAll("\\s+", " "); // removes duplicated whitespaces
            return output.replace(" ", "-").toLowerCase();
        } catch (NullPointerException ex) {
            return output;
        }
    }

    public static String convertToLatin(String input) {
        input = input.replace("ß", "ss");
        input = input.replace("ı", "i");
        input = input.replace("ğ", "g");
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9 ]", "");
    }

}
