package Client;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Noah-Josua Hartmann
 * @created 08/04/2022 - 11:42
 * @project Client_Server_Application
 */

public class ClientMain {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Please specify a username you want to use: ");
        final String USERNAME = SCANNER.nextLine();
        System.out.println("Trying to connect ... hold on!");
        new Client("localhost", 4001, USERNAME);
    }
}

