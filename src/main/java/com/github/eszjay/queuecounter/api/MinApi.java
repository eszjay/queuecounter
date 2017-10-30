/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

@Api(value = "min", description = "the min API")
public interface MinApi {

    @ApiOperation(value = "Count of numbers above the threshold", notes = "Count of numbers above the threshold", response = Void.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Void.class)})

    @RequestMapping(value = "/min",
            produces = {"text/plain"},
            method = RequestMethod.GET)
    ResponseEntity<String> getMin();

}
