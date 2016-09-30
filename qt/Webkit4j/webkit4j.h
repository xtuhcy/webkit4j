#ifndef WEBKIT4J_H
#define WEBKIT4J_H

#include "webkit4j_global.h"

#include <QApplication>
#include "extwebpage.h"

typedef void (*Callback)(bool , const char* );

class WEBKIT4JSHARED_EXPORT Webkit4j : public QObject
{
Q_OBJECT

public:
    Webkit4j(Callback callback);
    void start();
    void open(QString url);

public Q_SLOTS:
    void onFinish(bool success);

private :
    QApplication *app;
    ExtWebPage *page;
    Callback callback;
};


extern "C" {

    WEBKIT4JSHARED_EXPORT void open(const char* ch, Callback callback);

}

#endif // WEBKIT4J_H
