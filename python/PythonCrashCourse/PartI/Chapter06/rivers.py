#!/usr/bin/python3

rivers = {
        'nile': "egypt",
        'amazon': "brazil",
        'lerma': "mexico",
        }

for river, country in rivers.items():
    print("The " + river.title() +
            " runs through " + country.title() +
            ".")

print("\nRivers:")
for river in rivers.keys():
    print(river.title())
print("\nCountries:")
for country in rivers.values():
    print(country.title())
