package com.agmtopy.source.networkinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NetworkInterfaceTest {

    public static void main(String[] args) {
        //获取网关地址
        getGateway();
        //遍历ip地址进行ping

        //排除虚拟网卡设备

    }

    /*
     * 通过traceroute的第一跳获得网关
     * 缺点：没有连接网络时获取不到/只能在windows环境下使用
     */
    public static String getGateway() {
        String os = System.getProperty("os.name");

        //通过tracert获取网关地址
        if (os != null && os.startsWith("Windows")) {
            try {
                String command = "tracert -d 8.8.8.8";
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                String line;

                String[] tmp = null;
                while ((line = br.readLine()) != null) {
                    if (line.trim().startsWith("1")) {
                        tmp = line.trim().split("\\s+");
                        if (tmp.length > 0 && tmp[0].equals("1")) {
                            System.out.println("网关：" + tmp[tmp.length - 1]);
                            return tmp[tmp.length - 1];
                        }
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
