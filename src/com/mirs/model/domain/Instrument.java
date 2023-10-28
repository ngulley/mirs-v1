package com.mirs.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class Instrument implements Serializable {
    private static final long serialVersionUID = 6688114759172186190L;
    private Integer id;
    private String name;
    private InstrumentType type;
    private String modelNum;
    private String serialNum;
    private InstrumentStatus status;

    public Instrument(Integer id, String name, InstrumentType type, String modelNum, String serialNum, InstrumentStatus status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.modelNum = modelNum;
        this.serialNum = serialNum;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InstrumentType getType() {
        return type;
    }

    public String getModelNum() {
        return modelNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public InstrumentStatus getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(InstrumentType type) {
        this.type = type;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setStatus(InstrumentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type && Objects.equals(modelNum, that.modelNum) && Objects.equals(serialNum, that.serialNum) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, modelNum, serialNum, status);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", modelNum='" + modelNum + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean validate() {
        if (id == null || name == null || type == null || modelNum == null || serialNum == null || status == null) {
            return false;
        }
        return true;
    }
}
