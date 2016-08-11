#!/usr/bin/python3

active = True
while active:
    topping = input("Enter a topping: ")
    
    if topping != 'quit':
        print("You'll add " + topping + " to your pizza.")
    else:
        active = False
