#!/usr/bin/python3

number = input("Enter a number: ")
number = int(number)

if number % 10 == 0:
    print("\n" + str(number) + " is multiple of 10.")
else:
    print("\n" + str(number) + " is not multiple of 10.")
