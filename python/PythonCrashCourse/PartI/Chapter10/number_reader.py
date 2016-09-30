#!/usr/bin/python3
"""Using json."""
import json

file_name = 'text_files/numbers.json'
with open(file_name) as f_obj:
    numbers = json.load(f_obj)

print(numbers)
