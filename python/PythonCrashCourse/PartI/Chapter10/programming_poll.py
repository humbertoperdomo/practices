#!/usr/bin/python3
"""Programming poll."""

path_file = "text_files/programming_poll.txt"

print("Write 'quit' to exit")
while True:
    answer = input("Why do you like programming? ")

    if answer == "quit":
        break

    if answer:
        with open(path_file, 'a') as file_object:
            file_object.write(answer + '\n')
