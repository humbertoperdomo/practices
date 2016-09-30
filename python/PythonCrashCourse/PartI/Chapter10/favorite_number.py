#!/usr/bin/python3
"""Favorite number."""
import json

favorite_number = input("What is your favorite number? ")
file_name = 'text_files/favorite_number.json'
with open(file_name, 'w') as f_obj:
    json.dump(favorite_number, f_obj)
