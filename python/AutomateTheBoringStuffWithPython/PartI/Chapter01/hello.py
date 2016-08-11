#!/usr/bin/python3

# This program says hello and asks for my name.

print('Hello world!')
myName = input('What is your name? ')   # Ask for their name
print('It is good to meet you, ' + myName + '.')
print('The length of your name is: ' + str(len(myName)))

myAge = input('What is your age? ') # ask for their age
print('You will be ' + str(int(myAge) + 1) + ' in a year.')
