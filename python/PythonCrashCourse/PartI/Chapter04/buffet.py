#!/usr/bin/python3

simple_foods = ( 'salad', 'soup', 'pizza', 'hamburger', 'hot dog' )

for food in simple_foods:
    print(food)

# 'tuple' object does not support item assignment
#simple_foods[3] = 'crab'

simple_foods = ('salad', 'soup', 'pizza', 'spaghetti and meatballs', 'lasagna')
print('\nRestaurant changed its menu')
for food in simple_foods:
    print(food)

