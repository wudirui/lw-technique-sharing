### <p align="center">Hadoop的I/O操作</p>

---

#### 一、数据完整性
&ensp;&ensp;&ensp; 检测数据的损坏的措施是，在数据第一次引入系统时计算校验和（checkSum）并在数据通过一个不可靠的通道进行传输时再次计算校验和，这样就能发现数据是否损坏。
Hdfs存储着每个数据块的复本（replica），因此可以通用复本来修复损坏的数据块。hdfs的LocalFileSystem执行客户端的校验和验证。LocalFileSystem通过ChecksumFileSystem来完成自己的任务。

#### 二、压缩
&ensp;&ensp;&ensp; 文件压缩有两大好处：1、节省磁盘空间；2、挺高在网络空间的传输速度。有很多不同的压缩格式、工具和算法，他们各有千秋。

#### 三、序列化
&ensp;&ensp;&ensp; 序列化是将结构化对象转换成字节流在网络空间进行传递，反序列化是将字节流转换成结构化的对象

#### 四、序列化框架
&ensp;&ensp;&ensp; 

#### 五、Avro
&ensp;&ensp;&ensp; 

#### 六、基于文件的数据结构
&ensp;&ensp;&ensp; 