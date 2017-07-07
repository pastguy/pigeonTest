package ymm.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Client {
	public static void main(String[] args) {

        if(args.length != 3){
            System.out.println("Usage :  java -jar ClientTcpSend.jar ipaddress port  filename");
            return;
        }

        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(args [0], Integer.parseInt(args[1])));

            final Path filePath = Paths.get(args[2]);

			FileChannel fileChannel = FileChannel.open(filePath, EnumSet.of(StandardOpenOption.READ));

            long start = System.currentTimeMillis();

            //用来发送文件大小
            ByteBuffer buf = ByteBuffer.allocate(8);
            buf.asLongBuffer().put(fileChannel.size());
            socketChannel.write(buf);

            //发送文件内容
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            fileChannel.close();
            socketChannel.close();

            long end = System.currentTimeMillis();

            System.out.println("Total use " + (end - start) + "ms");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
