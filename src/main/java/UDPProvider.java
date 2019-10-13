import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPProvider {

    public static void main(String[] args) throws IOException {
        //指定一个端口用于接收数据
        System.out.println("Provider started .");
        DatagramSocket provider = new DatagramSocket(20000);
         final byte [] buf = new byte[512];
         //构建接收实体
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        //构建接受到的信息和发送方的信息
        provider.receive(packet);

        String ip = packet.getAddress().getHostAddress();
        int port = packet.getPort();
        int dataLen = packet.getLength();

        String recieve = new String(packet.getData(),0,dataLen);

        System.out.println("Provider ip is "+ip+"\tport is "+port+"\tlength is "+ dataLen);

        String reponseData = "Provider reponse data is";
        DatagramPacket reponse = new DatagramPacket(reponseData.getBytes(),reponseData.length(),packet.getAddress(),packet.getPort());

        provider.send(reponse);

        System.out.println("Provider send success");

        provider.close();
    }
}
