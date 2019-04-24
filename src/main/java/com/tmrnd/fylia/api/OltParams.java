/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

///**
// *
// * @author user
// */
//public class OltParams {
//    
//    @NotNull
//    private Integer id;
//    @NotBlank @Length(min=2, max=255)
//    private String adminStatus;
//    
//    private float onuTemp;
//    
//    private DateTime lastUptime;
//    private DateTime lastDowntime;
//    private String lastDownCause;
//    
//    
//    private Integer downloadSpeed;
//    private Integer uploadSpeed;
//    
//    private float oltTxPow;
//    private float oltRxPow;
//    private float onuTxPow;
//    private float onuRxPow;
//    
//    private float ranging;
//    private String becup;
//    private String becdown;
//    
//    private String bwInUtil;
//    private String bwOutUtil;
//    
//    private String freqDisconDaily;
//    private String dayDetail;
//    private String freqDisconWeekly;
//    private String weekDetail;
//    
//    private String neighbouringCustPonPort;
//    private String onlineSessionPonPort;
//    private String neighbouringCustDp;
//    private String onlineSessionDp;
//    
//     
//    public OltParams(){
//    }
//    
//   
// 
//    public OltParams(Integer id, String adminStatus, float onuTemp, DateTime lastUptime,DateTime lastDowntime,
//            String lastDownCause,Integer downloadSpeed,Integer uploadSpeed,float oltTxPow,float oltRxPow,
//            float onuTxPow,float onuRxPow,float ranging,String becup,String becdown,String bwInUtil,
//            String bwOutUtil,String freqDisconDaily,String dayDetail,String freqDisconWeekly,String weekDetail,
//            String neighbouringCustPonPort,String onlineSessionPonPort,String neighbouringCustDp,
//            String onlineSessionDp) {
//        this.id = id;
//        this.adminStatus = adminStatus;
//        this.onuTemp = onuTemp;
//        this.lastUptime = lastUptime;
//        
//        this.lastDowntime = lastDowntime;
//        this.lastDownCause = lastDownCause;
//        this.downloadSpeed = downloadSpeed;
//        this.uploadSpeed = uploadSpeed;
//        this.oltTxPow = oltTxPow;
//        this.oltRxPow = oltRxPow;
//        this.onuTxPow = onuTxPow;
//        this.onuRxPow = onuRxPow;
//        this.ranging = ranging;
//        this.becup = becup;
//        this.becdown = becdown;
//        
//        this.bwInUtil = bwInUtil;
//        this.bwOutUtil = bwOutUtil;
//        this.freqDisconDaily = freqDisconDaily;
//        this.dayDetail = dayDetail;
//        this.freqDisconWeekly = freqDisconWeekly;
//        this.weekDetail = weekDetail;
//        this.neighbouringCustPonPort = neighbouringCustPonPort;
//        
//        this.onlineSessionPonPort = onlineSessionPonPort;
//        this.neighbouringCustDp = neighbouringCustDp;
//        this.onlineSessionDp = onlineSessionDp;
//        
//    }
// 
//    public Integer getId() {
//        return id;
//    }
// 
//    public void setId(Integer id) {
//        this.id = id;
//    }
//  
//    @Override
//    public String toString() {
//        return "Emplyee [id=" + id + ", firstName=" + firstName + ", lastName="
//                + lastName + ", email=" + email + "]";
//    }
//    
//}

public class OltParams {
    

    private Integer id;
    private Integer oltTxPow;
    private Integer oltRxPow;
    private Integer onuTxPow;
    private Integer onuRxPow;
    
     
    public OltParams(){
    }
    
 
    public OltParams(Integer id, Integer oltTxPow,Integer oltRxPow,
            Integer onuTxPow,Integer onuRxPow) {
        this.id = id;

        this.oltTxPow = oltTxPow;
        this.oltRxPow = oltRxPow;
        this.onuTxPow = onuTxPow;
        this.onuRxPow = onuRxPow;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getOltTxPow() {
        return oltTxPow;
    }
 
    public void setOltTxPow(Integer oltTxPow) {
        this.oltTxPow = oltTxPow;
    }
    
    public Integer getoltRxPow() {
        return oltRxPow;
    }
 
    public void setoltRxPow(Integer oltRxPow) {
        this.oltRxPow = oltRxPow;
    }
    
    public Integer getOnuTxPow() {
        return onuTxPow;
    }
 
    public void setOnuTxPow(Integer onuTxPow) {
        this.onuTxPow = onuTxPow;
    }
    
    public Integer getonuRxPow() {
        return onuRxPow;
    }
 
    public void setonuRxPow(Integer onuRxPow) {
        this.onuRxPow = onuRxPow;
    }
  
    @Override
    public String toString() {
        return "Olt [id=" + id 
                + ", oltTxPow=" + oltTxPow 
                + ", oltRxPow="  + oltRxPow 
                + ", onuTxPow=" + onuTxPow 
                + ", onuRxPow=" + onuRxPow + "]";
    }
    
}

