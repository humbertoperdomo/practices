#!/usr/bin/python3
"""Test cities."""
import unittest
from city_functions import get_city_country_formatted

class TestCityFunctions(unittest.TestCase):
    """Test for 'city_functions.py'."""

    def test_city_country(self):
        """Do format like 'Santiago, Chile'?"""
        city_country_formatted = get_city_country_formatted('santiago', 'chile')
        self.assertEqual(city_country_formatted, 'Santiago, Chile')

    def test_city_country_population(self):
        """Do format like 'Santiago, Chile - population=5000000'?"""
        city_country_population_formatted = get_city_country_formatted('santiago', 'chile', 5000000)
        self.assertEqual(city_country_population_formatted, 'Santiago, Chile - population=5000000')


unittest.main()
