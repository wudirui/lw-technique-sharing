package com.zr.hadoop;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 * @Description 文件的压缩
 * @Author super rui
 * @Date 2019/10/08
 */
public class CompressorCodec extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        String input = conf.get("input");
        String output = conf.get("output");
        LocalFileSystem lfs = FileSystem.getLocal(conf);
        FileSystem rfs = FileSystem.get(URI.create(output), conf);
        FSDataInputStream is = lfs.open(new Path(input));
        FSDataOutputStream os = rfs.create(new Path(output));
        CompressionCodecFactory ccf = new CompressionCodecFactory(conf);
        //把路径传进去，根据指定的后缀名获取编解码器
        CompressionCodec codec = ccf.getCodec(new Path(output));
        CompressionOutputStream cos = codec.createOutputStream(os);
        System.out.println(codec.getClass().getName());
        IOUtils.copyBytes(is, cos, 1024, true);
        //close
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new CompressorCodec(), args));
    }
}
