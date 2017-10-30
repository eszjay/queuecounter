/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.api;

import com.github.eszjay.queuecounter.model.CurrentResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

@Api(value = "all", description = "the all API")
public interface DefaultApi {

    @ApiOperation(value = "Get all fields in one request", notes = "", response = CurrentResults.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = CurrentResults.class)})

    @RequestMapping(value = "/all",
            produces = {"application/json", "application/xml"},
            method = RequestMethod.GET)
    ResponseEntity<CurrentResults> getCurrentResults();

}
