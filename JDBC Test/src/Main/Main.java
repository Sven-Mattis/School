package Main;

import DB.GetUser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static int PORT;

    private static final String SQL_INSTALL_DIR = "C:/xampp/mysql";
    private static String commandStart = SQL_INSTALL_DIR + "/bin/mysqld -P";

    private static int getOpenPort() {
        AtomicInteger openPort = new AtomicInteger(0);
        int i = 3206;

        while(openPort.get() == 0) {
            int finalI = i;
            new Thread(() -> {
                try {
                    ServerSocket s = new ServerSocket(finalI);
                    s.close();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    openPort.set(finalI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
        }

        return openPort.get();
    }

    private static Process mysqlProc;

    public static void main(String[] args) throws SQLException, SocketException {

        Main.PORT = getOpenPort();

        start();

        System.out.println(new GetUser("Test", "Test").exists());
        System.out.println(new GetUser("Test", "Test").getGroup());

        kill();
    }

    private static void start() {
        try {
            mysqlProc = Runtime.getRuntime().exec(commandStart+Main.PORT);
            Thread.sleep(100);
            System.out.println("MySQL server started successfully!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(0x999);
        }
    }

    private static void kill() {
        mysqlProc.children().forEach(e -> e.destroy());
        mysqlProc.destroy();
    }
}
