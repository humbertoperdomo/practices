#!/usr/bin/python3
"""Guest book."""

path_file = "text_files/guest_book.txt"

while True:
    user_name = input("Please enter your name: ")

    if user_name == 'quit':
        break

    if user_name:
        with open(path_file, 'a') as file_object:
            file_object.write(user_name + '\n')
