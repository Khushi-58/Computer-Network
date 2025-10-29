import java.util.*;
public class Rsa {
    public static void main(String[] args){
        String msg;
        int pt[]=new int[50];
        int ct[]=new int[50];
        int z,n,d,e,p,q,mlen;
        Scanner in=new Scanner(System.in);
        do{
            System.out.println("enter the prime numbers for p & q:");
            p=in.nextInt();
            q=in.nextInt();
        }
        while(prime(p)==0||prime(q)==0);
        n=p*q;
        z=(p-1)*(q-1);
        System.out.println("n="+n+"z="+z);
        for(e=2;e<2;e++){
            if(gcd(e,z)==1)
                break;
        }
        System.out.println("e="+e+"n="+n);
        for(d=2;d<z;d++){
            if((e*d)%z==1)
                break;
        }
        System.out.println("d="+d+"n="+n);
        in.nextLine();
        System.out.println("enter msg for encryption");
        msg=in.nextLine();
        mlen=msg.length();
        for(int i=0;i<mlen;i++)
            pt[i]=msg.charAt(i);
        System.out.println("ASCII value of p array is");
        for(int i=0;i<mlen;i++)
            System.out.println(pt[i]);
        System.out.println("encryption:lipher text obtained:");
        for(int i=0;i<mlen;i++)
        ct[i]=mult(pt[i],e,n);
        for(int i=0;i<mlen;i++)
                System.out.println(ct[i]+"\t");
        System.out.println("decryption plain text obtained:");
        for(int i=0;i<mlen;i++)
            pt[i]=mult(ct[i],d,n);
        for(int i=0;i<mlen;i++)
        
        {
            
            System.out.println(pt[i]+":"+(char)pt[i]);
            }
    }
    public static int mult(int base,int exp,int n)
    {
        int res=1,j;
        for(j=1;j<=exp;j++)
            res=((res*base)%n);
        return res;
    }
    private static int prime(int num)
    {
        if(num<=1){
            return 0;
        }
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0){
                return 0;
            }
        }
        return 1;
    }
    public static int gcd(int a,int b){
        while(b!=0)
        {
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
        }
    }
    