package edu.escuelaing.app;

import java.io.*;
import java.util.*;

public class LocalFileReader {

    public static void main(String[] args) {
        String directoryPath = "C:/Users/richy/Downloads/Different_Files/";
        List<File> files = listFiles(directoryPath); //list all files in the directory
        Map<String, String> extensions = new HashMap<>(); //Hash have all extensions
        extensions.put("html", "text");
        extensions.put("css", "text");
        extensions.put("js", "application");
        extensions.put("jpg", "image");
        extensions.put("jpeg", "image");
        extensions.put("png", "image");
        extensions.put("gif", "image");

        StringBuilder htmlContent = new StringBuilder(); //Build a index HTML
        htmlContent.append("<html><body>");

        for (File file : files) {
            String fileName = file.getName(); //Get file name
            String extension = getFileExtension(fileName);
            String fileType = extensions.getOrDefault(extension, "");
            String fileContent = readFileContent(file);

            htmlContent.append("<h2>").append(fileName).append("</h2>");

            if (fileType.equals("image")) {
                htmlContent.append("<img src=\"").append(fileName).append("\"/><br>");
            } else {
                htmlContent.append("<pre>").append(fileContent).append("</pre><br>");
            }
        }

        htmlContent.append("</body></html>");
        writeHTMLToFile(htmlContent.toString(), "index.html");

        System.out.println("Archivo HTML generado con éxito.");
    }

    private static List<File> listFiles(String directoryPath) {
        List<File> files = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();

        if (fileList != null) {
            files.addAll(Arrays.asList(fileList));
        }

        return files;
    }

    private static String readFileContent(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private static void writeHTMLToFile(String content, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}
