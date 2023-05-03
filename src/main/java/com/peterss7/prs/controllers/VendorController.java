package com.peterss7.prs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.services.VendorService;
import com.peterss7.prs.specifications.VendorSpecifications;



@RestController
@RequestMapping("/vendors")
@CrossOrigin("http://localhost:4200")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@GetMapping("")
	public ResponseEntity<List<Vendor>> findVendorsByFields(
			
		@RequestParam(required = false) String code,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String address,
		@RequestParam(required = false) String city,
		@RequestParam(required = false) String state,
		@RequestParam(required = false) String zip,
		@RequestParam(required = false) String phone,
		@RequestParam(required = false) String email){
	
		try {
			
			if ((code == null)  && 
				(name == null) &&
				(address == null)  &&
				(city == null)     &&
				(state == null)     &&
				(zip == null)&&
				(phone == null) &&
				(email == null)){
				
				List<Vendor> vendors = vendorService.findAllVendors(); 
				
				return ResponseEntity.ok(vendors);
				
			} else{
				
				Vendor searchTerm = new Vendor();
				
				if (code != null) {
					searchTerm.setCode(code);	
				}
				if (name != null) {
					searchTerm.setName(name);	
				}
				if (address != null) {
					searchTerm.setAddress(address);	
				}				
				if (city != null) {
					searchTerm.setCity(city);	
				}
				if (state != null) {
					searchTerm.setState(state);	
				}
				if (zip != null) {
					searchTerm.setZip(zip);	
				}
				if (phone != null) {
					searchTerm.setPhone(phone);	
				}
				if (email != null) {
					searchTerm.setEmail(email);	
				}
				
				
				List<Vendor> vendors = vendorService.findVendorsByFields(
					VendorSpecifications.getVendorSpecs(searchTerm));
				
				return ResponseEntity.ok(vendors);
				
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}	
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Vendor> findVendorById(@PathVariable int id) {
		
		try {
			Vendor vendor = vendorService.findVendorById(id);
			return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>( null, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping("")
	public ResponseEntity<Vendor> createVendor(@RequestBody Vendor newVendor){		
		try {
			Vendor vendor = vendorService.createVendor(newVendor);
			return new ResponseEntity<>(vendor, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Vendor> updateVendor(@RequestBody Vendor updatedVendor){
		return ResponseEntity.ok(vendorService.updateVendor(updatedVendor));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVendor(@PathVariable int id){
		return vendorService.deleteVendorById(id);
	}	
}