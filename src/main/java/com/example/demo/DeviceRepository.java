package com.example.demo;

import com.example.demo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, String> {
    Optional<Device> findBySerialNumber(String serialNumber);
    List<Device> findByMachineCode(String serialNumber);
    Device save(Device device);
}

