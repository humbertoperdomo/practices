#!/usr/bin/python3
"""Electric Car class"""

# Inheritance in Python 2.7
#class Car(object):
class Car():
    """A simple attempt to represent a car."""

    def __init__(self, make, model, year):
        """Initialize attributes to describe a car.
        Setting a default value for an attribute"""
        self.make = make
        self.model = model
        self.year = year
        self.odometer_reading = 0

    def get_descriptive_name(self):
        """Return a neatly formatted descriptive name."""
        long_name = str(self.year) + ' ' + self.make + ' ' + self.model
        return long_name.title()

    def read_odometer(self):
        """Print a statement showing the car's mileage."""
        print("This car has " + str(self.odometer_reading) + " miles on it.")

    def update_odometer(self, mileage):
        """
        Set the odometer reading to the given value.
        Reject the change if it attempts to roll the odometer back.
        """
        if mileage >= self.odometer_reading:
            self.odometer_reading = mileage
        else:
            print("You can't roll back an odometer!")

    def increment_odometer(self, miles):
        """Add the given amount to the odometer reading."""
        self.odometer_reading += miles

    def fill_gas_tank(self):
        """Fill the gas tank."""
        print("Gas tank full.")


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

    def upgrade_battery(self):
        """Upgrade battery."""
        if self.battery_size != 85:
            self.battery_size = 85


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


MY_TESLA = ElectricCar('tesla', 'model s', 2016)
print(MY_TESLA.get_descriptive_name())
MY_TESLA.describe_battery()
MY_TESLA.battery.get_range()
MY_TESLA.battery.upgrade_battery()
MY_TESLA.battery.get_range()
