#!/usr/bin/python3

# Using int() to accept numerical input
height = input("How tall are you, in inches? ")
# If you're using Pyhton 2.7, use raw_input() instead of input()
#height = raw_input("How tall are you, in inches? ")
height = int(height)

if height >= 36:
    print("\nYou're tall enough to ride!")
else:
    print("\nYou'll be able to ride when you're a little older.")


