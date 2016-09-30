#!/usr/bin/python3

user_name = input("Please type your name: ")

if user_name:
    path_file = "text_files/guest.txt"
    with open(path_file, 'w') as file_object:
        file_object.write(user_name)
