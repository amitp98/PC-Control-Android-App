package com.example.amit.remotedesktop;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public abstract class Connect extends AsyncTask<Void, Void, Socket> {
    private static Socket clientSocket;
    String ip, port;
    Context context;
    Connect(String ip, String port, Context context) {
        this.ip = ip;
        this.port = port;
        this.context = context;
    }


    @Override
    protected Socket doInBackground(Void... voids) {
        try {
            clientSocket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(ip, Integer.parseInt(port));
            clientSocket.connect(socketAddress, 3000);
            MainActivity.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            MainActivity.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return clientSocket;
    }
    protected void onPostExecute(Socket clientSocket) {
        receiveData(clientSocket);
    }


    public abstract void receiveData(Object result);

}