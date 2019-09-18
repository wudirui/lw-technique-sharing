package com.zr.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

/**
 * @ClassName FileCopyWithProgress
 * @Description TODO
 * @Author ruizhang
 * @Date 2019/9/17 11:31
 */
public class FileCopyWithProgress {
    public static void main(String[] args) throws IOException {
        String localSrc = args[0];
        String dst = args[1];
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), configuration);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            @Override
            public void progress() {
                System.out.println(".");
                System.out.println("----------");
            }
        });

        org.apache.hadoop.io.IOUtils.copyBytes(in, out, 4409, true);


    }
}


