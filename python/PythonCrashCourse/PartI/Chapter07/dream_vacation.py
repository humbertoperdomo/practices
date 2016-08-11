#!/usr/bin/python3

prompt = "If you could visit one place in the world, where would you go? "
places_to_go = {}
active = True

while active:
    name = input("What is your name? ")
    place = input(prompt)

    places_to_go[name] = place

    keep_going = input("Would you like to continue with the poll? (yes/no) ")

    if keep_going == 'no':
        active = False

print("\n--- Result of the poll ---")
for name, place in places_to_go.items():
    print(name + " would like to go to " + place + ".")
