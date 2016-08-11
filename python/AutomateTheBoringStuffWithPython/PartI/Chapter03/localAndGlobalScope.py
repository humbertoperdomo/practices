#!/usr/bin/python3

def spam():
    eggs = 31337
    bacon()
    print(eggs)
# Global variables can be read from a local scope
    print(globalEggs)

def bacon():
    ham = 101
# Local scopes cannot use variables in other local scopes
    eggs = 0

globalEggs = 42
spam()
print(globalEggs)
# Local variables cannot be used in the global scope
#print(eggs)

