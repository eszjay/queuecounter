/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.api;

import com.github.eszjay.queuecounter.Application;
import com.github.eszjay.queuecounter.model.CurrentResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

@Controller
public class DefaultApiController implements DefaultApi {

    public ResponseEntity<CurrentResults> getCurrentResults() {

        CurrentResults currentResults = new CurrentResults();
        currentResults.setAboveThresholdCount(Application.getThresholdCount());
        currentResults.setMax(Application.getCurrentMax());
        currentResults.setMin(Application.getCurrentMin());
        currentResults.setThreshold(Application.getThreshold());
        currentResults.setTotalCount(Application.getTotalCount());

        return new ResponseEntity<CurrentResults>(currentResults, HttpStatus.OK);
    }

}
