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
package com.liferay.store.composite.product.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.liferay.store.composite.product.model.Product;
import com.liferay.store.composite.product.model.ProductImpl;


/**
 * @author  Neil Griffin
 */
@ManagedBean(eager = true, name = "productService")
@ApplicationScoped
public class ProductServiceImpl implements ProductService {

	private List<Product> products;

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@PostConstruct
	public void postConstruct() {

		products = new ArrayList<Product>();
		products.add(new ProductImpl(1, "Keyboard", new BigDecimal("24.99")));
		products.add(new ProductImpl(2, "Mouse", new BigDecimal("15.99")));
		products.add(new ProductImpl(3, "Monitor", new BigDecimal("399.25")));
		products.add(new ProductImpl(4, "Webcam", new BigDecimal("24.78")));
		products.add(new ProductImpl(5, "USB Stick", new BigDecimal("7.99")));
		products.add(new ProductImpl(6, "Wireless Router", new BigDecimal("57.69")));
		products.add(new ProductImpl(7, "Smart Phone", new BigDecimal("399.95")));
		products.add(new ProductImpl(8, "Bluetooth Speaker", new BigDecimal("79.99")));
		products.add(new ProductImpl(9, "Tablet", new BigDecimal("153.45")));
		products.add(new ProductImpl(10, "Smart Watch", new BigDecimal("199.99")));
	}
}
