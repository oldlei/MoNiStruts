package com.dy.java17;

import java.util.HashMap;

public class Ban_sj {
    private String className;

    private HashMap<String,String> jresult_sj;

    public Ban_sj(String className, HashMap<String, String> jresult_sj) {
        this.className = className;
        this.jresult_sj = jresult_sj;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setJresult_sj(HashMap<String, String> jresult_sj) {
        this.jresult_sj = jresult_sj;
    }

    public String getClassName() {
        return className;
    }

    public HashMap<String, String> getJresult_sj() {
        return jresult_sj;
    }
}
