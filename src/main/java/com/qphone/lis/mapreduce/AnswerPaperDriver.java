package com.qphone.lis.mapreduce;

import com.google.inject.internal.cglib.core.$LocalVariablesSorter;
import com.qphone.lis.pojo.AnswerPaper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Administrator
 */
public class AnswerPaperDriver {
    public static class AnswerPaperMapper extends Mapper<LongWritable, Text, NullWritable, AnswerPaper> {
        AnswerPaper answerPaper = new AnswerPaper();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] infos = line.split("\001");
            for (int i = 0; i < infos.length; i++) {
                answerPaper.setId(Integer.parseInt(infos[0]));
                answerPaper.setExamId(Integer.parseInt(infos[1]));
                answerPaper.setExaminee_num(infos[5]);
                answerPaper.setExaminee_name(infos[4]);
                answerPaper.setClass_name(infos[7]);
                answerPaper.setExam_time(infos[9]);
                answerPaper.setObjective_mark(infos[11]);
                answerPaper.setSubjective_mark(infos[12]);

            }
        }
    }
}
