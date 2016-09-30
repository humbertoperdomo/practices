#!/usr/bin/python3
"""Read stored favorite number."""
import json

file_name = 'text_files/favorite_number.json'
with open(file_name) as f_obj:
    favorite_number = json.load(f_obj)

print("I know your favorite number! It's " + favorite_number + ".")
