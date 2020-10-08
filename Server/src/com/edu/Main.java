package com.edu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try
        {
            var server = new Server(6666);
            server.accept();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
