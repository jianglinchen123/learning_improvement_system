package com.qphone.lis.mapreduce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qphone.lis.md5.MD5;
import com.qphone.lis.pojo.AnswerPaper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public class AnswerPaperDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(AnswerPaperDriver.class);
        job.setMapperClass(AnswerPaperMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(AnswerPaper.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        Path dstPath = new Path(args[1]);
        FileSystem fileSystem = dstPath.getFileSystem(conf);
        if (fileSystem.exists(dstPath)) {
            fileSystem.delete(dstPath,true);
        }
        FileOutputFormat.setOutputPath(job,dstPath);
        Boolean res = job.waitForCompletion(true);
        System.out.println(res ? "true" : "false");
    }

    public static class AnswerPaperMapper extends Mapper<LongWritable, Text, NullWritable, AnswerPaper> {
        AnswerPaper answerPaper = new AnswerPaper();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] infos = line.split("\001");
            answerPaper.setExam_id(Integer.parseInt(infos[1]));
            answerPaper.setPaper_id(Integer.parseInt(infos[2]));
            answerPaper.setExaminee_id(infos[3]);
            answerPaper.setExaminee_name(MD5.getMD5(infos[4]));
            answerPaper.setClass_id(infos[6]);
            answerPaper.setExam_time(Integer.parseInt(infos[9]));
            answerPaper.setObjective_mark(Integer.parseInt(infos[11]));
            if ("null".equals(infos[12]) || infos[12] == null || infos[12].isEmpty()) {
                infos[12] = ""+0;
            }
            answerPaper.setSubject_mark(Integer.parseInt(infos[12]));
            JSONObject json_objective = JSON.parseObject(infos[16]);
            Set<Map.Entry<String, Object>> object_entry = json_objective.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = object_entry.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                String question_id = next.getKey();
                String real_score = json_objective.getString("score");
                if ("null".equals(question_id) || question_id == null || question_id.isEmpty()) {
                    question_id = ""+0;
                }
                answerPaper.setQuestion_id(Integer.parseInt(question_id));
                if ("null".equals(real_score) || real_score == null || real_score.isEmpty()) {
                    real_score = ""+0;
                }
                answerPaper.setReal_score(Integer.parseInt(real_score));
                if ("0".equals(real_score)) {
                    answerPaper.setIs_right(0);
                } else {
                    answerPaper.setIs_right(1);
                }
                context.write(NullWritable.get(),answerPaper);
            }
            JSONObject json_subjective = JSON.parseObject(infos[17]);
            Set<Map.Entry<String, Object>> subject_entry = json_objective.entrySet();
            Iterator<Map.Entry<String, Object>> it = subject_entry.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = it.next();
                String question_id = next.getKey();
                String real_score = json_subjective.getString("score");
                if ("null".equals(question_id) || question_id == null || question_id.isEmpty()) {
                    question_id = ""+0;
                }
                answerPaper.setQuestion_id(Integer.parseInt(question_id));
                if ("null".equals(real_score) || real_score == null || real_score.isEmpty()) {
                    real_score = ""+0;
                }
                if ("0".equals(real_score)) {
                    answerPaper.setIs_right(0);
                } else {
                    answerPaper.setIs_right(1);
                }
                answerPaper.setReal_score(Integer.parseInt(real_score));
                context.write(NullWritable.get(),answerPaper);
            }
        }
    }
}
