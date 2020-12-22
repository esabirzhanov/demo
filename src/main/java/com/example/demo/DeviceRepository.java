package com.example.demo;

import com.example.demo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findBySerialNumber(String serialNumber);
    List<Device> findByMachineCode(String serialNumber);
    Device save(Device device);
}

