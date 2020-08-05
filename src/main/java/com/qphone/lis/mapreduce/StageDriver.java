package com.qphone.lis.mapreduce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qphone.lis.pojo.AnswerPaper;
import com.qphone.lis.pojo.Stage;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Administrator
 */
public class StageDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(StageDriver.class);
        job.setMapperClass(StageMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Stage.class);
        FileInputFormat.setInputPaths(job,new Path("D://1.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D://output"));
        Boolean res = job.waitForCompletion(true);
        System.out.println(res ? "true" : "false");
    }

    public static class StageMapper extends Mapper<LongWritable, Text, NullWritable, Stage> {
        Stage stage = new Stage();
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            JSONArray parseArray = JSONArray.parseArray(line);
            for (int i = 0; i < 3; i++) {
                JSONObject object = parseArray.getJSONObject(i);
                JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String pId = jsonObject.getString("pId");
                stage.setId(Integer.parseInt(id));
                stage.setName(name);
                stage.setpId(Integer.parseInt(pId));
                context.write(NullWritable.get(),stage);
            }
        }
    }
}
