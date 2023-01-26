package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

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
		boolean available = false;
		List<Driver> driverList = driverRepository2.findAll();
		for(Driver driver: driverList){
			if(driver.getCab().isAvailable()){
				// creating the trip
				TripBooking tripBooking = new TripBooking();
				// setting costumer to the trip
				tripBooking.setCustomer(customerRepository2.findById(customerId).get());
				// setting driver to the list
				tripBooking.setDriver(driverRepository2.findById(driver.getDriverId()).get());
				// creating tripBook entity
				tripBooking.setFromLocation(fromLocation);
				tripBooking.setToLocation(toLocation);
				tripBooking.setDistanceInKm(distanceInKm);
				tripBooking.setStatus(TripStatus.CONFIRMED);
				tripBooking.setBill(driver.getCab().getPerKmRate()*distanceInKm);
				// setting trip to the database and done with the setting the trip connection
				tripBookingRepository2.save(tripBooking);
				// now setting the customer to the trip
				// getting the customer form the database
				Customer customer = customerRepository2.findById(customerId).get();
				// getting the list of trips from that customer entity
				List<TripBooking> tripBookingList = customer.getTripBookingList();
				// setting the current trip to thr list
				tripBookingList.add(tripBooking);
				// and changing the list and customer part is completed
				customer.setTripBookingList(tripBookingList);
				// now change in the database of the customer
				// delete the previous
				customerRepository2.deleteById(customerId);
				// add the new one
				customerRepository2.save(customer);
				// creating the driver entity
				Driver driver1 = driverRepository2.findById(driver.getDriverId()).get();
				// now get the list trips done by the driver
				List<TripBooking> tripBookings = driver1.getTripBookingList();
				// now add this current trip to the driver
				tripBookings.add(tripBooking);
				// update the driver trips list
				driver1.setTripBookingList(tripBookings);
				// now update the driver database
				// delete the previous one
				driverRepository2.deleteById(driver.getDriverId());
				// now add the updated database
				driverRepository2.save(driver1);
				available = true;
				return tripBooking;
			}
		}
		if(!available){
			throw new Exception("No cab available!");
		}
		return null;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.CANCELED);
		tripBooking.getDrive().getCab().setAvailable(true);
		tripBooking.setBill(0);
		tripBookingRepository2.save(tripBooking);
	}
	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setBill(0);
		tripBooking.getDrive().getCab().setAvailable(true);
		tripBooking.setStatus(TripStatus.COMPLETED);
		tripBookingRepository2.save(tripBooking);
	}
}
