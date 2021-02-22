package com.investment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.investment.exception.RecordNotFoundException;
import com.investment.service.InvestmentServiceImpl;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {InvestmentController.class, InvestmentServiceImpl.class})
@WebMvcTest
class InvestmentControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    InvestmentServiceImpl is;
	
	  @Test 
	  @DirtiesContext
	  void testGetMarketValueByInvestmentId() throws Exception {
	  is.initilizeValues();
	  this.mockMvc.perform(MockMvcRequestBuilders.get("/investment/marketValue/investor/50001"))
	  .andExpect(status().isOk())
	  .andReturn();
	  
	  }
	  
	  @Test 
	  @DirtiesContext
	  void testGetMarketValueByFundId() throws Exception {
	  is.initilizeValues();
	  this.mockMvc.perform(MockMvcRequestBuilders.get("/investment/marketValue/fund/20001"))
	  .andExpect(status().isOk())
	  .andReturn();
	  
	  }
	  
	  @Test 
	  @Disabled
	  @DirtiesContext
	  void testGetMarketValueByFundIdRecordNotFoud() throws Exception {
	  is.initilizeValues();
	  this.mockMvc.perform(MockMvcRequestBuilders.get("/investment/marketValue/fund/20001"))
	  .andExpect(status().isNotFound())
	  .andExpect((r)-> assertTrue(r.getResolvedException() instanceof RecordNotFoundException))
	  .andExpect(r-> assertEquals("Record is not vailable with provided Fund Id.", r.getResolvedException().getMessage()));
	  
	  }
	 
	  
	  @Test 
	  @DirtiesContext
	  void testGetAllFunds() throws Exception {
	  is.initilizeValues();
	  this.mockMvc.perform(MockMvcRequestBuilders.get("/investment/funds/getFunds"))
	  .andExpect(status().isOk())
	  .andReturn();
	  
	  }

}
