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
package com.liferay.store.composite.purchase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.liferay.store.composite.customer.model.Customer;
import com.liferay.store.composite.customer.service.CustomerService;
import com.liferay.store.composite.product.model.Product;
import com.liferay.store.composite.product.service.ProductService;
import com.liferay.store.composite.purchase.model.Purchase;
import com.liferay.store.composite.purchase.model.PurchaseImpl;


/**
 * @author  Neil Griffin
 */
@ManagedBean(eager = true, name = "purchaseService")
@ApplicationScoped
public class PurchaseServiceImpl implements PurchaseService {

	@ManagedProperty(value = "#{customerService}")
	private CustomerService customerService;

	@ManagedProperty(value = "#{productService}")
	private ProductService productService;

	private Map<Long, List<Purchase>> purchases;

	@Override
	public List<Purchase> getPurchases(long customerId) {
		return purchases.get(customerId);
	}

	@PostConstruct
	public void postConstruct() {

		purchases = new HashMap<Long, List<Purchase>>();

		Random random = new Random();

		for (Customer customer : customerService.getCustomers()) {

			List<Purchase> customerPurchases = new ArrayList<Purchase>();
			List<Product> products = productService.getProducts();
			int startPurchaseIndex = random.nextInt(5);
			int finishPurchaseIndex = random.nextInt(7) + startPurchaseIndex + 1;

			long purchaseId = 1L;

			for (int i = startPurchaseIndex; i < finishPurchaseIndex; i++) {
				int quantity = random.nextInt(5) + 1;
				PurchaseImpl purchase = new PurchaseImpl(purchaseId++, products.get(i), quantity);
				customerPurchases.add(purchase);
			}

			purchases.put(customer.getCustomerId(), customerPurchases);
			System.err.println("!@#$ added purchase list for customerId=" + customer.getCustomerId() +
				" total purchases = " + customerPurchases.size());
		}
	}

	public void setCustomerService(CustomerService customerService) {

		// Injected via @ManagedProperty
		this.customerService = customerService;
	}

	public void setProductService(ProductService productService) {

		// Injected via @ManagedProperty
		this.productService = productService;
	}
}
