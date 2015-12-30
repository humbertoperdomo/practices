TEMPLATE = app
CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    filter.cpp \
    threadpool.cpp
LIBS += `pkg-config opencv --libs` -pthread

HEADERS += \
    threadpool.h \
    filter.h
