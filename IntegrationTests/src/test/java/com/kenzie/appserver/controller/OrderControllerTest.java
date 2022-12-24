package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    OrderService orderService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getOrders_returnsListOfOrders() throws Exception {

    }

    @Test
    public void getOrders_noOrders_notFoundStatus() throws Exception {

    }

    @Test
    public void createOrder_withValidContents_goodStatus() throws Exception {

    }

    @Test
    public void getOrderById_withValidId_returnsCorrectOrder() throws Exception {

    }

    @Test
    public void getOrderById_withInvalidId_notFoundStatus() throws Exception {

    }

    @Test
    public void deleteOrder_withValidId_deletesOrder() throws Exception {

    }

    @Test
    public void deleteOrder_withInvalidId_notFoundStatus() throws Exception {

    }

}
