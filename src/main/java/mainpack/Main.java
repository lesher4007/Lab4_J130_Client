package mainpack;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Input a port: ");
        int num = in.nextInt();

//      new AppClientFileTCP().start(num);
        new AppClientFileUDP().start(num);
    }
}