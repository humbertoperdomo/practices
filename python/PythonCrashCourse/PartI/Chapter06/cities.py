#!/usr/bin/python3

cities = {
        'guadalajara': {
            'country': 'mexico',
            'population': 12345,
            'fact': 'La Perla de Occidente',
            },
        'chicago': {
            'country': 'usa',
            'population': 123464,
            'fact': 'Home of Al Capone',
            },
        'santiago': {
            'country': 'chile',
            'population': 342343,
            'fact': 'Casa de la Moneda',
            }
        }

for city, information in cities.items():
    print("\n" + city.title())
    for key, info in information.items():
        if key == 'population':
            info = str(info)
        print("\t" + key.title() + ": " + info + ".")
