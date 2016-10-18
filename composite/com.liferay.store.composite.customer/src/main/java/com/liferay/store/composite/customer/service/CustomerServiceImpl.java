/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.store.composite.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.liferay.store.composite.customer.model.Customer;
import com.liferay.store.composite.customer.model.CustomerImpl;


/**
 * @author  Neil Griffin
 */
@ManagedBean(eager = true, name = "customerService")
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	@PostConstruct
	public void postConstruct() {

		customers = new ArrayList<Customer>();
		customers.add(new CustomerImpl(1L, "Neil", "Griffin"));
		customers.add(new CustomerImpl(2L, "Michael", "Han"));
	}
}
