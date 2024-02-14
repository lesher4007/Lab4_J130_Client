package mainpack;

import java.io.*;
import java.net.Socket;


public class AppClientFileTCP {
    String host= "127.0.0.1";

    public  void start(int port){

        try(BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            while (!exit){
                    String message = console.readLine();
                    if(!message.equals("shutdown")){
                        Socket socket = new Socket(host, port);
                        handleSocket(socket,message);
                    }else {
                        exit=true;
                    }
            }
            }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void handleSocket(Socket s, String message){

        try(Socket socket = s;
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){

            writer.write(message);
            writer.flush();
            socket.shutdownOutput();
            String answer = reader.readLine();
            System.out.println(answer);

        }catch (IOException ioe){}


    }
}
