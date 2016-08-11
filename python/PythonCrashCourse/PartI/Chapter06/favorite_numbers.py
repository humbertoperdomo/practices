#!/usr/bin/python3

favorite_numbers = {
        'emily': [2, 13],
        'evangeline': [3, 17, 19],
        'elliot': [5, 23, 29],
        'armida': [7],
        'humberto': [11, 31],
        }

for name, numbers in favorite_numbers.items():
    print("\n" + name.title() + "'s favorite numbers are: ")
    for number in numbers:
        print("\t" + str(number))
