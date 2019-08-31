package com.zr.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import javax.security.auth.login.AppConfigurationEntry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import static com.zr.hadoop.HdfsConfig.PATH;

/**
 * @Author super rui
 * @Date 2019/08/31
 */
public class FileSyetemCat {
    public static void main(String[] args) throws IOException {
        fileRead();
    }
    private static void fileRead() throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(PATH), configuration);
        InputStream in = null;
        try {
            in = fs.open(new Path(PATH));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }


    }
}
