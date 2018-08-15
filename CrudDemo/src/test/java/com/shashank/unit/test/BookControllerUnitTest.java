package com.shashank.unit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.shashank.controller.BookApi;
import com.shashank.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookApi.class, secure = false)
public class BookControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	
	@Test
	public void retrieveDetailsForBook() throws Exception {

		/*Mockito.when(
				bookService.getSingleBook(Mockito.anyLong())).thenReturn(mockCourse);*/

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/book/2").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{bookId:2,bookName:Head first,deleted:false}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
