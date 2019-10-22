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
package com.antheminc.oss.nimbus.samples.repws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.antheminc.oss.nimbus.samples.repws.model.Book;

/**
 * @author Tony Lopez
 *
 */
@Service
public class BookService {

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		Book book = new Book("978-1-891830-75-4", "110 Per¢", "Tony Consiglio", 12.95);
		books.add(book);
		book = new Book("978-1-60309-050-6", "The 120 Days of Simon", null, 14.95);
		books.add(book);
		book = new Book("978-1-891830-71-6", "AEIOU or Any Easy Intimacy", "Jeffrey Brown", 12.00);
		books.add(book);
		book = new Book("978-1-60309-025-4", "Alec: The Years Have Pants", null, 35.00);
		books.add(book);
		book = new Book("978-1-60309-047-6", "Alec: The Years Have Pants -- HARDCOVER", null, 49.95);
		books.add(book);
		book = new Book("978-1-60309-322-4", "Alone Forever", null, 9.95);
		books.add(book);
		book = new Book("978-1-60309-2395", "American Elf", "James Kochalka", null);
		books.add(book);
		book = new Book("978-1-891830-85-3", "American Elf (Book 2)", null, 19.95);
		books.add(book);
		book = new Book("978-1-60309-016-2", "American Elf (Book 3)", null, 19.95);
		books.add(book);
		book = new Book("978-1-60309-265-4", "American Elf (Book 4)", null, 24.95);
		books.add(book);
		return books;
	}

}
