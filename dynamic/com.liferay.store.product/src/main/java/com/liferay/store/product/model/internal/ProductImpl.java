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
package com.liferay.store.product.model.internal;

import java.io.Serializable;
import java.math.BigDecimal;

import com.liferay.store.product.model.Product;


/**
 * @author  Neil Griffin
 */
public class ProductImpl implements Product, Serializable {

	private static final long serialVersionUID = 7636966332990094526L;

	private long productId;
	private String description;
	private BigDecimal price;

	public ProductImpl(long productId, String description, BigDecimal price) {
		this.productId = productId;
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getProductId() {
		return productId;
	}
}
