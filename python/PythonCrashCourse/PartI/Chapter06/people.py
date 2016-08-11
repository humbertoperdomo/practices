#!/usr/bin/python3

people = [
        {
            'first_name': 'humberto',
            'last_name': 'perdomo',
            'age': 34,
            'location': 'tlaquepaque',
            },
        {
            'first_name': 'jesus',
            'last_name': 'nuno',
            'age': 33,
            'location': 'guadalajara',
            },
        {
            'first_name': 'juan',
            'last_name': 'perez',
            'age': 41,
            'location': 'zapopan',
            },
        ]

for person in people:
    full_name = person['first_name'].title() + " " + person['last_name'].title()
    print("\n" + full_name + " is " + str(person['age']) + " years old and lives in " + person['location'].title() + ".")
