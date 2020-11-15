package com.csapatsportok.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorPageController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private ErrorAttributes errorAttributes;

    @Autowired
    public void setErrorAttributes(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public String error(Model model, HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Map<String, Object> error = this.errorAttributes.getErrorAttributes(servletWebRequest, true);

        model.addAttribute("status", error.get("status"));
        model.addAttribute("error", error.get("error"));

        String message;
        if (error.get("status").toString().equals("404")) { // If status code == 404 display specific error msg to user
            message = "Not found: " + error.get("path");
        } else {
            message = error.get("message").toString();
        }

        model.addAttribute("message", message);
        model.addAttribute("path", error.get("path"));

        model.addAttribute("timestamp", error.get("timestamp"));

        // Reworking invalid urls and specifying the appropriate format
        String path = error.get("path").toString();
        long countSymbol = path.chars().filter(ch -> ch == '/').count();

        LOG.error(error.get("timestamp").toString());
        LOG.error(error.get("status").toString());
        LOG.error(error.get("error").toString());
        LOG.error(error.get("message").toString());
        LOG.error(error.get("path").toString());

        return "error/error";
    }
}
