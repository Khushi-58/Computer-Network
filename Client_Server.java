package client_server;
import java.net.*;
import java.io.*;
import java.util.*;
public class Client_Server {
    public static void main(String[] args)throws Exception {
        Scanner in=new Scanner(System.in);
        Socket clientSocket=new Socket("127.0.0.1",4000);
        System.out.println("*********CLIENT SERVER*******");
        System.out.println("enter the file name to transfer");
        String fname=in.nextLine();
        OutputStream ostream=clientSocket.getOutputStream();
        PrintWriter pwrite=new PrintWriter(ostream,true);
        pwrite.println(fname);
        InputStream istream=clientSocket.getInputStream();
        BufferedReader SocketRead=new BufferedReader(new InputStreamReader(istream));
        System.out.println("contents of the file"+fname+" are");
        String str;
        while((str=SocketRead.readLine())!=null)
        {
            System.out.println(str);
        }
        pwrite.close();
        SocketRead.close();
        
    }
    
}
