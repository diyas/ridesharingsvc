package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.model.payload.ErrorJson;
import com.test.ridesharingsvc.model.payload.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
public class ErrorCtl implements ErrorController {

    private static final String PATH = "/error";

    @Value("true")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public Response error(HttpServletRequest request, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring.
        // Here we just define response body.
        ErrorJson err = new ErrorJson(response.getStatus(), getErrorAttributes(request, true));
        Response resp = new Response();
        resp.setCode(err.getStatus());
        resp.setMessage(err.getError());
        return resp;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
