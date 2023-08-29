package edu.escuelaing.app;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Class about reading about local file
 */
public class LocalFileReader {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String directoryPath = "src/main/resources/Different_Files/";
        List<File> files = listFiles(directoryPath); //list all files in the directory
        Map<String, String> extensions = new HashMap<>(); //Hash have all extensions
        extensions.put("html", "text");
        extensions.put("css", "text");
        extensions.put("js", "application");
        extensions.put("jpg", "image");
        extensions.put("jpeg", "image");
        extensions.put("png", "image");
        extensions.put("gif", "image");

        StringBuilder htmlContent = new StringBuilder(); //Build index HTML
        htmlContent.append("<html><body>");

        for (File file : files) {
            String fileName = file.getName(); //Get file name
            String extension = getFileExtension(fileName); //Extension '.' ; html, css, js, jpg, jpeg, png, gif
            String fileType = extensions.getOrDefault(extension, "");
            String fileContent = readFileContent(file);

            htmlContent.append("<h2>").append(fileName).append("</h2>");

            if (fileType.equals("image")) {
                byte[] img64 = new byte[0];
                try {
                    img64 = FileUtils.readFileToByteArray(new File("src/main/resources/Different_Files/" + fileName)); // code inspirado de: https://www.baeldung.com/java-base64-image-string
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String encodedString = Base64.getEncoder().encodeToString(img64);
                htmlContent.append("<img src=\"data:image/png;base64,").append(encodedString).append("\"/><br>");
            }else {
                htmlContent.append("<pre>").append(fileContent).append("</pre><br>");
            }
        }

        htmlContent.append("</body></html>");
        writeHTMLToFile(htmlContent.toString(), "index.html");

        //System.out.println("Archivo HTML generado con éxito.");
    }

    /**
     * Search all files, and list
     * @param directoryPath
     * @return
     */
    private static List<File> listFiles(String directoryPath) {
        List<File> files = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        if (fileList != null) {
            files.addAll(Arrays.asList(fileList));
        }
        return files;
    }

    /**
     * @param file
     * @return
     */
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

    /**
     * @param content
     * @param fileName
     */
    private static void writeHTMLToFile(String content, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets file extension after dot '.'
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            return fileName.substring(i + 1).toLowerCase();
        }
        return "";
    }
}
