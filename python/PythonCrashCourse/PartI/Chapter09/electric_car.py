#!/usr/bin/python3
"""Electric Car class
Importing a module into a module.
"""
from car import Car

class Battery():
    """A simple attempt to model a battey for an electric car."""

    def __init__(self, battery_size=70):
        """Initialize the battery's attributes."""
        self.battery_size = battery_size

    def describe_battery(self):
        """Print a statement describing the battery size."""
        print("This car has a " + str(self.battery_size) + "-KWh battery.")

    def get_range(self):
        """Print a statement about the range this battery provides."""
        if self.battery_size == 70:
            range_ = 240
        elif self.battery_size == 85:
            range_ = 270

        message = "This car can go approximately " + str(range_)
        message += " miles on a full charge."
        print(message)


class ElectricCar(Car):
    """Represent aspects of a car, specific to electric vehicles."""

    def __init__(self, make, model, year):
        """Initialize attributes of the parent class.
        Then initialize attributes specific to an electric car.
        """
        super().__init__(make, model, year)
        # Inheritance in Python 2.7
        #super(ElectricCar, self).__init__(make, model, year)
        # Defining attributes and methods for the child class
        #self.battery_size = 70
        self.battery = Battery()

    def describe_battery(self):
        """Print a statement describing the battery size."""
        self.battery.describe_battery()

    def fill_gas_tank(self):
        """Electric cars don't have gas tanks."""
        print("This car doesn't need a gas tank!")


#MY_TESLA = ElectricCar('tesla', 'model s', 2016)
#print(MY_TESLA.get_descriptive_name())
#MY_TESLA.describe_battery()
#MY_TESLA.battery.get_range()
#MY_TESLA.fill_gas_tank()
