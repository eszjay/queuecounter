/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

public class ApiException extends Exception {
    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

}
