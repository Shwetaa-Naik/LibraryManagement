package org.shweta.LibraryManagement.controller;


import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.shweta.LibraryManagement.controllers.BookController;
import org.shweta.LibraryManagement.modals.Book;
import org.shweta.LibraryManagement.services.BookService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=BookController.class)
public class TestBookController {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;


    @Before
    public void setUp(){
        /*this will create dummy request like dispatcher servlet does*/
        mockMvc= MockMvcBuilders.standaloneSetup(bookController).build();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testAddBook() throws Exception {
        when(bookService.addBook(any())).thenReturn(new Book());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("bookNumber","book101");
        jsonObject.put("bookCost","200");

        RequestBuilder requestBuilder = post("/book/create").contentType(MediaType.APPLICATION_JSON).content(String.valueOf(jsonObject));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
