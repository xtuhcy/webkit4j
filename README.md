# webkit4j
qtwebkit-based headless browser for java

#Demo
```
    Webkit4j.open("url", new WebkitCallback() {
			
			public void invoke(boolean success, String content) {
				if(success) {
					System.out.println(content);
				} else {
					System.out.println(success);
				}
			}
		});
```
