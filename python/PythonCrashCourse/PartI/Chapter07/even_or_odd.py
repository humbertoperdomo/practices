#!/usr/bin/python3

# The modulo operator
number = input("Enter a number, and I'll tell you if it's even or odd: ")
# If you're using Pyhton 2.7, use raw_input() instead of input()
#number = raw_input("Enter a number, and I'll tell you if it's even or odd: ")

number = int(number)

if number % 2 == 0:
    print("\nThe number " + str(number) + " is even.")
else:
    print("\nThe number " + str(number) + " is odd.")
