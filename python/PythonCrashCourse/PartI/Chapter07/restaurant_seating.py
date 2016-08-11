#!/usr/bin/python3

MAX = 8

people_number = input("How many people are in your dinner group? ")
people_number = int(people_number)

if people_number > MAX:
    print("You have to wait for a table.")
else:
    print("Your table is ready.")
