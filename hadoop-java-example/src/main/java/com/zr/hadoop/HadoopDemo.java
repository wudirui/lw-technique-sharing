package com.zr.hadoop;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @Author super rui
 * @Date 2019/8/19 22:21
 */
public class HadoopDemo {

    //hadoop文件系统的端口为9000
    private static final String PATH = "hdfs://172.22.5.7:9000/user/admin/output/part-r-00000";

    public static void main(String[] args) {
        InputStream in = null;
        try {
            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
            in = new URL(PATH).openStream();

            //读取文件
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            org.apache.hadoop.io.IOUtils.closeStream(in);
        }
    }
}


