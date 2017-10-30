/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.api;

import com.github.eszjay.queuecounter.Application;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

@Controller
public class CountApiController implements CountApi {

    public ResponseEntity<String> getAboveThreshold() {
        return new ResponseEntity<String>(String.valueOf(Application.getThresholdCount()), HttpStatus.OK);
    }

    public ResponseEntity<String> getTotalCount() {
        return new ResponseEntity<String>(String.valueOf(Application.getTotalCount()), HttpStatus.OK);
    }

}
