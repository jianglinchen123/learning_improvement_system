package com.qphone.lis.pojo;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Administrator
 */
public class AnswerPaper implements WritableComparable<AnswerPaper> {

    private int exam_id;
    private int paper_id;
    private String examinee_id;
    private String examinee_name;
    private String class_id;
    private int exam_time;
    private int objective_mark;
    private int subject_mark;
    private int question_id;
    private int real_score;

    public AnswerPaper() {
    }

    public AnswerPaper(int exam_id, int paper_id, String examinee_id, String examinee_name, String class_id, int exam_time, int objective_mark, int subject_mark, int question_id, int real_score) {
        this.exam_id = exam_id;
        this.paper_id = paper_id;
        this.examinee_id = examinee_id;
        this.examinee_name = examinee_name;
        this.class_id = class_id;
        this.exam_time = exam_time;
        this.objective_mark = objective_mark;
        this.subject_mark = subject_mark;
        this.question_id = question_id;
        this.real_score = real_score;
    }

    public String getExaminee_name() {
        return examinee_name;
    }

    public void setExaminee_name(String examinee_name) {
        this.examinee_name = examinee_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public String getExaminee_id() {
        return examinee_id;
    }

    public void setExaminee_id(String examinee_id) {
        this.examinee_id = examinee_id;
    }

    public int getExam_time() {
        return exam_time;
    }

    public void setExam_time(int exam_time) {
        this.exam_time = exam_time;
    }

    public int getObjective_mark() {
        return objective_mark;
    }

    public void setObjective_mark(int objective_mark) {
        this.objective_mark = objective_mark;
    }

    public int getSubject_mark() {
        return subject_mark;
    }

    public void setSubject_mark(int subject_mark) {
        this.subject_mark = subject_mark;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getReal_score() {
        return real_score;
    }

    public void setReal_score(int real_score) {
        this.real_score = real_score;
    }

    @Override
    public int compareTo(AnswerPaper o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(exam_id);
        dataOutput.writeInt(paper_id);
        dataOutput.writeUTF(examinee_id);
        dataOutput.writeUTF(examinee_name);
        dataOutput.writeUTF(class_id);
        dataOutput.writeInt(exam_time);
        dataOutput.writeInt(objective_mark);
        dataOutput.writeInt(subject_mark);
        dataOutput.writeInt(question_id);
        dataOutput.writeInt(real_score);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        exam_id = dataInput.readInt();
        paper_id = dataInput.readInt();
        examinee_id = dataInput.readUTF();
        examinee_name = dataInput.readUTF();
        class_id = dataInput.readUTF();
        exam_time = dataInput.readInt();
        objective_mark = dataInput.readInt();
        subject_mark = dataInput.readInt();
        question_id = dataInput.readInt();
        real_score = dataInput.readInt();
    }

    @Override
    public String toString() {
        return exam_id + "\001" + paper_id + "\001" + examinee_id + "\001" + examinee_name + "\001" + class_id + "\001" + exam_time + "\001" + objective_mark + "\001" + subject_mark + "\001" + question_id + "\001" + real_score;
    }
}
