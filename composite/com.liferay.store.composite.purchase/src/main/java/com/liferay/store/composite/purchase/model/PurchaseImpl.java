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
package com.liferay.store.composite.purchase.model;

import java.io.Serializable;

import com.liferay.store.composite.product.model.Product;
import com.liferay.store.composite.purchase.model.Purchase;


/**
 * @author  Neil Griffin
 */
public class PurchaseImpl implements Purchase, Serializable {

	private static final long serialVersionUID = 60033742721362352L;

	private long purchaseId;
	private Product product;
	private int quantity;

	public PurchaseImpl(long purchaseId, Product product, int quantity) {
		this.purchaseId = purchaseId;
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public int getQuantity() {
		return quantity;
	}
}
