/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorManager {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletRequest rq;
    private String pathError;
    private String pathSuccess;
    private List<String> error;
    private String successContent;

    public void start(String pathSuccess) {
        start(pathSuccess, pathSuccess);
    }

    public void start(String pathError, String pathSuccess) {
        start(pathError, pathSuccess, new ArrayList<>());
    }

    public void start(String pathError, String pathSuccess, List<String> error) {
        this.pathError = pathError;
        this.pathSuccess = pathSuccess;
        this.error = error;
    }

    public void add(String error) {
        this.error.add(error);
    }

    public void clear() {
        this.error.clear();
    }

    public void success(String content) {
        successContent = content;
    }

    public String success() {
        return successContent;
    }

    public boolean exists() {
        return !error.isEmpty();
    }

    public String path() {
        boolean success = error.isEmpty();
        rq.setAttribute("error", this.error);
        if (success) {
            rq.setAttribute("success", successContent);
        }
        return success ? this.pathSuccess : this.pathError;
    }
}
