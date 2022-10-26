package net.charliemeyer.heos.client;

import lombok.SneakyThrows;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.OutputStream;

public class HeosClient {

    private final TelnetClient telnetClient;

    @SneakyThrows
    public HeosClient(String host) {
        this.telnetClient = new TelnetClient();
        this.telnetClient.addOptionHandler(new EchoOptionHandler(false, false, false, true));
        this.telnetClient.connect(host, 1255);
    }

    public boolean reboot() {
        return sendCommand("heos://system/reboot");
    }

    @SneakyThrows
    private boolean sendCommand(String command) {
        OutputStream out = telnetClient.getOutputStream();
        out.write(command.getBytes());
        Thread.sleep(100);
        out.write("\r\n".getBytes());
        Thread.sleep(100);
        out.flush();
        return true;
    }
}
