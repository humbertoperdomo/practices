#!/usr/bin/python3
# Importing an entire module
# Using 'as' to give a module an alias
#import pizza as p

#pizza.make_pizza(16, 'pepperoni')
#pizza.make_pizza(12, 'mushrooms', 'green peppers', 'extra cheese')
#p.make_pizza(16, 'pepperoni')
#p.make_pizza(12, 'mushrooms', 'green peppers', 'extra cheese')

# Importing specific functions
# Using 'as' to give a function an alias
#from pizza import make_pizza as mp

#make_pizza(16, 'pepperoni')
#make_pizza(12, 'mushrooms', 'green peppers', 'extra cheese')
#mp(16, 'pepperoni')
#mp(12, 'mushrooms', 'green peppers', 'extra cheese')

# Importing all functions in a module
from pizza import *

make_pizza(16, 'pepperoni')
make_pizza(12, 'mushrooms', 'green peppers', 'extra cheese')
