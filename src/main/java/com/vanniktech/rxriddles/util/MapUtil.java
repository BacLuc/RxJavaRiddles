package com.vanniktech.rxriddles.util;

import java.util.AbstractMap;

public class MapUtil {
	public static  <K, V> AbstractMap.SimpleEntry<K, V> entry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}
}
