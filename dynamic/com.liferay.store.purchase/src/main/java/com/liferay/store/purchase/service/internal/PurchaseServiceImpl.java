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
package com.liferay.store.purchase.service.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import com.liferay.store.customer.model.Customer;
import com.liferay.store.customer.service.CustomerService;
import com.liferay.store.product.model.Product;
import com.liferay.store.product.service.ProductService;
import com.liferay.store.purchase.model.Purchase;
import com.liferay.store.purchase.model.internal.PurchaseImpl;
import com.liferay.store.purchase.service.PurchaseService;


/**
 * @author  Neil Griffin
 */
@Component(
	immediate = true, service = PurchaseService.class, property = {
			"service.exported.interfaces=*", "service.exported.configs=cxf"
		}
)
public class PurchaseServiceImpl implements PurchaseService {

	@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
	private volatile CustomerService customerService;

	@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
	private volatile ProductService productService;

	private Map<Long, List<Purchase>> purchases;

	@Activate
	public void activate() {

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

	public List<Purchase> getPurchases(long customerId) {
		return purchases.get(customerId);
	}
}
