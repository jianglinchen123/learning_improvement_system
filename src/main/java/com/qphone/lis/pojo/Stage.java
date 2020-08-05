package com.qphone.lis.pojo;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Administrator
 */
public class Stage implements WritableComparable<Stage> {
    private int id;
    private int subject_id;
    private String subject_name;
    private int pId;

    public Stage() {
    }

    public Stage(int id, int subject_id, String subject_name, int pId) {
        this.id = id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.pId = pId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }


    @Override
    public String toString() {
        return id + "\001" + subject_id + "\001" + subject_name + "\001" + pId;
    }

    @Override
    public int compareTo(Stage o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeInt(subject_id);
        dataOutput.writeUTF(subject_name);
        dataOutput.writeInt(pId);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readInt();
        subject_id = dataInput.readInt();
        subject_name = dataInput.readUTF();
        pId = dataInput.readInt();
    }
}
