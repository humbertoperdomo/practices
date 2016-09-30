#!/usr/bin/python3
"""Importing multiple classes from a module.
Importing an entire module.
Importing a module into a module.
"""
#from electric_car import Car, ElectricCar
#import electric_car
from car import Car
from electric_car import ElectricCar

my_beetle = Car('volkswagen', 'beetle', 2016)
print(my_beetle.get_descriptive_name())

my_tesla = ElectricCar('tesla', 'roadster', 2016)
print(my_tesla.get_descriptive_name())
