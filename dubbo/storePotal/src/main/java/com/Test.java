package com;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Test {

    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String hostAddress = inetAddress.getHostAddress();
        System.out.println(hostAddress);
    }




    /**
     * 获取本机的IPV4地址
     */
    public static String getIpv4() throws Exception{
        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
        if (null != enumeration) {
            while (enumeration.hasMoreElements()) {
                NetworkInterface fInterface = enumeration.nextElement();
                if (!fInterface.isVirtual() && !fInterface.isLoopback() && fInterface.isUp()) {
                    Enumeration<InetAddress> adds = fInterface.getInetAddresses();
                    while (adds.hasMoreElements()) {
                        InetAddress address = adds.nextElement();
                        byte[] bs = address.getAddress();
                        if (bs.length == 4) {
                            System.out.println(address.getHostAddress());
                            return address.getHostAddress();
                        }

                    }
                }

            }
        }

        return null;
    }
}
