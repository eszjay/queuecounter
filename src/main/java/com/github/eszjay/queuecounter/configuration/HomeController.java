/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}
