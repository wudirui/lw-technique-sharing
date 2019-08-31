package com.zr.hadoop;



import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.zr.hadoop.HdfsConfig.PATH;

/**
 * @Author super rui
 * @Date 2019/08/31
 */
public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) {
        readFile();
    }

    /**
     * 从hadoop的url读取数据
     */
    private static void readFile() {
        InputStream in = null;
        try {
            in = new URL(PATH).openStream();
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(in);
        }
    }

}
