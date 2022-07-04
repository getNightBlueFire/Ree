import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;

public class FileExample {
    public static void main(String[] args) {
        Supplier<User> supplier = User::new;
        User user = supplier.get();

        Supplier<Thread> threadSupplier = () -> {
            Thread thread = new Thread(user);
            thread.setName("user_nickname"+ new Random().nextInt(100));
            return thread;
        };
        Thread user1 = threadSupplier.get();
        Thread user2 = threadSupplier.get();
        Thread user3 = threadSupplier.get();
        user1.start();
        user2.start();
        user3.start();

    }


    public static String read() {
        Path path = Paths.get("test.txt");
        String string;
        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line = reader.readLine();
            System.out.println(line + "   " +  Thread.currentThread().getName() + " read this file");
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

class User implements Runnable{

    private int value = 0;

    @Override
    public void run() {
        doSome();
        doSome();
    }

    private void doSome() {
        String read = FileExample.read();
        int i = Integer.parseInt(read);
        String line = i + 10 + "";
        FileExample.write(line);
        this.value = i;
        System.out.println(Thread.currentThread().getName() + "change value from "+ this.value + " to " + (i+10));

    }


}
