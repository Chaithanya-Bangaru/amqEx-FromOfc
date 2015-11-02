import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Java program to find IP address and hostname of a machine. InetAddress of
 * java.net package is used to get IP address and hostname in Java applications.
 *
 * @author WINDOWS 8
 *
 */
public class IPAddress {

    private static String getIPFromHost(String hostName) throws UnknownHostException {
        //System.out.println("hostName:"+hostName);
        //logger.info("hostName:"+hostName);
        String ip = null;
        if(hostName != null){
            ip = Inet6Address.getByName(hostName).getHostAddress();
        }
        return ip;
    }
    public static void main(String args[]) throws UnknownHostException {
        //System.out.println("hostName:"+getIPFromHost("localhost"));
        System.out.println("\nIP for PKMA-DS001D630:"+getIPFromHost("PKMA-DS001D630"));
        System.out.println("\nIP for AMAGDEV.VBR.TLA.UPRR.COM:"+getIPFromHost("AMAGDEV.VBR.TLA.UPRR.COM"));
        System.out.println("\nIP for AMAGPRD.VBR.TLA.UPRR.COM:"+getIPFromHost("AMAGPRD.VBR.TLA.UPRR.COM"));
        //AMAGPRD.VBR.TLA.UPRR.COM
        //AMAGPRD.VBR.TLA.UPRR.COM

        // First get InetAddress for the machine, here localhost
        /*InetAddress myIP = null;
        try {
            myIP = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // getHostAddress() returns IP address of a machine
        String IPAddress = myIP.getHostAddress();

        // getHostname returns DNS hostname of a machine
        String hostname = myIP.getHostName();

        System.out.printf("IP address of Localhost is %s %n", IPAddress);
        System.out.printf("Host name of your machine is %s %n", hostname);*/

    }

}


//Read more: http://java67.blogspot.com/2014/02/how-to-find-ip-address-of-localhost-Java-example.html#ixzz3ptYR2mlX
