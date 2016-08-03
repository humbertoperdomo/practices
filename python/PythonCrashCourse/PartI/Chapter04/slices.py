#!/usr/bin/python3

pizza_kinds = ['neapolitan', 'california style', 'chicago deep dish', 
                'chicago thin crust', 'detroit style', 'new england greek', 
                'new york thin crust', 'st. louis style', 'new jersey style']

for kind in pizza_kinds:
    print("I like " + kind.title() + " pizza.")

print("\nI really love pizza!")
print("The first three items in the list are: ")
print(pizza_kinds[:3])

print("\nThree items from the middle of the list are: ")
print(pizza_kinds[(9//2) - 1:(9//2) + 2])

print("\nThree items from the middle of the list are: ")
print(pizza_kinds[-3:])
