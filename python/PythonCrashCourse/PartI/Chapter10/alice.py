#!/usr/bin/python3
"""Handling the file not found error."""

file_name = 'text_files/alice.txt'

try:
    with open(file_name) as f_obj:
        contents = f_obj.read()
except FileNotFoundError:
    msg = "Sorry, the file " + file_name + " does not exist."
    print(msg)
else:
    # Count the approximate number of words in the file.
    words = contents.split()
    num_words = len(words)
    print("The file " + file_name + " has about " + str(num_words) + " words.")
