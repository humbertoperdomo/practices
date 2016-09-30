#!/usr/bin/python3
"""Print the content of a text file 3 times."""

file_path = "text_files/learning_python.txt"

with open(file_path) as file_object:
    contents = file_object.read()
    print(contents.rstrip())

print("")

with open(file_path) as file_object:
    for line in file_object:
        print(line.rstrip())

with open(file_path) as file_object:
    lines = file_object.readlines()

print("")

for line in lines:
    print(line.rstrip())
