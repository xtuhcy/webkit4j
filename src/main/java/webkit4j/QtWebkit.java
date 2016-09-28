package webkit4j;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface QtWebkit extends StdCallLibrary {
	
	public interface WebkitCallback extends Callback {
		void invoke(boolean success, String content);
	}
	
	public abstract void open(String url, WebkitCallback callback);
	
	QtWebkit INSTANCE = (QtWebkit) Native.loadLibrary("Webkit4j", QtWebkit.class);

}
