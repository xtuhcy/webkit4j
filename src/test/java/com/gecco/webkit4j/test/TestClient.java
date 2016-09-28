package com.gecco.webkit4j.test;

import webkit4j.QtWebkit.WebkitCallback;
import webkit4j.Webkit4j;

public class TestClient {
	public static void main(String[] args) {
		Webkit4j.open("http://www.163.com", new WebkitCallback() {
			
			public void invoke(boolean success, String content) {
				if(success) {
					System.out.println(content);
				}
			}
		});
	}
}
