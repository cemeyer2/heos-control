package net.charliemeyer.heos;

import lombok.extern.slf4j.Slf4j;
import net.charliemeyer.heos.client.HeosClient;

@Slf4j
public class Rebooter {
    public static void main(String[] args) {
        System.setProperty("sun.net.spi.nameservice.nameservers", "192.168.1.1");
        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
        for (String host : args) {
            log.info("Rebooting {}", host);
            new HeosClient(host).reboot();
        }
    }
}