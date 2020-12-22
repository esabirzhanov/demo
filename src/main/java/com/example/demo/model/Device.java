package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
public class Device {
    @Id
    private String serialNumber;

    private String name;
    private String machineCode;

    public Device() {}

    public Device(String name, String serialNumber, String machineCode) throws Exception {
        this.name = name;
        this.machineCode = machineCode;
        this.serialNumber = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getMachineCode() {
        return machineCode;
    }
}
