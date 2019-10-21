package com.zr.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

/**
 * @Description 通过文件扩展名选取codec解压缩文件
 * @Author super rui
 * @Date 2019/10/11
 */
public class FileDecompressor {
    public static void main(String[] args) throws IOException {
        String url = args[0];
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(url), configuration);
        Path inputPath = new Path(url);
        CompressionCodecFactory factory = new CompressionCodecFactory(configuration);
        CompressionCodec codec = factory.getCodec(inputPath);
        if (codec == null) {
            System.out.println("没有找到该类压缩");
            System.exit(1);
        }


        String outputUri = factory.removeSuffix(url, codec.getDefaultExtension());
        //CompressionCodecFactory的removeSuffix（）用来返回一个文件名，如README.txt.gz调用removeSuffix（）方法后，返回的是README.txt
        //CompressionCodec的getDefaultExtension()方法返回的是一个压缩算法的压缩扩展名，如gzip的是.gz

        System.out.println("压缩算法的生成文件的扩展名：" + codec.getDefaultExtension());
        System.out.println("解压后生成的文件名：" + outputUri);
        InputStream in = null;
        OutputStream out = null;
        in = codec.createInputStream(fileSystem.open(inputPath));
        out = fileSystem.create(new Path(outputUri));
        IOUtils.copyBytes(in, out, configuration);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
}
