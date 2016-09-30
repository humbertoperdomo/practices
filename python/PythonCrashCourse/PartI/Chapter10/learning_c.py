#!/usr/bin/python3
"""Print the content of a text file 3 times."""

file_path = "text_files/learning_python.txt"

with open(file_path) as file_object:
    lines = file_object.readlines()

print("")

for line in lines:
    print(line.rstrip().replace('Python', 'C'))
