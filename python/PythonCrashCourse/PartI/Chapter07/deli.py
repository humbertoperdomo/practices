#!/usr/bin/python3

sandwich_orders = ['chicken', 'pastrami', 'cheese', 'pastrami', 'meatballs', 'jam', 'pastrami', 'ham']
finished_sandwiches = []

print("Deli has run out of pastrami!!!")
while 'pastrami' in sandwich_orders:
    sandwich_orders.remove('pastrami')

while sandwich_orders:
    sandwich = sandwich_orders.pop() 
    print("I made your " + sandwich + " sandwich.")
    finished_sandwiches.append(sandwich)

print("\nFinished sandwiches: ")
for sandwich in finished_sandwiches:
    print(sandwich.title())
