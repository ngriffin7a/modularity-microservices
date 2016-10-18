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
package com.liferay.store.composite.portlet;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.liferay.store.composite.customer.model.Customer;
import com.liferay.store.composite.customer.service.CustomerService;
import com.liferay.store.composite.purchase.model.Purchase;
import com.liferay.store.composite.purchase.service.PurchaseService;


/**
 * @author  Neil Griffin
 */
@ManagedBean
@RequestScoped
public class StoreBacking {

	private long selectedCustomerId;
	private List<Customer> customers;
	private List<Purchase> purchases;

	@ManagedProperty(value = "#{customerService}")
	private CustomerService customerService;

	@ManagedProperty(value = "#{purchaseService}")
	private PurchaseService purchaseService;

	public List<Customer> getCustomers() {

		if (customers == null) {
			customers = customerService.getCustomers();
		}

		return customers;
	}

	public List<Purchase> getPurchases() {

		if (purchases == null) {
			purchases = purchaseService.getPurchases(selectedCustomerId);
		}

		return purchases;
	}

	public long getSelectedCustomerId() {
		return selectedCustomerId;
	}

	public void setCustomerService(CustomerService customerService) {

		// Injected via @ManagedProperty
		this.customerService = customerService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {

		// Injected via @ManagedProperty
		this.purchaseService = purchaseService;
	}

	public void setSelectedCustomerId(long selectedCustomerId) {
		this.selectedCustomerId = selectedCustomerId;
	}
}
