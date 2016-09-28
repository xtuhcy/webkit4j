package webkit4j;

import webkit4j.QtWebkit.WebkitCallback;

public class Webkit4j {
	
	public static void open(String url, WebkitCallback callback) {
		QtWebkit.INSTANCE.open(url, callback);
	}

}
