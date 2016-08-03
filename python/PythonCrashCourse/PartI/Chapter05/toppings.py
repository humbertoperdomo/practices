#!/usr/bin/python3

# Checking for inequality
#requested_topping = 'mushrooms'
#if requested_topping != 'anchovies':
    #print("Hold the anchovies")

requested_toppings = ['mushrooms', 'green peppers', 'extra cheese']

# Testing multiple conditions
#if 'mushrooms' in requested_toppings:
    #print("Adding mushrooms")
#elif 'pepperoni' in requested_toppings:
    #print("Adding pepperoni")
#elif 'extra cheese' in requested_toppings:
    #print("Adding extra cheese")

# Checking for special items
#for requested_topping in requested_toppings:
    #if requested_topping == 'green peppers':
        #print("Sorry, we are out of green peppers right now.")
    #else:
        #print("Adding " + requested_topping + ".")

#print("\nFinished making your pizza!")

# Checking that a list is not empty
#requested_toppings = []

#if requested_toppings:
    #for requested_topping in requested_toppings:
        #if requested_topping == 'green peppers':
            #print("Sorry, we are out of green peppers right now.")
        #else:
            #print("Adding " + requested_topping + ".")
    #print("\nFinished making your pizza!")
#else:
    #print("Are you sure you want a plain pizza?")

# Using multiple lists
available_toppings = ['mushrooms', 'olives', 'green peppers', 'pepperoni', 
                        'pineapple', 'extra cheese']
requested_toppings = ['mushrooms', 'french fries', 'extra cheese']

for requested_topping in requested_toppings:
    if requested_topping in available_toppings:
        print("Adding " + requested_topping + ".")
    else:
        print("Sorry, we don't have " + requested_topping + ".")

print("\nFinished making your pizza!")
