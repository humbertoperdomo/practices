#!/usr/bin/python3

keep_running = True
while keep_running:
    age = input("How old are you? ")
    if age == 'quit':
        break

    age = int(age)
    if age < 3:
        print("You don't need to buy a ticket.")
    elif age >= 3 and age <= 12:
        print("The price of your ticket is $10")
    else:
        print("The price of your ticket is $15")
