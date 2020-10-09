package echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConstData {
    ServerSocket myServer = null;
    Socket socketToClient = null;
    static final public int PORT = 3000;
    String messageClient;
    String messageServer;
    Scanner in = null;
    PrintWriter out = null;
    Scanner write = null;

    void startServer() {
        try {
            myServer = new ServerSocket( PORT );
        } catch (IOException e) {
            System.out.println( "Порт " + PORT + " занят" );
            System.exit( 1 );
        }
        System.out.print( "Server started\n" );
    }

    void clientCatch() throws IOException {
        try {
            socketToClient = myServer.accept();
            System.out.println( "Клиент подключен" );
        } catch (IOException e) {
            System.out.println( "Клиент не подключен" );
            System.exit( 1 );
        }

        in = new Scanner( socketToClient.getInputStream() );
        out = new PrintWriter( socketToClient.getOutputStream(), true );

        System.out.println( " Expectation " );

    }

    void messageSend(String message) throws IOException {
        out.println( "Сервер - " + message );
        System.out.println( message );
    }

    void close() throws IOException {
        out.close();
        in.close();
        socketToClient.close();
        myServer.close();
    }

    void write() throws IOException {
        while (true) {
            String writeT = write.nextLine();
            if (writeT.equalsIgnoreCase( "close" ) ||
                    writeT.equalsIgnoreCase( "exit" )) {
                System.out.println( "Break Client" );
                close();
            }
            messageSend( writeT );
        }
    }

    void messageRead() throws IOException {
        while (true) {

            if ((messageServer = in.nextLine()) != null) {
                System.out.println( messageServer );
            }
        }
    }

    void messageSend() throws IOException {
        while (true) {

            if ((messageClient = write.nextLine()) != null) {
                out.println( messageClient );
                if (messageClient.equalsIgnoreCase( "close" ) ||
                        messageClient.equalsIgnoreCase( "exit" )) {
                    System.out.println( "Break chat" );
                    close();
                    break;
                }
            }
        }
    }

    public void ClientCons() throws IOException {
        socketToClient = new Socket( "localhost", PORT );
        in = new Scanner( socketToClient.getInputStream() );
        out = new PrintWriter( socketToClient.getOutputStream(), true );
        write = new Scanner( System.in );
    }

}
