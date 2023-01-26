package com.driver.services.impl;
import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.driver.model.Driver;
import com.driver.repository.DriverRepository;
@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	DriverRepository driverRepository3;
	@Autowired
	CabRepository cabRepository3;
	@Override
	public void register(String mobile, String password){

	}
	@Override
	public void removeDriver(int driverId){

	}
	@Override
	public void updateStatus(int driverId){

	}
}
