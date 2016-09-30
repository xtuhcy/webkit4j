package com.gecco.webkit4j.test;

import com.geccocrawler.webkit4j.Webkit4j;
import com.geccocrawler.webkit4j.QtWebkit.WebkitCallback;

public class TestClient {
	public static void main(String[] args) {
		Webkit4j.open("https://www.baidu.com", new WebkitCallback() {
			
			public void invoke(boolean success, String content) {
				System.out.println(success);
				System.out.println(content);
			}
		});
	}
}
