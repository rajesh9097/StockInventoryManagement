package com.stockInventorymanagement.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stockInventorymanagement.app.controller.StockInventoryController;
import com.stockInventorymanagement.app.entity.StockDetails;
import com.stockInventorymanagement.app.service.StockInventoryService;



@RunWith(SpringRunner.class)
@WebMvcTest(value = StockInventoryController.class)
@WithMockUser
public class StockInventoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockInventoryService customerService;

	List<StockDetails> mockStockList = null;
	@Before
	public void setUp() {
		mockStockList= new ArrayList<StockDetails>();
		StockDetails stock = new StockDetails(100, "Shirts", 500, parseDate("1990-04-17"), 100);
		mockStockList.add(stock);
	}
	
	
	String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	
	@Test
	public void testDisplayStock() throws Exception {
		Mockito.doReturn(mockStockList).when(customerService).displaystock();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/displaystock")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		System.out.println("----" + actual);
		String expected = "[{\"stockNumber\":100,\"stockName\":\"Shirts\",\"purchasingPrice\":500.0,\"purchaseDate\":\"16-04-1990\",\"quantity\":100}]";
		Assert.assertEquals(expected, actual);
	}

}
