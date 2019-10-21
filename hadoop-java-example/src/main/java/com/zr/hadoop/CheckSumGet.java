package com.zr.hadoop;


import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @Description 获取文件时候验证文件的完整性
 * @Author super rui
 * @Date 2019/10/08
 */
public class CheckSumGet extends Configured implements Tool {
    private FileSystem fs;
    private InputStream is;
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        fs = new RawLocalFileSystem();
        fs.initialize(URI.create(args[0]), conf);
        is = fs.open(new Path(args[0]));
        byte[] buff = new byte[1024];
        int len = is.read(buff);
        System.out.println(new String(buff, 0, len));
        is.close();

        fs = new LocalFileSystem(fs);
        is = fs.open(new Path(args[1]));
        byte[] buff1 = new byte[1024];
        int len1 = is.read(buff1);
        System.out.println(new String(buff1, 0, len1));
        is.close();
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new CheckSumGet(), args));
    }
}