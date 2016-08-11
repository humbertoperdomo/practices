#!/usr/bin/python3

#message = input("Tell me something, and I will repeat it back to you: ")
# If you're using Pyhton 2.7, use raw_input() instead of input()
#message = raw_input("Tell me something, and I will repeat it back to you: ")
#print(message)


# Letting the user choose when to quit
prompt = "\nTell me something, and I will repeat it back to you: "
prompt += "\nEnter 'quit' to end the program. "

active = True
message = ""
while active:
    message = input(prompt)

    if message == 'quit':
        active = False
    else:
        print(message)
