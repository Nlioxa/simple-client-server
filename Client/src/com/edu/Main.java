package com.edu;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            var socket = new Socket("localhost", 6666);
            var dataInputStream = new DataInputStream(
                    socket.getInputStream());
            var dataOutputStream = new DataOutputStream(
                    socket.getOutputStream());
            var bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in));

            var client_command = " ";
            while(!client_command.isEmpty()) {
                client_command = bufferedReader.readLine();

                dataOutputStream.writeUTF(client_command);
                dataOutputStream.flush();

                var server_answer = dataInputStream.readUTF();
                System.out.println("server: " + server_answer);
            }

            dataOutputStream.flush();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
