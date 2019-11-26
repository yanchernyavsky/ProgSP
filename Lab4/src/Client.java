

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8006);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully connected to " + socket.getInetAddress().toString() + ":" + socket.getPort());

        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\\? - info");
        Scanner consoleIn = new Scanner(System.in, "UTF-8");


        boolean flag = true;
        while (flag){
            String request = consoleIn.nextLine();
            try {
                writer.write(request);
                writer.newLine();
                writer.flush();
                if(request.equals("Quit")) break;

                do{
                    String answer = reader.readLine();
                    System.out.println(answer);
                }while (reader.ready());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}