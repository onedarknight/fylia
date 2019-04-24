/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devicelayer;
import java.io.IOException;
import java.util.List;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeUtils;
import org.snmp4j.util.TreeEvent;

public class SnmpWalk {
    private String targetAddr;
    private String oidStr;
    private String commStr;
    private int snmpVersion;
    private String portNum;
    private String usage;

    SnmpWalk() throws IOException {
        // Set default value.
        targetAddr = null;
        oidStr = null;
        commStr = "public";
        snmpVersion = SnmpConstants.version2c;
        portNum =  "161";
        usage = "Usage: snmpwalk [-c communityName -p portNumber -v snmpVersion(1 or 2)] targetAddr oid";
    }

    private void execSnmpWalk() throws IOException {
        Address targetAddress = GenericAddress.parse("udp:"+ targetAddr + "/" + portNum);
        TransportMapping<? extends Address> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();

        // setting up target
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(commStr));
        target.setAddress(targetAddress);
        target.setRetries(3);
        target.setTimeout(1000 * 3);
        target.setVersion(snmpVersion);
        OID oid;
        try {
            oid = new OID(translateNameToOID(oidStr));
        } catch (Exception e) {
            System.err.println("Failed to understand the OID or object name.");
            throw e;
        }

        // Get MIB data.
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(target, oid);
        if(events == null || events.size() == 0) {
            System.out.println("No result returned.");
            System.exit(1);
        }

        // Handle the snmpwalk result.
        for (TreeEvent event : events) {
            if(event == null) {
                continue;
            }
            if (event.isError()) {
                System.err.println("oid [" + oid + "] " + event.getErrorMessage());
                continue;
            }

            VariableBinding[] varBindings = event.getVariableBindings();
            if(varBindings == null || varBindings.length == 0) {
                continue;
            }
            for (VariableBinding varBinding : varBindings) {
                if (varBinding == null) {
                    continue;
                }
                System.out.println(
                        varBinding.getOid() +
                                " : " +
                                varBinding.getVariable().getSyntaxString() +
                                " : " +
                                varBinding.getVariable());
            }
        }
        snmp.close();
    }

    private String translateNameToOID(String oidStr) {
        switch (oidStr) {
            case "oltTxPow":
                oidStr = ".1.3.6.1.2.1.1.1.0";
                break;
            case "oltRxPow":
                oidStr = " .1.3.6.1.2.1.1.4";
                break;
            case "onuTxPow":
                oidStr = " .1.3.6.1.2.1.1.5";
                break;
            case "onuRxPow":
                oidStr = " .1.3.6.1.2.1.1.6";
                break;

        }
        return oidStr;
    }

    private void setArgs(String[] args) {
        if(args.length < 2) {
            System.err.println(usage);
            System.exit(1);
        }

        for (int i=0; i<args.length; i++) {
            if("-c".equals(args[i])) {
                commStr = args[++i];
            }
            else if ("-v".equals(args[i])) {
                if(Integer.parseInt(args[++i]) == 1) {
                    snmpVersion = SnmpConstants.version1;
                }
                else {
                    snmpVersion = SnmpConstants.version2c;
                }
            }
            else if ("-p".equals(args[i])) {
                portNum = args[++i];
            }
            else{
                targetAddr = args[i++];
                oidStr = args[i];
            }
        }
        if(targetAddr == null || oidStr == null) {
            System.err.println(usage);
            System.exit(1);
        }
    }

// Delegate main function to Snmpwalk.
//    public static void main(String[] args) {
//        try{
//            Snmpwalk snmpwalk = new Snmpwalk();
//            snmpwalk.setArgs(args);
//            snmpwalk.execSnmpwalk();
//        }
//        catch(Exception e) {
//            System.err.println("----- An Exception happened as follows. Please confirm the usage etc. -----");
//            System.err.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
}