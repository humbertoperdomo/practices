#!/usr/bin/python3

pets = [
        {
            'name': 'chiquita',
            'kind': 'dog',
            'owner': 'armida',
            },
        {
            'name': 'samantha',
            'kind': 'dog',
            'owner': 'mingo',
            },
        {
            'name': 'qwerty',
            'kind': 'fish',
            'owner': 'elliot',
            },
        ]

for pet in pets:
    print("\n" + pet['name'].title() + " is a " + pet['kind'] + 
            " and belongs to " + pet['owner'].title() + ".")
