#!/usr/bin/python3
"""Using json."""
import json

numbers = [2, 3, 5, 7, 11, 13]

file_name = 'text_files/numbers.json'
with open(file_name, 'w') as f_obj:
    json.dump(numbers, f_obj)
