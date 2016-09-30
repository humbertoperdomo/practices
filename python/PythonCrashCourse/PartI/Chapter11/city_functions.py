#!/usr/bin/python3
"""City functions."""

def get_city_country_formatted(city, country, population=0):
    """Generate a neatly formatted city, country string."""
    city_country_formatted = city.title() + ', ' + country.title()

    if population > 0:
        city_country_formatted = city_country_formatted.title() + ' - population=' + str(population)

    return city_country_formatted
