#!/usr/bin/python3

favorite_languages = {
        'jen': 'python',
        'sarah': 'c',
        'edward': 'ruby',
        'phil': 'python',
        'humberto': 'java',
        }

people = favorite_languages.keys()
people.append('emily')
people.append('evangeline')
people.append('elliot')

for person in people:
    if person in favorite_languages:
        print(person + " thank you for your time")
    else:
        print(person + " please, be part of our poll")
