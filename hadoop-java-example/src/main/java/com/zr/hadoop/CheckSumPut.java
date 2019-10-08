package com.zr.hadoop;

import java.io.OutputStream;
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
 * @Description 上传文件时候验证文件的完整性
 * @Author super rui
 * @Date 2019/10/08
 */
public class CheckSumPut extends Configured implements Tool {

    private FileSystem fs;
    private OutputStream os;

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        //不做数据校验
        fs = new RawLocalFileSystem();
        //因为是直接new的对象，所以这里使用这个方法去传递配置文件
        fs.initialize(URI.create(args[0]), conf);
        os = fs.create(new Path(args[0]));
        os.write("123456".getBytes());
        os.close();

        //做数据校验
        fs = new LocalFileSystem(fs);
        os = fs.create(new Path(args[1]));
        os.write("09876".getBytes());
        os.close();
        return 0;
    }
    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new CheckSumPut(), args));
    }
}
