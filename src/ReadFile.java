import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by User on 11/8/2016.
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        try(RandomAccessFile inFile = new RandomAccessFile("D:/text.txt", "r");
            RandomAccessFile outFile = new RandomAccessFile("D:/out.txt", "rw");

            FileChannel inChannel = inFile.getChannel();
            FileChannel outChannel = outFile.getChannel()) {

            ByteBuffer inBuffer = ByteBuffer.allocate(128);
            ByteBuffer outBuffer = ByteBuffer.allocate(128);

            while (inChannel.read(inBuffer) > 0) {
                inBuffer.flip();
                outBuffer.flip();
                for (int i = 0; i < inBuffer.limit(); i++) {
                    System.out.print((char) inBuffer.get());
                    outChannel.write(outBuffer);
                }
                inBuffer.clear();
                outBuffer.clear();
            }
        }
    }
}
