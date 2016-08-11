#!/usr/bin/python3

def describe_city(city, country='mexico'):
    print(city.title() + " is in " + country.upper() + ".")

describe_city('guadalajara')
describe_city('chicago', 'usa')
describe_city('mazatlan')
describe_city(country='england', city='manchester')
