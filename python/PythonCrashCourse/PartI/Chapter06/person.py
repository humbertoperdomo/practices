#!/usr/bin/python3

person = {
        'first_name': 'humberto',
        'last_name': 'perdomo',
        'age': 34,
        'city': 'guadalajara',
        }

print("My name is " +
        person['first_name'].title() +
        " " +
        person['last_name'].title() +
        ", I am " +
        str(person['age']) +
        " years old and I am from " +
        person['city'].title() +
        ".")
