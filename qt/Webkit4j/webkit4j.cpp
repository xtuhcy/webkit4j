#include "webkit4j.h"

#include <QWebFrame>

Webkit4j::Webkit4j(Callback callback)
{
    int argc = 0;
    char *argv;
    this->app = new QApplication(argc, &argv);
    this->page = new ExtWebPage();
    this->callback = callback;
}

void Webkit4j::open(QString url) {
    page->mainFrame()->load(QUrl(url));
    QObject::connect(page, SIGNAL(loadFinished(bool)), this, SLOT(onFinish(bool)));
}


void Webkit4j::onFinish(bool success) {
    callback(success, page->mainFrame()->toHtml().toStdString().c_str());
    app->quit();
}

void Webkit4j::start() {
    app->exec();
}

void open(const char* ch, Callback callback) {
    Webkit4j *webkit4j = new Webkit4j(callback);
    webkit4j->open(ch);
    webkit4j->start();
}

