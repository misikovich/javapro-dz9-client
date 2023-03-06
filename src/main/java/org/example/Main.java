package org.example;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Main {
    static final int PORT = 8081;
    static final String quitCmd = "q:";
    public static void main(String[] args) {
        System.out.println("Client");

        Scanner sc = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", PORT)) {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            String serverLine;
            while (!(serverLine = bufferedReader.readLine()).equals("Бувай!")) {
                System.out.println("[server] > " + serverLine);
                System.out.print("> ");
                String userLine;
                if ((userLine = sc.nextLine()).equals(quitCmd)) break;
                printWriter.println(userLine);
            }

            printWriter.close();
            bufferedReader.close();
        } catch (SocketException e) {
            System.out.println("Server disconnected");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}