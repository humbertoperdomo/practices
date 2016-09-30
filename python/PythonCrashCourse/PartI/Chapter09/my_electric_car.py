#!/usr/bin/python3
"""Storing multiple classes in a module."""
from electric_car import ElectricCar

MY_TESLA = ElectricCar('tesla', 'model s', 2016)

print(MY_TESLA.get_descriptive_name())
MY_TESLA.battery.describe_battery()
MY_TESLA.battery.get_range()
