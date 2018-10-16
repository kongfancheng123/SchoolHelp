package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTableParam {
    private String tableName;
    private String seqName;
    private String originalTableName;
}
