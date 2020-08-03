package com.qphone.lis.pojo;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Administrator
 */
public class AnswerPaper implements WritableComparable<AnswerPaper> {
    private int id;
    private int examId;
    private String examinee_num;
    private String examinee_name;
    private String class_name;
    private String exam_time;
    private String objective_mark;
    private String subjective_mark;
    private int questionId;
    private int score;

    public AnswerPaper(int id, int examId, String examinee_num, String examinee_name, String class_name, String exam_time, String objective_mark, String subjective_mark, int questionId, int score) {
        this.id = id;
        this.examId = examId;
        this.examinee_num = examinee_num;
        this.examinee_name = examinee_name;
        this.class_name = class_name;
        this.exam_time = exam_time;
        this.objective_mark = objective_mark;
        this.subjective_mark = subjective_mark;
        this.questionId = questionId;
        this.score = score;
    }

    public AnswerPaper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExaminee_num() {
        return examinee_num;
    }

    public void setExaminee_num(String examinee_num) {
        this.examinee_num = examinee_num;
    }

    public String getExaminee_name() {
        return examinee_name;
    }

    public void setExaminee_name(String examinee_name) {
        this.examinee_name = examinee_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getObjective_mark() {
        return objective_mark;
    }

    public void setObjective_mark(String objective_mark) {
        this.objective_mark = objective_mark;
    }

    public String getSubjective_mark() {
        return subjective_mark;
    }

    public void setSubjective_mark(String subjective_mark) {
        this.subjective_mark = subjective_mark;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(AnswerPaper o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeInt(examId);
        dataOutput.writeUTF(examinee_num);
        dataOutput.writeUTF(examinee_name);
        dataOutput.writeUTF(class_name);
        dataOutput.writeUTF(exam_time);
        dataOutput.writeUTF(objective_mark);
        dataOutput.writeUTF(subjective_mark);
        dataOutput.writeInt(questionId);
        dataOutput.writeInt(score);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readInt();
        examId = dataInput.readInt();
        examinee_num = dataInput.readUTF();
        examinee_name =dataInput.readUTF();
        class_name = dataInput.readUTF();
        exam_time = dataInput.readUTF();
        objective_mark = dataInput.readUTF();
        subjective_mark = dataInput.readUTF();
        questionId = dataInput.readInt();
        score = dataInput.readInt();
    }
}
