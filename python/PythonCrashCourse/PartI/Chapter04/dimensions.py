#!/usr/bin/python3

dimensions = (200, 50)
#print(dimensions[0])
#print(dimensions[1])

# 'tuple' object does not support item assignment
#dimensions[0] = 250

# Looping through all values in a tuple
#for dimension in dimensions:
    #print(dimension)

# Writing over a tuple
print("Original dimensions: ")
for dimension in dimensions:
    print(dimension)

dimensions = (400, 100)
print("\nModified dimensions: ")
for dimension in dimensions:
    print(dimension)
