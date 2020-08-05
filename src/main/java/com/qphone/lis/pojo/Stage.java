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
    private String name;
    private int pId;

    public Stage() {
    }

    public Stage(int id, String name, int pId) {
        this.id = id;
        this.name = name;
        this.pId = pId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Override
    public int compareTo(Stage o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(pId);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readInt();
        name = dataInput.readUTF();
        pId = dataInput.readInt();
    }

    @Override
    public String toString() {
        return id + "\001" + name + "\001" + pId;
    }
}
