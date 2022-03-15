package com.company.json;


import java.io.IOException;

public class Main {


    public static void main(String[] args) {


        String READER_IP_ADDRESS = "192.168.153.43";
        ReaderClient readerClient = new ReaderClient();
        try
        {
            readerClient.connectToReader(READER_IP_ADDRESS);
            Thread.sleep(500);
            readerClient.sendToReader("Version()\n\r");
            Thread.sleep(500);
            readerClient.closeConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
