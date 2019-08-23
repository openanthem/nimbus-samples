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
package com.antheminc.oss.nimbus.samples.web.view;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.antheminc.oss.nimbus.app.extension.config.BPMEngineConfig;
import com.antheminc.oss.nimbus.channel.web.WebActionController;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecution.MultiOutput;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecution.Output;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.samples.web.test.TestApplication;
import com.antheminc.oss.nimbus.support.Holder;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;
import com.antheminc.oss.nimbus.test.domain.support.utils.ParamUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
@Import(BPMEngineConfig.class)
public class HomeviewTest {

	public static final String PREFIX = "/client/org/app/p/";
	public static final String VIEW_DOMAIN_ALIAS = "homeview";
	public static final String VIEW_DOMAIN_ROOT = PREFIX + VIEW_DOMAIN_ALIAS;

	@Autowired
	private WebActionController controller;

	@Test
	public void testHello() {
		MultiOutput response = invokeGet(VIEW_DOMAIN_ROOT + "/vpMain/vtMain/vsMain/hello");
		Assert.assertNotNull(response);
		
		Output<Param<String>> helloParamOutput = response.findFirstParamOutputEndingWithPath("/vpMain/vtMain/vsMain/hello");
		Param<String> helloParam = helloParamOutput.getValue();
		Assert.assertEquals("Hello World!", ParamUtils.getLabelText(helloParam));
	}
	
	// TODO move to Test utility
	@SuppressWarnings("unchecked")
	public MultiOutput invokeGet(String paramPath) {
		Object response = controller.handleGet(MockHttpRequestBuilder.withUri(paramPath).addAction(Action._get).getMock(), null);
		return ((Holder<MultiOutput>) response).getState();
	}
}
