#!/usr/bin/python3
"""Addition."""

try:
    first_number = int(input("Enter the first number: "))
    second_number = int(input("enter the second number: "))
    result = first_number + second_number
except ValueError:
    print("You have to enter two numbers!")
else:
    print("The result is: " + str(result))
