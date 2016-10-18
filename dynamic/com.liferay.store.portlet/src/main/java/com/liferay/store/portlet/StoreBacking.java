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
package com.liferay.store.portlet;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import com.liferay.store.customer.model.Customer;
import com.liferay.store.customer.service.CustomerService;
import com.liferay.store.purchase.model.Purchase;
import com.liferay.store.purchase.service.PurchaseService;


/**
 * @author  Neil Griffin
 */
@ManagedBean
@RequestScoped
public class StoreBacking {

	private long selectedCustomerId;
	private ServiceTracker customerServiceTracker;
	private List<Customer> customers;
	private List<Purchase> purchases;
	private ServiceTracker purchaseServiceTracker;

	public List<Customer> getCustomers() {

		if (customers == null) {

			if (!customerServiceTracker.isEmpty()) {
				customers = ((CustomerService) customerServiceTracker.getService()).getCustomers();
			}
		}

		return customers;
	}

	public List<Purchase> getPurchases() {

		if (purchases == null) {

			if (!purchaseServiceTracker.isEmpty()) {
				PurchaseService purchaseService = (PurchaseService) purchaseServiceTracker.getService();
				this.purchases = purchaseService.getPurchases(selectedCustomerId);
			}
		}

		return purchases;
	}

	public long getSelectedCustomerId() {
		return selectedCustomerId;
	}

	@PostConstruct
	public void postConstruct() {

		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		BundleContext bundleContext = bundle.getBundleContext();
		customerServiceTracker = new ServiceTracker(bundleContext, CustomerService.class, null);
		customerServiceTracker.open();
		purchaseServiceTracker = new ServiceTracker(bundleContext, PurchaseService.class, null);
		purchaseServiceTracker.open();
	}

	@PreDestroy
	public void preDestroy() {
		customerServiceTracker.close();
		purchaseServiceTracker.close();
	}

	public void setSelectedCustomerId(long selectedCustomerId) {
		this.selectedCustomerId = selectedCustomerId;
	}
}
