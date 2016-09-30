#!/usr/bin/python3
"""Class user"""

class Person():
    """Model a Person."""

    def __init__(self, first_name, last_name):
        """Initialize first_name and last_name attribute."""
        self._first_name = first_name
        self._last_name = last_name

    @property
    def first_name(self):
        """first_name property"""
        return self._first_name

    @property
    def last_name(self):
        """last_name property"""
        return self._last_name

class User():
    """Model a user"""

    def __init__(self, person, user_name, user_password, user_email):
        """Initialize first_name and last_name attributes."""
        self.person = person
        self.user_name = user_name
        self.user_password = user_password
        self.user_email = user_email
        self.login_attemps = 0

    def describe_user(self):
        """Describes user."""
        print('First name:'.ljust(12) + self.person.first_name.title())
        print('Last name:'.ljust(12) + self.person.last_name.title())
        print('Username:'.ljust(12) + self.user_name)
        print('Email:'.ljust(12) + self.user_email)

    def greet_user(self):
        """Greeting user."""
        print("\nHello " + self.user_name)

    def increment_login_attempts(self):
        """Increment the number of login attempts by 1."""
        self.login_attemps += 1

    def reset_login_attempts(self):
        """Reset the login attempts counter to 0."""
        self.login_attemps = 0

person_one = Person('humberto', 'perdomo')
user_one = User(person_one, 'hperdomo', '!Q2w3e4r', 'humberto.perdomo@gmail.com')
user_one.greet_user()
print("Number of login attempts till now: " + str(user_one.login_attemps))
user_one.increment_login_attempts()
user_one.increment_login_attempts()
user_one.increment_login_attempts()
user_one.increment_login_attempts()
print("Number of login attempts till now: " + str(user_one.login_attemps))
user_one.reset_login_attempts()
print("Number of login attempts till now: " + str(user_one.login_attemps))
