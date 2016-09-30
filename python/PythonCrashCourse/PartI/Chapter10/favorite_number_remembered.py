#!/usr/bin/python3
""" Favorite number remembered."""
import json

file_name = 'text_files/favorite_number.json'
try:
    with open(file_name) as f_obj:
        favorite_number = json.load(f_obj)
except FileNotFoundError:
    favorite_number = input("What is your favorite number? ")
    with open(file_name, 'w') as f_obj:
        json.dump(favorite_number, f_obj)
else:
    print("I know your favorite number! It's " + favorite_number + ".")
