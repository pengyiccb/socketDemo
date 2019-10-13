import java.io.IOException;
import java.net.*;

public class UDPSearcher {

    public static void main(String[] args) throws IOException {

        DatagramSocket search = new DatagramSocket();

        //构建一份发送数据
        String reponse = "HelloWorld!";
        DatagramPacket packet = new DatagramPacket(reponse.getBytes(),reponse.length());
        packet.setAddress(InetAddress.getLocalHost());
        packet.setPort(20000);

        search.send(packet);
        final byte[] buf = new byte[512];
        DatagramPacket revieve = new DatagramPacket(buf,buf.length);

        search.receive(revieve);

        String ip = revieve.getAddress().getHostAddress();
        int port = revieve.getPort();
        int dataLen = revieve.getLength();
        String data = new String(revieve.getData(),0,dataLen);
        System.out.println("Provider ip is "+ip+"\tport is "+port+"\tlength is "+ dataLen);

        System.out.println("Searcg is fished .");
        search.close();
        //打印收到的数据
    }
}
