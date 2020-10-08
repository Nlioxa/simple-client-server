package com.edu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    private List<Student> studentList = new ArrayList();

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void accept() {
        try {
            this.socket = serverSocket.accept();

            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());

            var query = " ";
            while (!query.isEmpty()) {
                query = this.inputStream.readUTF();
                var query_result = "";

                try {
                    var command = CommandFactory.command(query);
                    query_result = command.apply(studentList);
                } catch (InvalidParameterException e) {
                    query_result = e.getMessage();
                }

                this.outputStream.writeUTF(query_result);
                this.outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        this.inputStream.close();
        this.socket.close();
        this.serverSocket.close();
    }
}
