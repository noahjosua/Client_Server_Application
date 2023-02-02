package Server;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author Noah-Josua Hartmann
 * @created 08/04/2022 - 11:42
 * @project Client_Server_Application
 */

public class WriterThread extends Thread {
    private boolean isRunning;
    private BlockingQueue<String> messages;
    private List<PrintWriter> connections;

    public WriterThread(BlockingQueue<String> messages, List<PrintWriter> connections) {
        this.isRunning = true;
        this.messages = messages;
        this.connections = connections;
    }

    @Override
    public void run() {
        // taking messages from Queue as long as > 0
        while (this.isRunning) {
            try {
                String message = this.messages.take();
                for (PrintWriter output : this.connections) {
                    output.println(message);
                }
            } catch (InterruptedException e) {
                this.isRunning = false;
                this.interrupt();
            }
        }
    }
}