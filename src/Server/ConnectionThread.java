package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * @author Noah-Josua Hartmann
 * @created 08/04/2022 - 11:42
 * @project Client_Server_Application
 */

public class ConnectionThread extends Thread {
    private boolean isRunning;
    private ServerSocket server;
    private BlockingQueue<String> messages;
    private List<PrintWriter> connections;
    private PrintWriter serverOutput;
    private Scanner serverInput;
    private Socket socket;

    public ConnectionThread(Socket socket, ServerSocket server, BlockingQueue<String> messages, List<PrintWriter> connections) {
        this.isRunning = true;
        this.socket = socket;
        this.server = server;
        this.messages = messages;
        this.connections = connections;
    }

    @Override
    public void run() {
        try {
            // send data to clients
            this.serverOutput = new PrintWriter(this.socket.getOutputStream(), true);

            // confirmation
            if (this.socket.isConnected()) {
                this.serverOutput.println("Successfully connected!");
                System.out.println("""
                        The following message was send to the new client: Successfully connected!
                        Connection established!
                        Waiting for Clients...""");
            }

            // add new connected client to the list
            this.connections.add(this.serverOutput);

            // read data coming from clients
            this.serverInput = new Scanner(this.socket.getInputStream());

            // continuously waiting for incoming messages
            String message;
            while (this.isRunning) {
                try {
                    while ((message = this.serverInput.nextLine()) != null) {
                        // add to Queue(source for WriterThread)
                        this.messages.put(message);
                    }
                } catch (NoSuchElementException | InterruptedException e) {
                    this.isRunning = false;
                    this.interrupt();
                    this.serverInput.close();
                    this.serverOutput.close();
                }
            }
        } catch (IOException e) {
            this.isRunning = false;
            this.interrupt();
        }
    }
}