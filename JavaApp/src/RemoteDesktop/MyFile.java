/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteDesktop;


import static RemoteDesktop.Server.clientSocket;
import static RemoteDesktop.Server.objectOutputStream;
import static RemoteDesktop.Server.os;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author amit
 */
public class MyFile {
    public static ObjectOutputStream out = null;

    public String getHomeDirectoryPath() {
        String path = System.getProperty("user.home");
        return path;
    }
    
    public void receiveFile(final String fileName, long fileSize, 
        ObjectInputStream objectInputStream) {
        FileOutputStream fos = null;
        String path = getHomeDirectoryPath();
        path = path + "/AMIT/Android/" + fileName;
        File file = new File(path);
        File dirs = new File(file.getParent());
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        try {
            fos = new FileOutputStream(file);
            byte buffer[] = new byte[4096];
            int read = 0;
            long totalRead = 0;
            int remaining = (int) fileSize;
            while ((read = objectInputStream.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                RemoteDesktop.info.setText("Receive Progress: " + 
                        ((totalRead * 100) / fileSize) + "%");
                fos.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void sendFile(final String path, final String name, int f) {
        
        out = Server.objectOutputStream;
        new Thread() {
            @Override
            public void run() {
                FileInputStream fis = null;
               

                try {
                    File file = new File(path);
                    long fileSize = file.length();
                    out.writeObject(name);
                    out.flush();
                    
                    out.writeObject(fileSize);
                    out.flush();
                    System.out.println(path + " " + fileSize);
                    fis = new FileInputStream(file);
                    byte[] buffer = new byte[4096];
                    int read = 0;
                    long totalRead = 0;
                    int remaining = (int) fileSize;
                    while ((read = fis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                        totalRead += read;
                        remaining -= read;
                        System.out.println("Transfer Progress: " + ((totalRead * 100) / fileSize));
                        out.write(buffer, 0, read);
                        out.flush();
                    }
                    out.flush();
                    
                    if(f == 1){
                        if(file.delete()) 
                        { 
                            System.out.println("File deleted successfully"); 
                        } 
                        else
                        { 
                            System.out.println("Failed to delete the file"); 
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
     
}
