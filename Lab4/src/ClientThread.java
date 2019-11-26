
import java.io.*;
import java.net.Socket;

class ClientThread implements Runnable{
    private Socket clientSocket;
    private int id;
    private String serverInfo = "Input '\\?', Add arg1 [argn]', 'Show' or 'Quit'.";
    ClientThread(Socket socket, int id)
    {
        this.clientSocket = socket;
    }
    public void run(){
        BufferedWriter writer;
        BufferedReader reader;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            clientSocket.getOutputStream()));
            reader = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        boolean flag = true;
        while (flag) {
            try {
                String[] input = reader.readLine().split(" ");
                if(input.length != 0)
                    switch (input[0]){
                        case "\\?":
                            writer.write(serverInfo);
                            break;
                        case "Add":
                            if(input.length == 3) {
                                DB_Controller.Add(input[1], input[2]);
                                writer.write("Successfully updated.");
                                writer.newLine();
                            }
                            else
                                writer.write("Invalid syntax. " + serverInfo);
                            break;
                        case "Search":
                            if(input.length == 2) {
                                DB_Controller.Search(input[1]);
                                try {
                                    while (DB_Controller.resultSet.next()) {
                                        int id = DB_Controller.resultSet.getInt(1);
                                        String name = DB_Controller.resultSet.getString(2);
                                        String adress = DB_Controller.resultSet.getString(3);
                                        writer.write("Id: " + id + "\tName: " + name + "\tCount: " + adress);
                                        writer.newLine();
                                    }
                                } catch (java.sql.SQLException e){
                                    e.printStackTrace();
                                }
                            }
                            else
                                writer.write("Invalid syntax. " + serverInfo);
                            break;
                        case "Show":
                            DB_Controller.show();
                            try {
                                while (DB_Controller.resultSet.next()) {
                                    int id = DB_Controller.resultSet.getInt(1);
                                    String name = DB_Controller.resultSet.getString(2);
                                    String adress = DB_Controller.resultSet.getString(3);
                                    writer.write("Id: " + id + "\tName: " + name + "\tCount: " + adress);
                                    writer.newLine();
                                }
                            } catch (java.sql.SQLException e){
                                e.printStackTrace();
                            }
                            break;
                        case "Quit":
                            flag = false;
                            break;
                        default:
                            writer.write("Invalid syntax. " + serverInfo);
                            break;
                    }
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
            reader.close();
            clientSocket.close();
            System.out.println("Client #" + id + " successfully disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}