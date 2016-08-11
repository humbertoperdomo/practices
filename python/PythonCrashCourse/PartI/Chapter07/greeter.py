#!/usr/bin/python3

# Writing clear prompts
#name = input("Please enter your name: ")
#print("Hello, " + name + "!")


prompt = "If you tell us who you are, we can personalize the message you see."
prompt += "\nWhat is your first name? "

name = input(prompt)
# If you're using Pyhton 2.7, use raw_input() instead of input()
#name = raw_input(prompt)
print("Hello, " + name + "!")
