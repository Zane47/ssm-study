package com.imooc.spring.ioc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


@ToString
public class Company {

    private List<String> rooms;

    private Set<String> rooms1;

    private Map<String, Computer> computerMap;

    private Properties info;

    public Set<String> getRooms1() {
        return rooms1;
    }

    public void setRooms1(Set<String> rooms1) {
        this.rooms1 = rooms1;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public Map<String, Computer> getComputerMap() {
        return computerMap;
    }

    public void setComputerMap(Map<String, Computer> computerMap) {
        this.computerMap = computerMap;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }
}
