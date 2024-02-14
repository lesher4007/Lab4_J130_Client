package mainpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AppClientFileUDP {
    private byte[] buff =  new byte[256];
    private int[] timeouts = {10, 200, 300, 400, 500};

    public  void start(int port){

        try(BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            System.out.println("Client starts");
            while (!exit){
                String message = console.readLine();
                if(!message.equals("shutdown")){
                    DatagramSocket socket = new DatagramSocket();
                    handleSocket(socket,message,port);
                }else {
                    exit=true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void handleSocket(DatagramSocket s, String mess, int port){
        try {
            byte[] message = mess.getBytes();
            DatagramPacket packetToServer = new DatagramPacket(message, message.length, InetAddress.getByName("127.0.0.1"), port);
                for (int timeout : timeouts) {
                    try {
                    s.setSoTimeout(timeout);
                    s.send(packetToServer);
                    DatagramPacket packetFromServer = new DatagramPacket(buff, buff.length);
                    s.receive(packetFromServer);
                    System.out.println(new String(packetFromServer.getData(), 0, packetFromServer.getLength()));
                    break;
                    } catch (IOException e) {
                        System.out.println("Fail: too long waiting");
                    }
                }
        } catch (Exception e) {}
    }
}
