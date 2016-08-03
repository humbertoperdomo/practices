#!/usr/bin/python3

cities = ['tlaquepaque', 'zapopan', 'tonala', 'guadalajara']

print(cities)
print("Number of elements: " + str(len(cities)) + ".\n")
cities.reverse()
print(cities)
print("\n")
print(sorted(cities))
print("\n")
print(sorted(cities, reverse=True))
print("\n")
print(cities)
cities.sort()
print("\n")
print(cities)
cities.sort(reverse=True)
print("\n")
print(cities)
