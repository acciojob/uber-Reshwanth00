package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		customerRepository2.delete(customerRepository2.findById(customerId).get());
	}
	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		boolean booked = false;
		List<Driver> driverList = driverRepository2.findAll();
		TripBooking tripBooking = new TripBooking();
		for(Driver driver: driverList){
			if(driver.getCab().getAvailable()){
				tripBooking.setStatus(TripStatus.CONFIRMED);
				tripBooking.setFromLocation(fromLocation);
				tripBooking.setToLocation(toLocation);
				tripBooking.setDistanceInKm(distanceInKm);
				tripBooking.setBill(driver.getCab().getPerKmRate()*distanceInKm);
				tripBookingRepository2.save(tripBooking);
				Customer customer = customerRepository2.findById(customerId).get();
				List<TripBooking> customerTripBookingList = customer.getTripBookingList();
				customerTripBookingList.add(tripBooking);
				customer.setTripBookingList(customerTripBookingList);
				customerRepository2.save(customer);
				List<TripBooking> driverTripBookingList =driver.getTripBookingList();
				driverTripBookingList.add(tripBooking);
				driver.setTripBookingList(driverTripBookingList);
				driverRepository2.save(driver);
				booked = true;
			}
		}
		if(!booked)throw new Exception("No cab available!");
		return tripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId){
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.getDriver().getCab().setAvailable(true);
		tripBooking.setBill(0);
		tripBooking.setStatus(TripStatus.CANCELED);
		tripBooking.getCustomer().getTripBookingList().remove(tripBooking);
		tripBookingRepository2.save(tripBooking);

	}
	@Override
	public void completeTrip(Integer tripId){
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.getDriver().getCab().setAvailable(true);
		tripBooking.setStatus(TripStatus.COMPLETED);
		tripBookingRepository2.save(tripBooking);
	}
}
