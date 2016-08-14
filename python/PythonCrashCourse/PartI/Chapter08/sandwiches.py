#!/usr/bin/python3

def make_sandwich(*ingredients):
    print("\nMaking sandwich with the following ingredients:")
    for ingredient in ingredients:
        print("- " + ingredient)

make_sandwich('peanut butter')
make_sandwich('peanut butter', 'strawberry jam')
make_sandwich('pastrami', 'mustard', 'mayonnaise')
