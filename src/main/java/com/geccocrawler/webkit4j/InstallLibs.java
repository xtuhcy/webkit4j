package com.geccocrawler.webkit4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.jna.Platform;

public class InstallLibs {
	
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
	
	private static String[] ssllibs = {
			"libssl32",
			"libeay32"
	};
	
	public static void install() {
		ClassLoader classLoader = Webkit4j.class.getClassLoader();
		String classpath = classLoader.getResource("").getPath();
		String ospath = Platform.RESOURCE_PREFIX; 
		for(String name : qtlibs) {
			installQtLib(classLoader, classpath, ospath, name);
		}
		for(String name : ssllibs) {
			installSslLib(classLoader, ospath, name);
		}
	}
	
	/**
	 * 安装ssllib到c:\windown\system32下
	 */
	static void installSslLib(ClassLoader classLoader, String ospath, String name) {
		String libname = mapSharedLibraryName(name);//lib全名.dll .so
		if(Platform.isWindows()) {
			String fullPath = "C:\\Windows\\System32\\" + libname;
			File dest = new File(fullPath);
			if(!dest.exists()) {
				File dir = dest.getParentFile();
		    	if(!dir.exists()) {
		    		dir.mkdirs();
		    	}
	        	String libClassPath = ospath + "/" + libname;
				extract(classLoader, libClassPath, dest);
			} else {
				System.out.println("have install : " + fullPath);
			}
		}
	}
	
	/**
	 * 安装qtlib到classpath下
	 */
	static void installQtLib(ClassLoader classLoader, String classpath, String ospath, String name) {
		String libname = mapSharedLibraryName(name);//lib全名.dll .so
		String fullPath = classpath + ospath + "/" + libname;
		File dest = new File(fullPath);
		if(!dest.exists()) {
			File dir = dest.getParentFile();
	    	if(!dir.exists()) {
	    		dir.mkdirs();
	    	}
        	String libClassPath = ospath + "/" + libname;
			extract(classLoader, libClassPath, dest);
		} else {
			System.out.println("have install : " + fullPath);
		}
	}
	
	
	static void extract(ClassLoader classLoader, String libClassPath, File dest) {
		InputStream is = classLoader.getResourceAsStream(libClassPath);
		if(is == null) {
			System.out.println("is null");
			return ;
		}
		FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(dest);
            int count;
            byte[] buf = new byte[1024];
            while ((count = is.read(buf, 0, buf.length)) > 0) {
                fos.write(buf, 0, count);
            }
            System.out.println("extract : " + dest.getAbsolutePath());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
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

}
