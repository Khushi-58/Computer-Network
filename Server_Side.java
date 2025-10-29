package server_side;
import java.net.*;
import java.io.*;
public class Server_Side {
    public static void main(String[] args)throws Exception{
        ServerSocket servSocket=new ServerSocket(4000);
        System.out.println("********SERVER SIDE*********");
        System.out.println("server ready for connection");
        Socket connSock=servSocket.accept();
        System.out.println("connection is successful and ready for file transfer");
        InputStream istream=connSock.getInputStream();
        BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream));
        String fname=fileRead.readLine();
        File fileName=new File(fname);
        OutputStream ostream=connSock.getOutputStream();
        PrintWriter pwrite=new PrintWriter(ostream,true);
        if(fileName.exists())
        {
            BufferedReader ContentRead=new BufferedReader(new FileReader(fname));
            System.out.println("writing file contents to the socket");
            String str;
            while((str=ContentRead.readLine())!=null){
                pwrite.println(str);
            }
            ContentRead.close();
                
            }
        else
        {
            System.out.println("requested file does not exist");
            String msg="Requested file does not exist at the server side";
            pwrite.println(msg);
        }
        connSock.close();
        servSocket.close();
        fileRead.close();
        pwrite.close();
        
       
    }
    
}
