#!/usr/bin/python3

# The global statement
def spam():
    global eggs
    eggs = 'spam'

eggs = 'global'
spam()
print(eggs)
