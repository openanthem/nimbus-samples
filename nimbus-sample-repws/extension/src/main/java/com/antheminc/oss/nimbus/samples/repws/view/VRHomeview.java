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
package com.antheminc.oss.nimbus.samples.repws.view;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Paragraph;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.samples.repws.core.BookCore;

import lombok.Getter;
import lombok.Setter;

@Domain(value = "homeview", includeListeners = { ListenerType.websocket })
@Repo(value = Repo.Database.rep_none, cache = Repo.Cache.rep_device)
@ViewRoot(layout = "")
@Getter
@Setter
public class VRHomeview {

	@Page(defaultPage = true)
	private VPMain vpMain;

	@Model
	@Getter
	@Setter
	public static class VPMain {

		@Tile
		private VTMain vtMain;
	}

	@Model
	@Getter
	@Setter
	public static class VTMain {

		@Section
		private VSMain vsMain;
	}

	@Model
	@Getter
	@Setter
	public static class VSMain {

		@Label("The grid below highlights data that is retrieved using the <b>rep_ws</b> feature. Data is retrieved from a configured controller (see BooksController) and then set into the grid via a <b>@Config</b> statement. See VRHomeview.java for the view configuration.")
		@Paragraph
		private String introduction;
		
		/**
		 * The @Config URL "<!#this!>/.m/_process?fn=_set&url=/p/book/_search?fn=example" is responsible for retrieving the data and setting it into the grid.
		 * It happens in two steps:
		 * 
		 *   1) The left hand side: "<!#this!>/.m/_process?fn=_set&url=..." performs a set operation on the 
		 *      Param represented by this field (the "books" @Grid) with the result of the Command execution 
		 *      given by "url".
		 *      
		 *   2) The right hand side: "/p/book/_search?fn=example" does a "Search by Example" on the BookCore 
		 *   domain entity. Because BookCore is decorated with @Repo(value = Database.rep_ws), the Nimbus 
		 *   framework will make an HTTP POST request to the following URL: 
		 *   
		 *     http://localhost:8080/nimbus-samples-repws/api/books/client/org/app/p/book/_search. 
		 *     
		 * Where did this URL come from? 
		 *   Any domain entity that is configured with @Repo(value = Database.rep_ws) is expected to have an 
		 *   application property available at ${nimbus.ext.repository.targetUrl.[DOMAIN_ALIAS]}, where 
		 *   [DOMAIN_ALIAS] should be replaced with the domain alias of the domain entity in question (in 
		 *   this case "book", because BookCore is decorated with @Domain(value = "book").
		 * 
		 * How is the retrieved data converted?
		 *   When the result retrieved from the right hand side is set into the param on the left hand side, 
		 *   the result will be mapped via JSON. For this scenario, the fields in Book and BookCore are 
		 *   identical fields, making the conversion seamless. For RESTful API services that return an object 
		 *   having a different contract, consider peforming any necessary conversions in a custom controller.
		 *   (see BooksController.java)  
		 */
		@Label("Books")
		@Grid(onLoad = true)
		@Path(linked = false)
		@Config(url = "<!#this!>/.m/_process?fn=_set&url=/p/book/_search?fn=example")
		private List<BookLineItem> books;
	}

	@MapsTo.Type(BookCore.class)
	@Getter
	@Setter
	public static class BookLineItem {

		@Label("ISBN")
		@GridColumn
		@Path
		private String isbn;

		@Label("Title")
		@GridColumn
		@Path
		private String title;

		@Label("Author")
		@GridColumn
		@Path
		private String author;

		@Label("Price")
		@GridColumn
		@Path
		private Double price;
	}
}
