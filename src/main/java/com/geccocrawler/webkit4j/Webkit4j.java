package com.geccocrawler.webkit4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.geccocrawler.webkit4j.QtWebkit.WebkitCallback;
import com.sun.jna.Platform;

/**
 * 先查询classpath下是否有dll
 * 如果没有查询classpath下的jar是否有dll
 * File embedded = Native.extractFromResourcePath(libraryName, (ClassLoader)options.get(Library.OPTION_CLASSLOADER));
 * 
 * String libname = name.startsWith("/") ? name : NativeLibrary.mapSharedLibraryName(name);
   String resourcePath = name.startsWith("/") ? name : Platform.RESOURCE_PREFIX + "/" + libname;
 * 
 * @author huchengyi
 *
 */

public class Webkit4j {
	
	static {
		InstallLibs.install();
	}
	
	public static void open(String url, WebkitCallback callback) {
		QtWebkit qtWebkit = QtWebkit.INSTANCE;
		qtWebkit.open(url, callback);
	}
}
