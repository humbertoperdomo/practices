#!/usr/bin/python3
"""Remember me."""
import json

def get_stored_username():
    """Get stored username if available."""
    file_name = 'text_files/user_name.json'
    try:
        with open(file_name) as f_obj:
            username = json.load(f_obj)
    except FileNotFoundError:
        return None
    else:
        return username

def get_new_username():
    """Prompt for a new username."""
    username = input("What is your name? ")
    file_name = 'text_files/user_name.json'
    with open(file_name, 'w') as f_obj:
        json.dump(username, f_obj)
    return username

def greet_user():
    """Greet the user by name."""
    # Load the username, if it has been stored previously.
    #  Otherwise, prompt for the username and store it.
    username = get_stored_username()
    if username and verify_user(username):
        print("Welcome back, " + username + "!")
    else:
        username = get_new_username()
        print("We'll remember you when you come back, " + username + "!")

def verify_user(username):
    """Verify that it's the correct user."""
    answer = input("Are you " + username + "? (Yes/No) ")
    if answer.lower() == 'yes' or answer.lower() == 'y':
        return True
    return False

greet_user()
