#!/usr/bin/python3

# Exception handling
def spam(divideBy):
        return 42 / divideBy

try:
    print(spam(2))
    print(spam(12.0))
    print(spam(0))
    print(spam(1))
except ZeroDivisionError:
    print('Error: Invalid argument.')
