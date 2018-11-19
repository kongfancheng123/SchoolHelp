package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTableParam {
    private String tableName;
    private String seqName;
    private String originalTableName;

    @Override
    public String toString() {
        return "CreateTableParam{" +
                "tableName='" + tableName + '\'' +
                ", seqName='" + seqName + '\'' +
                ", originalTableName='" + originalTableName + '\'' +
                '}';
    }
}
