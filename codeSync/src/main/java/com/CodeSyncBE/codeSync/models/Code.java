package com.CodeSyncBE.codeSync.models;

public class Code {
    private String name, code;

    public Code(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode() {
        this.code = code;
    }

}
