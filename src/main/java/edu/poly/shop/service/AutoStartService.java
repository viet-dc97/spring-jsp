/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class AutoStartService {

    @PostConstruct
    public void ahihi() {
        System.out.println("Loading!");
    }
}
