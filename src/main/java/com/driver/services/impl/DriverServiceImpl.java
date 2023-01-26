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
		Driver driver = new Driver();
		Cab cab = new Cab();
		driver.setMobile(mobile);
		driver.setPassword(password);
		cab.setPerKmRate(10);
		cab.setAvailable(true);
		cabRepository3.save(cab);
		driverRepository3.save(driver);
	}
	@Override
	public void removeDriver(int driverId){
		Driver driver = driverRepository3.findById(driverId).get();
		Cab cab = driver.getCab();
		driverRepository3.delete(driver);
		cabRepository3.delete(cab);
	}
	@Override
	public void updateStatus(int driverId){
		Driver driver=driverRepository3.findById(driverId).get();
		Cab cab=driver.getCab();
		cab.setAvailable(false);
		driverRepository3.save(driver);
	}
}
