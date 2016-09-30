#!/usr/bin/python3
"""Greet user."""
import json

file_name = 'text_files/user_name.json'

with open(file_name) as f_obj:
    user_name = json.load(f_obj)
    print("Welcome back, " + user_name + "!")
