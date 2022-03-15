package com.company.json;


import javax.swing.event.EventListenerList;
import java.io.*;
import java.net.Socket;
import java.util.EventListener;

public class ReaderClient implements EventListener
{
    private final int READER_PORT = 8023;
    private Socket clientSocket;
    private BufferedReader input;
    private BufferedWriter output;

    private EventListenerList eventListenerList;

    private boolean runThread;

    public ReaderClient()
    {

    }

    public void connectToReader(String ipAddress) throws IOException {
        System.out.println("Attempting to connect to reader.");
        // Client socket created here
        clientSocket = new Socket(ipAddress, READER_PORT);
        // Get the input stream object from the socket.
        input = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        // Get the output stream object from the socket.
        output = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream()));
        System.out.println("Connected to the reader" +
                " at IP address: " + ipAddress);
        handleConnection();
    }

    private void handleConnection() throws IOException {
        if (runThread)
            return;

        runThread = true;
        new Thread(() -> {
            while (runThread)
            {
                try
                {
                    if (input.ready())
                    {
                        System.out.println("Received: " +
                                input.readLine());


                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void sendToReader(String command) throws IOException {
        if (output != null && clientSocket.isConnected())
        {
            output.write(command);
            output.flush();
        }
    }

    public void closeConnection() throws IOException, InterruptedException
    {
        System.out.println("Closing connections to the reader.");
        runThread = false;
    }
}