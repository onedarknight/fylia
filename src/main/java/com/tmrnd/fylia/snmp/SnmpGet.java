/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.snmp;

import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author user
 */
public class SnmpGet {
    
    private final String targetIpAddr = "192.168.20.88";
    private static final String  port    = "161";
    private static final Integer    snmpVersion  = SnmpConstants.version2c;
    private static String  community  = "public";
    private static String  oidValue1  = ".1.3.6.1.2.1.1.6.0";
    private static String  oidValue2  = ".1.3.6.1.2.1.1.7.0";
    
    public void getParam() throws IOException{
        System.out.println("SNMP GET OLT");
        
        TransportMapping transport = new DefaultUdpTransportMapping();
        transport.listen();
        
        // Create Target Address object
        CommunityTarget comtarget = new CommunityTarget();
        comtarget.setCommunity(new OctetString(community));
        comtarget.setVersion(snmpVersion);
        comtarget.setAddress(new UdpAddress(targetIpAddr + "/" + port));
        comtarget.setRetries(2);
        comtarget.setTimeout(1000);
        
        // Create the PDU object
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oidValue1)));
        pdu.add(new VariableBinding(new OID(oidValue2)));
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));
        
        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(transport);
        
        System.out.println("Sending Request to OLT Snmp Agent...");
        ResponseEvent response = snmp.get(pdu, comtarget);
        
        
        // Process Agent Response
        if (response != null)
        {
            System.out.println("Got Response from OLT SNMP Agent");
            PDU responsePDU = response.getResponse();

            if (responsePDU != null)
            {
              int errorStatus = responsePDU.getErrorStatus();
              int errorIndex = responsePDU.getErrorIndex();
              String errorStatusText = responsePDU.getErrorStatusText();

              if (errorStatus == PDU.noError)
              {
                System.out.println("Snmp Get Response = " + responsePDU.getVariableBindings().get(1));
              }
              else
              {
                System.out.println("Error: Request Failed");
                System.out.println("Error Status = " + errorStatus);
                System.out.println("Error Index = " + errorIndex);
                System.out.println("Error Status Text = " + errorStatusText);
              }
          }
          else
          {
            System.out.println("Error: Response PDU is null");
          }
        }
        else
        {
          System.out.println("Error: Agent Timeout... ");
        }
        snmp.close();
    }
    
}
