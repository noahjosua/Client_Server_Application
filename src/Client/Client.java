package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Noah-Josua Hartmann
 * @created 08/04/2022 - 11:42
 * @project Client_Server_Application
 */

public class Client {
    private Socket socket;
    private final String USERNAME;
    private Scanner serverInput;
    private Scanner userInput;
    private PrintWriter userOutput;
    private Thread thread;

    Client(final String HOST, final int PORT, final String USERNAME) throws IOException {
        this.USERNAME = USERNAME;
        try {
            // create ClientSocket
            this.socket = new Socket(HOST, PORT);
            this.serverInput = new Scanner(this.socket.getInputStream());
            readerThread();
            writeMessages();
        } catch (IOException e) {
            this.socket.close();
            this.serverInput.close();
        }
    }

    private void readerThread() {
        this.thread = new Thread(() -> {
            // read incoming data from server
            String message;
            while (!this.socket.isClosed()) {
                message = this.serverInput.nextLine();
                if (!message.startsWith(this.USERNAME)) {
                    System.out.println(message);
                }
            }
        });
        this.thread.start();
    }

    private void writeMessages() throws IOException {
        // read data from keyboard
        this.userInput = new Scanner(System.in);
        try {
            this.userOutput = new PrintWriter(this.socket.getOutputStream(), true);
            // repeat while input != exit
            String message;
            while (!"quit".equalsIgnoreCase(message = this.userInput.nextLine())) {
                // send to server
                this.userOutput.println(this.USERNAME + ": " + message);
            }
            this.userOutput.println(this.USERNAME + " is now offline");
            this.socket.close();
            this.thread.interrupt();
            this.userInput.close();
            this.userOutput.close();
        } catch (IOException e) {
            this.socket.close();
            this.thread.interrupt();
            this.userInput.close();
            this.userOutput.close();
        }
    }
}