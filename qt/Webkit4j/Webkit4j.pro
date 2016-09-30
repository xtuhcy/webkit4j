#-------------------------------------------------
#
# Project created by QtCreator 2016-09-28T22:27:15
#
#-------------------------------------------------

QT       += network webkitwidgets

QT       -= gui

TARGET = Webkit4j
TEMPLATE = lib

DEFINES += WEBKIT4J_LIBRARY

SOURCES += webkit4j.cpp \
    extwebpage.cpp

HEADERS += webkit4j.h\
        webkit4j_global.h \
    extwebpage.h

unix {
    target.path = /usr/lib
    INSTALLS += target
}
