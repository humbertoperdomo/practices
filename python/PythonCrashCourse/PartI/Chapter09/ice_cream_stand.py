#!e/urs/bn/python3
# restaurant.py
"""Class Restaurant"""
class Restaurant():
    """Class that represent a restaurant."""

    def __init__(self, restaurant_name, cuisine_type):
        """Initialize restaurant_name and cuisine_type attributes."""
        self.restaurant_name = restaurant_name
        self.cuisine_type = cuisine_type
        self.number_served = 0

    def describe_restaurat(self):
        """Describes restaurant using both attributes."""
        print(self.restaurant_name.title() + " is a restaurant of " +
              self.cuisine_type + " food.")

    def open_restaurant(self):
        """Simulates that restaurant is open."""
        print(self.restaurant_name.title() + " is open.")

    def set_number_served(self, number_served):
        """Set the number of customers served."""
        self.number_served = number_served

    def increment_number_served(self, served):
        """Increment the customers served using served amount."""
        self.number_served += served


class IceCreamStand(Restaurant):
    """Class that represents an ice cream stand."""

    def __init__(self, restaurant_name, cuisine_type):
        """Initialize Restaurant attributes."""
        super().__init__(restaurant_name, cuisine_type)
        self._flavors = ['chocolate chips', 'chocolate', 'vanilla', 'strawnerry']

    def display_flavors(self):
        """Shows flavors table."""
        print("")
        print('Flavors'.center(16))
        for flavor in self._flavors:
            print(flavor.title().ljust(16))

DQ = IceCreamStand("dairy queen", 'soft serve ice cream')
DQ.describe_restaurat()
DQ.open_restaurant()
DQ.display_flavors()
