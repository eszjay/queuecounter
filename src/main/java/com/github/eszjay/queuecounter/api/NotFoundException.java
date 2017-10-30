/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

public class NotFoundException extends ApiException {
    private int code;

    public NotFoundException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }

}
