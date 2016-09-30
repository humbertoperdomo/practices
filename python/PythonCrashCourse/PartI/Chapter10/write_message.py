#!/usr/bin/python3
"""Write a message to a file text."""

path_file = "text_files/programming.txt"

#with open(path_file, 'w') as file_object:
    #file_object.write("I love programming.\n")
    #file_object.write("I love creating new games.\n")

with open(path_file, 'a') as file_object:
    file_object.write("I also love finding meaning in large datasets.\n")
    file_object.write("I love creating apps that can run in a browser.\n")
