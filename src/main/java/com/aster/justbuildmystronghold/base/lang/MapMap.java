package com.aster.justbuildmystronghold.base.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMap<E,R,T> {
    private Map<E, Map<R,T>> mapMap ;

    public MapMap() {
        mapMap=new HashMap<>();
    }

    public void put(E e,R r,T t){
        Map<R, T> rtMap = mapMap.get(e);
        if (rtMap == null) rtMap=new HashMap<>();
        rtMap.put(r,t);
        mapMap.put(e,rtMap);
    }

    public T get(E e,R r){
        Map<R, T> rtMap = mapMap.get(e);
        if (rtMap==null) return null;
        return rtMap.get(r);
    }

    public Map<R,T> getMap(E e){
        return mapMap.get(e);
    }

    private Set<E> keySet(){
        return mapMap.keySet();
    }



}
