import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(2000);

        System.out.println("服务器准备就绪~");
        System.out.println("服务器信息:"+server.getInetAddress()+"P:"+server.getLocalPort());

        //等待客户端连接
        for (;;){
        Socket client = server.accept();
        ClientHandler clientHandler = new ClientHandler(client);
        clientHandler.start();
        }
    }

    private static class ClientHandler extends Thread{
        private Socket socket;

        private boolean flag = true;

        ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("新客户端连接了:"+socket.getInetAddress()+"P:"+socket.getPort());

            try {
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    String str = bufferedReader.readLine();
                    System.out.println("客户端返回的数据:"+str);
                    if ("bye".equalsIgnoreCase(str)){
                        flag=false;
                        printStream.println("bye");
                    }else{
                        System.out.println(str);
                        printStream.println("回送:"+str.length());
                    }

                }while (flag);

                printStream.close();
                bufferedReader.close();
            }catch (Exception e){
                System.out.println("异常终止");
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已退出:"+socket.getInetAddress()+"P:"+socket.getPort());
        }
    }
}
