### <p align="center">hdfs之java接口</p>

---

#### 一、从Hadoop的url读取数据

- 代码

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
     
     
- 运行结果

      I	1
      LW	1
      am	1
      hadoop	1
      hello	1
      to	1
      welcome	1
      world	1
      
- 方法总结

&ensp;&ensp;&ensp; 这种方式采用java.net.URL对象调用setURLStreamHandlerFactory()方法，将FsUrlStreamHandlerFactory对象作为输入参数，
但是每个java虚拟机只能调用一次这个方法，常在静态方法中调用。

      
#### 二、通过FileSystem API读取数据

- 代码
  
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

- 运行结果
    
      I	1
      LW	1
      am	1
      hadoop	1
      hello	1
      to	1
      welcome	1
      world	1
      
- 方法总结

   FileSystem 是通用的文件系统API，所以第一步要实现文件系统的实例，FilaSystem实例有以下三个静态方法
      
      public static FileSystem get(Configuration conf)
      public static FileSystem get(URI uri, Configuration conf)
      public static FileSystem get(final URI uri, final Configuration conf, String user)
      
  Configuration对象封装了客户端或者服务端的各种配置，第一个方法返回默认的文件系统，第二个方法通过给定的url和权限来确定使用的
  文件系统，作为客户端访问的文件系统，对于安全来说至关重要。
      
      
    
   

    

