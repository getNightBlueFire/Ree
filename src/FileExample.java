import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;

public class FileExample {
    public static void main(String[] args) {
        Supplier<Add> supplier = Add::new;
        Add add = supplier.get();

        Supplier<Thread> threadSupplier = () -> {
            Thread thread = new Thread(add);
            thread.setName("test"+ new Random().nextInt(100));
            return thread;
        };
        Thread thread1 = threadSupplier.get();
        Thread thread2 = threadSupplier.get();
        Thread thread3 = threadSupplier.get();
        thread1.start();
        thread2.start();
        thread3.start();

    }


    public static String read() {
        Path path = Paths.get("test.txt");
        String string;
        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line = reader.readLine();
            System.out.println(line + "   " +  Thread.currentThread().getName() + "read this file");
            string = line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string;
    }

    public static void write(String a) {
        Path path = Paths.get("test.txt");
        try(BufferedWriter reader = Files.newBufferedWriter(path)) {
            reader.write(a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Add implements Runnable{

    @Override
    public void run() {
        doSome();
    }

    private void doSome() {
        String read = FileExample.read();
        int i = Integer.parseInt(read);
        String line = i + 10 + "";
        FileExample.write(line);
    }


}
