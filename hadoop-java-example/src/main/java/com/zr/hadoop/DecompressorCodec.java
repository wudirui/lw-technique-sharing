package com.zr.hadoop;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 文件的解压
 * @Author super rui
 * @Date 2019/10/08
 */
public class DecompressorCodec extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        String input = conf.get("input");
        String output = conf.get("output");
        FileSystem rfs = FileSystem.get(URI.create(input), conf);
        LocalFileSystem lfs = FileSystem.getLocal(conf);
        FSDataInputStream is = rfs.open(new Path(input));
        FSDataOutputStream os = lfs.create(new Path(output));

        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(new Path(input));
        CompressionInputStream cis = codec.createInputStream(is);
        IOUtils.copyBytes(cis, os, 1024, true);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new DecompressorCodec(), args));
    }
}
