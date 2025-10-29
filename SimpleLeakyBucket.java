import java.util.Scanner;
public class SimpleLeakyBucket {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("enter no. of packets:");
        int n=sc.nextInt();
        System.out.print("enter bucket capacity:");
        int capacity=sc.nextInt();
        System.out.print("enter output rate");
        int rate=sc.nextInt();
        int packets[] = new int[n];
        System.out.println("enter the packets:");
        for(int i=0;i<n;i++)
        {
            packets[i]=sc.nextInt();
        }
        int stored=0;
        System.out.println("\n clock\tpacket\taccept\t\tsent\tremaining");
        for(int i=0;i<n;i++)
        {
            int acceptDis;
            int sent;
            int packet=packets[i];
            if(packet>capacity)
            {
                sent=0;
                stored=0;
            }
            else
            {
                
                if(stored+packet<=capacity){
                    stored+=packet;
                }
                else
                {
                    acceptDis=packet-capacity;
                    sent=acceptDis-rate;
                    stored=0;
                    System.out.println((i+1)+"\t"+packet+"\t"+acceptDis+"\t\t"+sent+"\t"+stored);
                    continue;
                }
                if(stored>=rate)
                {
                    sent=rate;
                }
                else
                {
                    sent=stored;
                }
                stored-=sent;
            }
            acceptDis=packet;
            System.out.println((i+1)+"\t"+packet+"\t"+acceptDis+"\t\t"+sent+"\t"+stored);
        }
        sc.close();
    }
}