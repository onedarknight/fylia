/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.db;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.tmrnd.fylia.api.OltParams;
 
public class OltParamsDB {
     
    public static HashMap<Integer, OltParams> oltparams = new HashMap<>();
    static{
        oltparams.put(1, new OltParams(1, 12, 33, 48,83));
        oltparams.put(2, new OltParams(2, 12, 33, 48,83));
        oltparams.put(3, new OltParams(3, 12, 33, 48,83));
    }
     
    public static List<OltParams> getOltparams(){
        return new ArrayList<OltParams>(oltparams.values());
    }
     
    public static OltParams getOltparam(Integer id){
        return oltparams.get(id);
    }
     
    public static void updateOltParams(Integer id, OltParams oltparam){
        oltparams.put(id, oltparam);
    }
     
    public static void removeEmployee(Integer id){
        oltparams.remove(id);
    }
}