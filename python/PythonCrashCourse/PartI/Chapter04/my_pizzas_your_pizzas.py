#!/usr/bin/python3

my_pizzas = ['neapolitan', 'california style', 'chicago deep dish', 
                'chicago thin crust', 'detroit style', 'new england greek', 
                'new york thin crust', 'st. louis style', 'new jersey style']
friend_pizzas = my_pizzas[:]

my_pizzas.append('pepperoni')
friend_pizzas.append('hawaiian')

print("My favorite pizzas are: ")
for pizza in my_pizzas:
    print(pizza)

print("\nMy friend's favorite pizzas are:")
for pizza in friend_pizzas:
    print(pizza)
