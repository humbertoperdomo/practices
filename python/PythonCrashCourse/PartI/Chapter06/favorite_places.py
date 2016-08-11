#!/usr/bin/python3

favorite_places = {
        'emily': ['beach', 'movie theater', 'park'],
        'humberto': ['house'],
        'armida': ['beach', 'mall', 'movie theater'],
        }

for name, places in favorite_places.items():
    print("\n" + name.title() + "'s favorite places are: ")
    for place in places:
        print("\t" + place.title())
