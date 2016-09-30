#!/usr/bin/python3
"""Example to read a file text."""

#with open('text_files/pi_digits.txt') as file_object:
    #contents = file_object.read()
    #print(contents.rstrip())

# Absolute file path
#file_path = '/home/humberto/Documents/Repositories/practices/python/PythonCrashCourse/PartI/Chapter10/text_files/pi_digits.txt'
# Relative file path
file_path = 'text_files/pi_digits.txt'

#with open(file_path) as file_object:
    #contents = file_object.read()
    #print(contents.rstrip())

#with open(file_path) as file_object:
    #for line in file_object:
        #print(line.rstrip())

with open(file_path) as file_object:
    lines = file_object.readlines()

for line in lines:
    print(line.rstrip())
