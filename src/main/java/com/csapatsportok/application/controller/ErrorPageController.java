package com.csapatsportok.application.controller;

import antlr.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

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
        ServletWebRequest servletWebRequest = new ServletWebRequest(request); // get the attributes of the request
        Map<String, Object> error = this.errorAttributes.getErrorAttributes(servletWebRequest, true); // get only the attributes concerning the error

        // add attributes to the model
        model.addAttribute("status", error.get("status"));
        model.addAttribute("error", error.get("error"));

        String message;
        if (error.get("status").toString().equals("404")) { // if status code == 404 display specific error msg to user
            message = "Not found: " + error.get("path");
        } else {
            message = error.get("message").toString();
        }

        model.addAttribute("message", message);
        model.addAttribute("path", error.get("path"));

        model.addAttribute("timestamp", error.get("timestamp"));

        //reworking invalid urls and specifying the appropriate format
        String path = error.get("path").toString();
        long countSymbol = path.chars().filter(ch -> ch == '/').count();

//        Map<String, String> thymeleafE = new HashMap<>(); //contains html elements
//        thymeleafE.put("cssPath","css/styles.css");
//        thymeleafE.put("whiteThemePath","css/whitedark.css");
//        thymeleafE.put("errorImage","images/error_img.png");
//        thymeleafE.put("darkMode","images/darkmode.png");
//        thymeleafE.put("whiteMode","images/whitemode.png");
//        thymeleafE.put("themeSwitch","js/main.js");
//
//        if(countSymbol > 1){ //If the url contains more than one "/"
//            StringBuilder sb = new StringBuilder();
//            for(int i=0;i<countSymbol-1;i++){
//                sb.append("../"); //the correct path form
//            }
//            Iterator elements = thymeleafE.entrySet().iterator();
//            while (elements.hasNext()) {
//                Map.Entry element = (Map.Entry) elements.next();
//                thymeleafE.put((String) element.getKey(),sb.toString() + element.getValue());
//            }
//        }
//        model.addAllAttributes(thymeleafE);

        LOG.error(error.get("timestamp").toString());
        LOG.error(error.get("status").toString());
        LOG.error(error.get("error").toString());
        LOG.error(error.get("message").toString());
        LOG.error(error.get("path").toString());

        return "error";
    }
}
