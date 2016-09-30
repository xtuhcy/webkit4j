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
	
	private static String[] qtlibs = {
			"icudt54",
			"icuin54",
			"icuuc54",
			"Qt5Core",
			"Qt5Gui",
			"Qt5Multimedia",
			"Qt5MultimediaWidgets",
			"Qt5Network",
			"Qt5OpenGL",
			"Qt5Positioning",
			"Qt5PrintSupport",
			"Qt5Qml",
			"Qt5Quick",
			"Qt5Sensors",
			"Qt5Sql",
			"Qt5WebChannel",
			"Qt5WebKit",
			"Qt5WebKitWidgets",
			"Qt5Widgets", 
			"Webkit4j"};
	
	static {
		ClassLoader classLoader = Webkit4j.class.getClassLoader();
		String classpath = classLoader.getResource("").getPath();
		String ospath = Platform.RESOURCE_PREFIX; 
		for(String name : qtlibs) {
			System.out.println("install lib : " + name);
			installLib(classLoader, classpath, ospath, name);
		}
	}
	
	/**
	 * 解压lib
	 */
	public static void installLib(ClassLoader classLoader, String classpath, String ospath, String name) {
		String libname = mapSharedLibraryName(name);
		String fullPath = classpath + ospath + "/" + libname;
		File file = new File(fullPath);
		if(!file.exists()) {
			extract(classLoader, classpath, ospath, libname);
		} else {
			System.out.println("install : " + fullPath);
		}
	}
	
	private static void extract(ClassLoader classLoader, String base, String ospath, String libname) {
		String libClassPath = ospath + "/" + libname;
		InputStream is = classLoader.getResourceAsStream(libClassPath);
		if(is == null) {
			System.out.println("is null");
			return ;
		}
		FileOutputStream fos = null;
        try {
        	File dir = new File(base + ospath);
        	if(!dir.exists()) {
        		dir.mkdirs();
        	}
        	File lib = new File(dir, libname);
            fos = new FileOutputStream(lib);
            int count;
            byte[] buf = new byte[1024];
            while ((count = is.read(buf, 0, buf.length)) > 0) {
                fos.write(buf, 0, count);
            }
            System.out.println("extract : " + lib.getAbsolutePath());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try { is.close(); } catch(IOException e) { }
            if (fos != null) {
                try { fos.close(); } catch(IOException e) { }
            }
        }
	}
	
	static String mapSharedLibraryName(String libName) {
        if (Platform.isMac()) {
            if (libName.startsWith("lib")
                && (libName.endsWith(".dylib")
                    || libName.endsWith(".jnilib"))) {
                return libName;
            }
            String name = System.mapLibraryName(libName);
            // On MacOSX, System.mapLibraryName() returns the .jnilib extension
            // (the suffix for JNI libraries); ordinarily shared libraries have
            // a .dylib suffix
            if (name.endsWith(".jnilib")) {
                return name.substring(0, name.lastIndexOf(".jnilib")) + ".dylib";
            }
            return name;
        }
        else if (Platform.isLinux() || Platform.isFreeBSD()) {
            if (isVersionedName(libName) || libName.endsWith(".so")) {
                // A specific version was requested - use as is for search
                return libName;
            }
        }
        else if (Platform.isAIX()) {	// can be libx.a, libx.a(shr.o), libx.so
            if (libName.startsWith("lib")) {
                return libName;
            }
        }
        else if (Platform.isWindows()) {
            if (libName.endsWith(".drv") || libName.endsWith(".dll")) {
                return libName;
            }
        }

        return System.mapLibraryName(libName);
    }
	
	static boolean isVersionedName(String name) {
        if (name.startsWith("lib")) {
            int so = name.lastIndexOf(".so.");
            if (so != -1 && so + 4 < name.length()) {
                for (int i=so+4;i < name.length();i++) {
                    char ch = name.charAt(i);
                    if (!Character.isDigit(ch) && ch != '.') {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
	
	public static void open(String url, WebkitCallback callback) {
		QtWebkit qtWebkit = QtWebkit.INSTANCE;
		qtWebkit.open(url, callback);
	}
}
