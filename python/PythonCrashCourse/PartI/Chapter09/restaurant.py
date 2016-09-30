#!e/urs/bn/python3
# restaurant.py
"""Class Restaurant"""
class Restaurant():
    """Class that represent a restaurant."""

    def __init__(self, restaurant_name, cuisine_type):
        """Initialize restaurant_name and cuisine_type attributes."""
        self.restaurant_name = restaurant_name
        self.cuisine_type = cuisine_type

    def describe_restaurat(self):
        """Describes restaurant using both attributes."""
        print(self.restaurant_name.title() + " is a restaurant of " +
              self.cuisine_type + " food.")

    def open_restaurant(self):
        """Simulates that restaurant is open."""
        print(self.restaurant_name.title() + " is open.")

#RESTAURANT = Restaurant("alessandro's", 'italian')
#print("Restaurant name: " + RESTAURANT.restaurant_name.title() + ".")
#print("Cuisine type: " + RESTAURANT.cuisine_type + ".")
#RESTAURANT.describe_restaurat()
#RESTAURANT.open_restaurant()
#RESTAURANT_2 = Restaurant('sanborns', 'international')
#RESTAURANT_3 = Restaurant('el santo coyote', 'mexican')
#RESTAURANT_2.describe_restaurat()
#RESTAURANT_3.describe_restaurat()
