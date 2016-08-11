#!/usr/bin/python3

numbers = list(range(1, 10))

for number in numbers:
    if number == 1:
        print(str(number) + "st")
    elif number == 2:
        print(str(number) + "nd")
    elif number == 3:
        print(str(number) + "rd")
    elif number > 3:
            print(str(number) + "th")
