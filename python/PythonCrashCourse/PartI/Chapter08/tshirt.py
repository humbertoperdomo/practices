#!/usr/bin/python3

def make_shirt(text='I love Python', size='large'):
    print("The specifications of the t-shirt are: ")
    print("Size: " + size)
    print("Text: " + text)

make_shirt()
make_shirt(size='medium')
make_shirt(size='small', text='Python expert in process')
