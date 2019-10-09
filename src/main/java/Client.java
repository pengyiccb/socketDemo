import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.setSoTimeout(3000);

        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000),3000);

        System.out.println("已发起服务器链接,并进入后续流程");
        System.out.println("客户端信息:"+socket.getLocalAddress()+"P:"+socket.getLocalPort());
        System.out.println("服务器信息:"+socket.getInetAddress()+"P:"+socket.getPort());

        try{
            todo(socket);
        }catch(Exception e){
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(Socket socket) throws Exception{
        boolean flag = true;


            InputStream in = System.in;
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        do {
            String str = input.readLine();
            printStream.println(str);
            String echo = bufferedReader.readLine();

            if ("bye".equalsIgnoreCase(echo)){
                flag=false;
            }else{
                System.out.println(echo);
            }


        }while(flag);

        printStream.close();
        bufferedReader.close();

    }
}
