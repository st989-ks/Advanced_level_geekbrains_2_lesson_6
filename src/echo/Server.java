package echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static echo.ConstData.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ConstData serv = new ConstData();

        serv.startServer();// готово на своем месте
        serv.clientCatch();
        serv.write();


    }
}
