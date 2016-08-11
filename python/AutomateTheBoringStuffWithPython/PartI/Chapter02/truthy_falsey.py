#!/usr/bin/python3

name = ''
while not name:
    name = input('Enter your name: ')
numOfGuests = int(input('How many guests will you have? '))
if numOfGuests:
   print('Be sure to have enough room for all your guests.') 
print('Done.')
