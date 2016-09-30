package com.geccocrawler.webkit4j;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface QtWebkit extends StdCallLibrary {
	
	/*Libeay32 libeay32 = Libeay32.INSTANCE;
	
	Libssl32 libssl32 = Libssl32.INSTANCE;
	
	Qt5Core qt5Core = Qt5Core.INSTANCE;
	
	Qt5Gui qt5Gui = Qt5Gui.INSTANCE;
	
	Qt5Widgets qt5Widgets = Qt5Widgets.INSTANCE;
	
	Qt5OpenGL qt5OpenGL = Qt5OpenGL.INSTANCE;
	
	Qt5PrintSupport qt5PrintSupport = Qt5PrintSupport.INSTANCE;
	
	Qt5Network qt5Network = Qt5Network.INSTANCE;
	
	Qt5Multimedia qt5Multimedia = Qt5Multimedia.INSTANCE;
	
	Qt5MultimediaWidgets qt5MultimediaWidgets = Qt5MultimediaWidgets.INSTANCE;
	
	Qt5Sensors qt5Sensors = Qt5Sensors.INSTANCE;
	
	Icudt54 icudt54 = Icudt54.INSTANCE;
	
	Icuuc54 icuuc54 = Icuuc54.INSTANCE;
	
	Icuin54 icuin54 = Icuin54.INSTANCE;
	
	Qt5Sql qt5Sql = Qt5Sql.INSTANCE;
	
	Qt5Qml qt5Qml = Qt5Qml.INSTANCE;
	
	Qt5WebChannel qt5WebChannel = Qt5WebChannel.INSTANCE;
	
	Qt5Quick qt5Quick = Qt5Quick.INSTANCE;
	
	Qt5Positioning qt5Positioning = Qt5Positioning.INSTANCE;
	
	Qt5WebKit qt5WebKit = Qt5WebKit.INSTANCE;
	
	Qt5WebKitWidgets qt5WebKitWidgets = Qt5WebKitWidgets.INSTANCE;*/
	
	QtWebkit INSTANCE = (QtWebkit) Native.loadLibrary("Webkit4j", QtWebkit.class);
	
	public interface WebkitCallback extends Callback {
		void invoke(boolean success, String content);
	}
	
	public abstract void open(String url, WebkitCallback callback);
	
}
