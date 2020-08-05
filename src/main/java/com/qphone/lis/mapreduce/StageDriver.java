package com.qphone.lis.mapreduce;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qphone.lis.pojo.Stage;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Administrator
 */
public class StageDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置文件
        Configuration conf = new Configuration();
        // 获取job对象
        Job job = Job.getInstance(conf);
        // 设置驱动类
        job.setJarByClass(StageDriver.class);
        // 设置Mapper类
        job.setMapperClass(StageMapper.class);
        // 设置Mapper输出类型
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Stage.class);
        // 设置输入路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        // 获取文件系统并判断输出路径是否存在，如果存在将其删除
        Path dstPath = new Path(args[1]);
        FileSystem fileSystem = dstPath.getFileSystem(conf);
        if (fileSystem.exists(dstPath)) {
            fileSystem.delete(dstPath,true);
        }
        // 设置输出路径
        FileOutputFormat.setOutputPath(job,dstPath);
        // 提交作业
        Boolean res = job.waitForCompletion(true);
        System.out.println(res ? "true" : "false");
    }

    public static class StageMapper extends Mapper<LongWritable, Text, NullWritable, Stage> {
        Stage stage = new Stage();
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\001");
            stage.setId(Integer.parseInt(split[0]));
            JSONArray parseArray = JSONArray.parseArray(split[2]);
            Iterator<Object> iterator = parseArray.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                JSONObject jsonObject = JSONObject.parseObject(next.toString());
                String subject_id = jsonObject.getString("id");
                String subject_name = jsonObject.getString("name");
                String pId = jsonObject.getString("pId");
                stage.setSubject_id(Integer.parseInt(subject_id));
                stage.setSubject_name(subject_name);
                stage.setpId(Integer.parseInt(pId));
                context.write(NullWritable.get(),stage);
            }
        }
    }
}
