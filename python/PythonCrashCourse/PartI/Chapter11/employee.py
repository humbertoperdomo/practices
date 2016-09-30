#!/usr/bin/python3
"""Employee class."""

class Employee():
    """Model of employee."""

    def __init__(self, first_name, last_name, annual_salary):
        """Initialize employee attributes."""
        self.first_name = first_name
        self.last_name = last_name
        self.annual_salary = annual_salary

    def give_raise(self, _raise=5000):
        """Give an raise method."""
        self.annual_salary += _raise
