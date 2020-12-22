package com.example.demo.api;

import com.example.demo.DeviceRepository;
import com.example.demo.errors.DeviceNotFoundException;
import com.example.demo.errors.DeviceNotValidException;
import com.example.demo.model.Device;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class DeviceController {
    private Pattern serialNumberPattern = Pattern.compile("^[a-zA-Z0-9\\-]+$");
    private final DeviceRepository repository;

    DeviceController(DeviceRepository repository) {
        this.repository = repository;
    }

    /* create a new device */
    @PostMapping("/devices")
    Device newDevice(@RequestBody Device newDevice) {
        validateDevice(newDevice);
        return repository.save(newDevice);
    }

    /** retrieve device by serial number
     * I assume serial numbers are unique for each device */
    @GetMapping("/devices/{id}")
    public Device bySerialNumber(@PathVariable String id) {
        Device device = repository.findBySerialNumber(id);
        if (device == null)
            throw new DeviceNotFoundException("serial.number.not.found");
        else
            return device;
    }

    /** I assume we can update the name and machine code for
     * each device only but not the serial number */
    @PutMapping("/devices/{id}")
    Device updateDevice(@RequestBody Device newDevice, @PathVariable String id) {
        validateDevice(newDevice);
        Device device = repository.findBySerialNumber(id);
        if (device == null) {
            throw new DeviceNotFoundException("serial.number.not.found");
        }
        device.setName(newDevice.getName());
        device.setMachineCode(newDevice.getMachineCode());
        return repository.save(device);
    }

    /** search by machine code
     * like this, for example
     * curl -v localhost:8080/devices/search-machine-code?q=abc
     * */
    @GetMapping("/devices/search-machine-code")
    public List<Device> byMachineCode(@RequestParam String q) {
        List<Device> devices = repository.findByMachineCode(q);
        if (devices.isEmpty())
            throw new DeviceNotFoundException("machine.code.not.found");
        else
            return devices;
    }

    private void validateDevice(Device newDevice) {
        /** when we validate an object
         * it is probably better to return the whole list of errors rather than
         * return them one by one  */
        List<String> errors = new ArrayList<>();
        Matcher matcher = serialNumberPattern.matcher(newDevice.getSerialNumber());
        if (!matcher.find()) {
            errors.add("serial.number.invalid");
        }
        if (newDevice.getMachineCode().isEmpty()) {
            errors.add("machine.code.invalid");
        }
        if (!errors.isEmpty()) {
            throw new DeviceNotValidException(errors);
        }
    }
}
