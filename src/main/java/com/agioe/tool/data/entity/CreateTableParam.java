package com.agioe.tool.data.entity;

import lombok.Data;

@Data
public class CreateTableParam {
    private String tableName;
    private String seqName;
    private String originalTableName;
}
