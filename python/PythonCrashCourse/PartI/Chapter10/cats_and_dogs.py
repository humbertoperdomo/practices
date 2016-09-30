#!/usr/bin/python3
"""Cats and dogs."""

cats_file = "text_files/cats.txt"
dogs_file = "text_files/dogs.txt"

try:
    current_file = cats_file
    with open(current_file) as f_cats:
        content = f_cats.read()
        print(content.rstrip())

    current_file = dogs_file
    with open(current_file) as f_dogs:
        for line in f_dogs:
            print(line.rstrip())
except FileNotFoundError:
    #print("Sorry, the file " + current_file + " does not exist.")
    pass
