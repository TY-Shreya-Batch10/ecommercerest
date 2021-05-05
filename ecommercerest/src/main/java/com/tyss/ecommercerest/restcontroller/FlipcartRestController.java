package com.tyss.ecommercerest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.ecommercerest.bean.FlipcartResponse;
import com.tyss.ecommercerest.bean.Item;
import com.tyss.ecommercerest.service.FlipcartService;

@RestController
public class FlipcartRestController {

	@Autowired
	FlipcartService service;
	
	@GetMapping(path = "/getItem", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public FlipcartResponse getItem(Integer id) {

		Item item = service.searchItem(id);
		FlipcartResponse response = new FlipcartResponse();
		
		if(item != null) {
			response.setStatusCode(200);
			response.setMessage("success");
			response.setItem(item);
		} else {
			response.setStatusCode(404);
			response.setMessage("item does not presesnt");
		}
		return response;
		
	}
	
	@GetMapping(path = "/getAllItems", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public FlipcartResponse getAllItem() {
		FlipcartResponse response  =  new FlipcartResponse();
		List<Item> itemList = service.getAllItems();
		if(itemList != null) {
			response.setStatusCode(200);
			response.setMessage("success");
			response.setItemList(itemList);
		} else {
			response.setStatusCode(404);
			response.setMessage("Items do not present");
		}
		return response;
	}
	
	@PostMapping(path = "/addItem", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE}, consumes = 
			{MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public FlipcartResponse addItem(@RequestBody Item item) {
		FlipcartResponse response  =  new FlipcartResponse();
		if(service.addItem(item)) {
			response.setStatusCode(200);
			response.setMessage("success");
	
		} else {
			response.setStatusCode(400);
			response.setMessage("Failure , Could not add the data");
		}
		return response;
	}
	
	@PutMapping(path = "/updateItem", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public FlipcartResponse updateItem(@RequestBody Item item) {
		FlipcartResponse response  =  new FlipcartResponse();
		if(service.updateItem(item)) {
			response.setStatusCode(200);
			response.setMessage("success");
		} else {
			response.setStatusCode(400);
			response.setMessage("Failure , Could not add the data");
		}
		return response;
	}
	
	@DeleteMapping(path = "/deleteItem/{id}" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public FlipcartResponse deleteItem(@PathVariable(name = "id") Integer id) {
		FlipcartResponse response  =  new FlipcartResponse();
		if(service.removeItem(id)) {
			response.setStatusCode(200);
			response.setMessage("success");
		}else {
			response.setStatusCode(400);
			response.setMessage("Failure , Could not add the data");
		}
		return response;
	}
}