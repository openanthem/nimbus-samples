/**
 *  Copyright 2016-2019 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.antheminc.oss.nimbus.samples.repws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antheminc.oss.nimbus.samples.repws.core.BookCore;
import com.antheminc.oss.nimbus.samples.repws.model.Book;
import com.antheminc.oss.nimbus.samples.repws.service.BookService;

/**
 * @author Tony Lopez
 *
 */
@RestController
public class BooksController {

	@Autowired
	private BookService bookService;
	
	@PostMapping(value = "/api/books/**/book/_search")
	public List<Book> searchBooks(BookCore searchCriteria) {
		// Use searchCriteria to build any RESTful search parameters (OPTIONAL)
		System.out.println(searchCriteria);
		
		// Retrieve data (e.g. RESTful webservice, database call, etc.)
		List<Book> response = this.bookService.getAllBooks();
		
		// Perform any conversions (OPTIONAL)
		return response;
	}	
}
