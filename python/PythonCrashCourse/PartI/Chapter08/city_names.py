#!/usr/bin/python3

def city_country(city, country):
    return city.title() + ', ' + country.title()

cities = {'guadalajara': 'mexico', 'los angeles': 'united states', 'hortolandia': 'brazil'}

for city, country in cities.items():
    print(city_country(city, country))
