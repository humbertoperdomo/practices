#!/usr/bin/python3

squares = []
# Using range() to make a list of numbers
#for value in range(1, 11):
    #square = value ** 2;
    #squares.append(square)
#print(squares)

# List comprehensions
squares = [value**2 for value in range(1, 11)]
print(squares)
