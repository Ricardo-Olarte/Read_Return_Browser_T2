package edu.escuelaing.app;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Web server, por el puerto 37600
 * @author Luis Benavides
 * Se han realizado algunas modificaciones
 * @author Ricardo Olarte
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(37600);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 37600");
            System.exit(1);
        }
        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Ready for receive ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            String uriString = "";

            boolean firstLine = true;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if(firstLine){
                    firstLine = false;
                    uriString = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            System.out.println("URI: " + uriString);
            if(uriString.startsWith("/hello!")){
                outputLine = getHello(uriString);
            }else{
                outputLine = indexResponse();
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     *
     * @param uri
     * @return
     */
    public static String getHello(String uri){
        String name = uri.replace("/hello?name=", "");
        return "{ \"msg\": \"Hello " + name + "\"}";
    }

    /**
     * @return
     */
    public static  String indexResponse(){
        byte[] encodedBytes = new byte[0];
        try{
            encodedBytes = Files.readAllBytes(Paths.get("src/main/resources/index.html"));
        }catch (IOException e){
            e.printStackTrace();
        }
        String indexHTML = new String(encodedBytes, StandardCharsets.UTF_8);
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + indexHTML;
        return response;
    }
}