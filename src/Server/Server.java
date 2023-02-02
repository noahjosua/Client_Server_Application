package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Noah-Josua Hartmann
 * @created 08/04/2022 - 11:42
 * @project Client_Server_Application
 */

public class Server {
    private final int MAX_CONNECTIONS;
    private BlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private List<PrintWriter> connections = new ArrayList<>();
    private ServerSocket server;

    Server(final int PORT, final int MAX_CONNECTIONS) throws IOException {
        this.MAX_CONNECTIONS = MAX_CONNECTIONS;
        try {
            this.server = new ServerSocket(PORT, MAX_CONNECTIONS);
        } catch (IOException e) {
            this.server.close();
        }
        start();
    }

    private void start() throws IOException {
        new WriterThread(this.messages, this.connections).start();
        System.out.println("Listening on Port 4001 \n" + "max connections: " + this.MAX_CONNECTIONS);

        int connected = 0;
        do {
            System.out.println("Waiting for Clients...");
            try {
                Socket socket = this.server.accept();
                connected++;
                ConnectionThread connectionThread = new ConnectionThread(socket, this.server, this.messages, this.connections);
                connectionThread.start();
            } catch (IOException e) {
                this.server.close();
            }
        } while (connected < this.MAX_CONNECTIONS);
    }
}
