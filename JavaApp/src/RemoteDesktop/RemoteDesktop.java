package RemoteDesktop;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author amit
 */
public class RemoteDesktop{
    JFrame window;
    JTextField ip, port;
    JLabel ip1, port1;
    static JLabel conn;
    static JTextArea info;
    static JButton reset;
    static JButton file;
    JFileChooser fileChooser;
       

    InetAddress localhost = null;
    int portNum;

    RemoteDesktop(){
        
        fileChooser = new JFileChooser("/home/amit/Desktop");
        fileChooser.setMultiSelectionEnabled(true);
                
        window= new JFrame("Remote Desktop");        
        reset = new JButton();
        file = new JButton();
        ip = new JTextField();
        port = new JTextField();        
        ip1 = new JLabel();
        port1 = new JLabel();
        conn = new JLabel();
        info = new JTextArea();

        reset.setText("Reset");    
        file.setText("Send");    
        file.setVisible(false);

        ip.setEditable(false);
        port.setEditable(false);
        info.setEditable(false);

        reset.setBounds(250, 130, 80, 30);
        file.setBounds(250, 170, 80, 25);
        ip.setBounds(125, 60, 110, 30);
        port.setBounds(125, 100, 110, 30);
        ip1.setBounds(30, 60, 85, 20);
        port1.setBounds(30, 100, 100, 20);
        conn.setBounds(30, 135, 300, 20);
        info.setBounds(30, 200, 300, 100);

        ip1.setText("IP Address:");
        port1.setText("Port Number:");
        conn.setText("Connection: not connected");
        info.setText(" Press Connect to connect to Android ...");
        info.setWrapStyleWord(true);
        
        portNum = getFreePort();
        port.setText("" + portNum);
        
        reset.setEnabled(false);
        window.add(ip);
        window.add(port);
        window.add(ip1);
        window.add(port1);
        window.add(ip);
        window.add(conn);
        window.add(info);
        window.add(reset);
        window.add(file);

       // reset.setVisible(false);
        window.setLayout(null);
        window.setResizable(false);
        window.setSize(350, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        if(getIp() == 1){
            ip.setText((localhost.getHostAddress()).trim());
        }
        else
            info.setText(" Connect your PC to Android phone \n via hotspot and \n Press Connect to connect to Android ...");
        
        if(getIp() == 1){
            ip.setText((localhost.getHostAddress()).trim());
            info.setText(" Waiting for connection ...");
            new Server(localhost, portNum);
        }
        else
            info.setText(" Connect your PC to Android phone \n via hotspot and \n Press Connect to connect to Android ...");

       reset.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                Server.connectionClosed();
                Android.closeConnectionToAndroid();
                if(getIp() == 1){
                    ip.setText((localhost.getHostAddress()).trim());
                    info.setText(" Waiting for connection ...");
                    new Server(localhost, portNum);
                }
                else
                    info.setText(" Connect your PC to Android phone \n via hotspot and \n Press Connect to connect to Android ...");
            
            }
        });
       file.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int result = fileChooser.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File[] files = fileChooser.getSelectedFiles();
                    if(files.length == 1)
                        new MyFile().sendFile(files[0].getAbsolutePath(), files[0].getName(), 0);
                    else
                        zip(files);
                }
            }
        });
    }
    
   /* void startS(){
        new Server(this.localhost, this.portNum);

    }*/
    int getIp(){
        try{
            this.localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex){
            return 0;
        }
        //System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
        return 1;
    }
    
    private boolean isPortAvailable(int port){
        boolean portAvailable = true;
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
        } catch(Exception e){
            portAvailable = false;
        } finally{
            if (serverSocket != null){
                try{
                    serverSocket.close();
                } catch(Exception e){
                    e.printStackTrace();
                };
            }
        }
        return portAvailable;
    }
    
    public int getFreePort(){
        int port = 3000;
        while (true){
            if (isPortAvailable(port) == true){
                break;
            } else{
                port++;
            }
        }
        return port;
    }
    
    public void zip(File files[]) {
      byte[] buffer = new byte[1024];
      try {
        String path = System.getProperty("user.home");
        path = path + "/AMIT/Android/";
          FileOutputStream fos = new FileOutputStream(path + "android.zip");
          
          ZipOutputStream zos = new ZipOutputStream(fos);
          // Create a zip entry inside the zip file with name temp.txt
          File selectedFile;
          
          for(int i = 0; i < files.length; i++){
                selectedFile = files[i];
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                System.out.println("Selected file: " + selectedFile.getName());

                ZipEntry ze1 = new ZipEntry(selectedFile.getName());
                zos.putNextEntry(ze1);
                FileInputStream in1 = new FileInputStream(selectedFile.getAbsolutePath());
                int len;
                while ((len = in1.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in1.close();

          }
                    
          
          zos.closeEntry();
          // remember to close it
          zos.close();
          new MyFile().sendFile(path+"android.zip", "android.zip", 1);
          System.out.println("Done!!!");
       } catch (IOException ex) {
               ex.printStackTrace();
       }
   }
   
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new RemoteDesktop();
            }
        });
    }
}
