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

RESTAURANT = Restaurant("alessandro's", 'italian')
print("Restaurant name: " + RESTAURANT.restaurant_name.title() + ".")
print("Cuisine type: " + RESTAURANT.cuisine_type + ".")
RESTAURANT.describe_restaurat()
RESTAURANT.open_restaurant()
print("Customer the restaurant has served: " + str(RESTAURANT.number_served))
RESTAURANT.set_number_served(7)
print("Customer the restaurant has served: " + str(RESTAURANT.number_served))
RESTAURANT.increment_number_served(5)
print("Customer the restaurant has served: " + str(RESTAURANT.number_served))
