#!/usr/bin/python3

# Passing an arbitrary number of arguments
# Mixing positional and arbitrary arguments
def make_pizza(size, *toppings):
    """Summarize the pizza we are about to make."""
    print("\nMaking a " + str(size) +
        "-inch pizza with the following toppings:")
    for topping in toppings:
        print("- " + topping)

