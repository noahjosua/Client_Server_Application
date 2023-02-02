package Server;

import java.io.IOException;

/**
 * @author Noah-Josua Hartmann
 * @created 08/04/2022 - 11:42
 * @project Client_Server_Application
 */

public class ServerMain {
    public static void main(String[] args) throws IOException {
        new Server(4001, 3);
    }
}
